package org.exoplatform.addon.elearning.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.service.ThemeService;
import org.exoplatform.addon.elearning.util.UserUtil;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("theme")
@Produces(MediaType.APPLICATION_JSON)
/* @RolesAllowed("users") */
public class ThemeServiceRest implements ResourceContainer {

  private static final Log LOG = ExoLogger.getLogger(ThemeServiceRest.class);

  private ThemeService themeService;

  private SpaceService spaceService;

  public ThemeServiceRest(ThemeService themeService, SpaceService spaceService) {
    this.themeService = themeService;
    this.spaceService = spaceService;
  }

  @POST
  @Path("/addTheme")
  @RolesAllowed("users")
  @ApiOperation(value = "Adds a theme", httpMethod = "POST", response = Response.class, notes = "This Adds theme")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response addTheme(@ApiParam(value = "Theme to save", required = true) Theme theme) {
    try {
      String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
      if (currentUser == null) {
        return Response.status(Response.Status.FORBIDDEN).build();
      }
      Space space;

      if (StringUtils.isNotBlank(theme.getSpaceName())) {
        space = spaceService.getSpaceByPrettyName(theme.getSpaceName());
        if (space == null) {
          LOG.warn("User {} attempts to create a Tutorial under a non existing space {}", currentUser, theme.getSpaceName());
          return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (!spaceService.isMember(space, currentUser)) {
          LOG.warn("User {} attempts to create a Theme under a non authorized space {}",
                  currentUser,
                  theme.getSpaceName());
          return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (theme.getParent() != null) {
          Long parentId = theme.getParent().getId();
          Theme parent = themeService.getThemeById(parentId);
          if (!parent.canEdit(ConversationState.getCurrent().getIdentity())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
          }
          theme = themeService.createTheme(theme, parentId);
        } else {
          List<String> memberships = UserUtil.getSpaceMemberships(space.getGroupId());
          Set<String> managers = new HashSet<>(Arrays.asList(memberships.get(0)));
          Set<String> participators = new HashSet<>(Arrays.asList(memberships.get(1)));
          theme.setManagers(managers);
          theme.setParticipators(participators);
          theme = themeService.createTheme(theme);
        }
      }

    } catch (Exception e) {
      LOG.error("Could not create Tutorial Theme", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity(theme).build();

  }

  @DELETE
  @Path("/deleteTheme/{id}")
  @RolesAllowed("users")
  @ApiOperation(value = "Delete theme", httpMethod = "DELETE", response = Response.class, notes = "This deletes the theme", consumes = "application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Theme deleted"),
          @ApiResponse(code = 400, message = "Invalid query input"),
          @ApiResponse(code = 401, message = "User not authorized to delete the Project"),
          @ApiResponse(code = 500, message = "Internal server error")})
  public Response deleteTheme(@ApiParam(value = "themeId", required = true) @PathParam("id") Long themeId) {

    try {
      themeService.deleteTheme(themeId);

    } catch (Exception e) {
      LOG.error("Could not deleteThemeById Theme with the Id {}", themeId, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Theme with id " + themeId + " Deleted").build();
  }

  @PUT
  @Path("/updateTheme")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("users")
  @ApiOperation(value = "Update Theme", httpMethod = "PUT", response = Response.class, notes = "This Update Theme info")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response updateTheme(@ApiParam(value = "Theme to update", required = true) Theme theme) {

    try {
      theme = themeService.updateTheme(theme);

    } catch (Exception e) {

      LOG.error("Could not update Theme", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Theme with id " + theme.getId() + " Updated").build();
  }

  @GET
  @Path("/getAllThemes")
  /* @RolesAllowed("users") */
  public Response getAllThemes(@ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                               @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit) {
    Set<Theme> themes;
    try {
      themes = themeService.getAllThemes(offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Themes", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(themes, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getThemeById/{id}")
  /* @RolesAllowed("users") */
  public Response getThemeById(@PathParam("id") Long id) {
    Theme theme = new Theme();

    try {
      theme = themeService.getThemeById(id);

    } catch (Exception e) {
      LOG.error("No Theme found with id {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(theme, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/findAllThemesByName/{themeName}")
  /* @RolesAllowed("users") */
  public Response getAllThemeNames(@ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                                   @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit,
                                   @PathParam("themeName") String themeName) {
    Set<Theme> themes;
    try {
      themes = themeService.findAllThemesByName(themeName, offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Themes by Name", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(themes, MediaType.APPLICATION_JSON).build();

  }

}
