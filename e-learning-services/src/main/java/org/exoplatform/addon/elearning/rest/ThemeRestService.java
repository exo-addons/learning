package org.exoplatform.addon.elearning.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.addon.elearning.rest.entity.SpaceDataEntity;
import org.exoplatform.addon.elearning.rest.entity.ThemesDataEntity;
import org.exoplatform.addon.elearning.service.ThemeService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("theme")
@Produces(MediaType.APPLICATION_JSON)
/* @RolesAllowed("users") */
public class ThemeRestService implements ResourceContainer {

  private static final Log LOG = ExoLogger.getLogger(ThemeRestService.class);

  private ThemeService themeService;

  private SpaceService spaceService;

  public ThemeRestService(ThemeService themeService, SpaceService spaceService) {
    this.themeService = themeService;
    this.spaceService = spaceService;
  }

  @POST
  @Path("addTheme")
  @RolesAllowed("users")
  @ApiOperation(value = "Adds a theme", httpMethod = "POST", response = Response.class, notes = "This Adds theme")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response addTheme(@ApiParam(value = "Theme to save", required = true) Theme theme) {
    try {
      Identity identity = ConversationState.getCurrent().getIdentity();
      if (identity.getUserId() == null) {
        return Response.status(Response.Status.FORBIDDEN).build();
      }
      theme.setCreator(identity.getUserId());

      Space space;
      if (StringUtils.isNotBlank(theme.getSpaceName())) {
        space = spaceService.getSpaceByPrettyName(theme.getSpaceName());
        if (space == null) {
          LOG.warn("User {} attempts to create a Tutorial under a non existing space {}", identity.getUserId(), theme.getSpaceName());
          return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (!spaceService.isMember(space, identity.getUserId())) {
          LOG.warn("User {} attempts to create a Theme under a non authorized space {}",
                  identity.getUserId(),
                  theme.getSpaceName());
          return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (theme.getParentId() != null && theme.getParentId() > 0) {
          Long parentId = theme.getParentId();
          Theme parent = themeService.getThemeById(parentId);
          if (!parent.canEdit(identity)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
          }
          theme = themeService.createTheme(theme, parentId, space.getPrettyName());
        } else {
          theme = themeService.createTheme(theme, null, space.getPrettyName());
        }
      } else {
        //todo add implementation for global themes (not space themes)
        return Response.status(Response.Status.UNAUTHORIZED).build();
      }

    } catch (Exception e) {
      LOG.error("Could not create Tutorial Theme", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(theme, MediaType.APPLICATION_JSON).build();

  }

  @DELETE
  @Path("deleteTheme/{id}")
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
  @Path("updateTheme")
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
    return Response.ok(theme, MediaType.APPLICATION_JSON).build();
  }

  @GET
  @Path("findThemes")
  @RolesAllowed("users")
  @ApiOperation(value = "Get Themes", httpMethod = "GET", response = Response.class, notes = "This returns Themes")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response findThemes(@ApiParam(value = "Search term", defaultValue = "") @QueryParam("q") String query,
                             @ApiParam(value = "Space Name", defaultValue = "") @QueryParam("spaceName") String spaceName,
                             @ApiParam(value = "Is Root Theme", defaultValue = "false") @QueryParam("isRoot") boolean isRoot,
                             @ApiParam(value = "Offset", defaultValue = "0") @QueryParam("offset") int offset,
                             @ApiParam(value = "Limit", defaultValue = "-1") @QueryParam("limit") int limit) {
    if (limit == 0) {
      limit = -1;
    }
    ThemesDataEntity dataEntity = new ThemesDataEntity();
    List<Theme> themes;
    try {
      Identity identity = ConversationState.getCurrent().getIdentity();
      if (StringUtils.isNoneEmpty(spaceName)) {
        // get space themes
        Space space = spaceService.getSpaceByPrettyName(spaceName);
        boolean isMember = spaceService.isMember(space, identity.getUserId());
        if (!isMember) {
          return Response.status(Response.Status.FORBIDDEN).build();

        } else {
          boolean canUpdateTheme = spaceService.isManager(space, identity.getUserId());
          long count = themeService.countFoundThemesBySpaceName(spaceName, isRoot, query);
          themes = themeService.findThemesBySpaceName(spaceName, isRoot, query, offset, limit);
          SpaceDataEntity spaceDataEntity = new SpaceDataEntity(space.getId(), space.getDisplayName(), space.getPrettyName(), space.getAvatarUrl(), space.getUrl(), space.getGroupId());
          dataEntity = new ThemesDataEntity(count, canUpdateTheme, spaceDataEntity, themes);

        }
      }

    } catch (Exception e) {
      LOG.error("Could not get all Themes", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(dataEntity, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("findThemeById/{id}")
  @RolesAllowed("users")
  @ApiOperation(value = "Get Theme by id", httpMethod = "GET", response = Response.class, notes = "This returns Theme")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response findThemeById(@ApiParam(value = "themeId", required = true) @PathParam("id") Long themeId) {
    Theme theme;
    try {
      theme = themeService.getThemeById(themeId);
      if (theme == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
    } catch (Exception e) {
      LOG.error("Could not get all Themes", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(theme, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("getChildThemes")
  @RolesAllowed("users")
  @ApiOperation(value = "Get Themes", httpMethod = "GET", response = Response.class, notes = "This returns Themes")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response getChildThemes(@ApiParam(value = "Search term", defaultValue = "") @QueryParam("q") String query,
                                 @ApiParam(value = "Space Name", defaultValue = "") @QueryParam("spaceName") String spaceName,
                                 @ApiParam(value = "Parent Theme Id", required = true) @QueryParam("parentThemeId") long parentThemeId,
                                 @ApiParam(value = "Offset", defaultValue = "0") @QueryParam("offset") int offset,
                                 @ApiParam(value = "Limit", defaultValue = "-1") @QueryParam("limit") int limit) {
    if (limit == 0) {
      limit = -1;
    }
    ThemesDataEntity dataEntity = new ThemesDataEntity();
    List<Theme> themes;
    try {
      Identity identity = ConversationState.getCurrent().getIdentity();
      if (StringUtils.isNoneEmpty(spaceName)) {
        Space space = spaceService.getSpaceByPrettyName(spaceName);
        boolean isMember = spaceService.isMember(space, identity.getUserId());
        if (!isMember) {
          return Response.status(Response.Status.FORBIDDEN).build();

        } else {
          boolean canUpdateTheme = spaceService.isManager(space, identity.getUserId());
          long count = themeService.countParentThemeChildren(parentThemeId, query);
          themes = themeService.retrieveChildThemes(parentThemeId, query, offset, limit);
          SpaceDataEntity spaceDataEntity = new SpaceDataEntity(space.getId(), space.getDisplayName(), space.getPrettyName(), space.getAvatarUrl(), space.getUrl(), space.getGroupId());
          dataEntity = new ThemesDataEntity(count, canUpdateTheme, spaceDataEntity, themes);
        }
      }

    } catch (Exception e) {
      LOG.error("Could not get all Themes", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(dataEntity, MediaType.APPLICATION_JSON).build();

  }

  @PUT
  @Path("updateThemeColor/{themeId}")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("users")
  @ApiOperation(value = "Change Theme Color", httpMethod = "POST", response = Response.class, notes = "This change Theme Color")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response changeThemeColor(@ApiParam(value = "themeId", required = true) @PathParam("themeId") Long themeId,
                                   @ApiParam(value = "color", required = false, defaultValue = "null") @QueryParam("color") String color) {
    Theme theme;
    try {
      theme = themeService.getThemeById(themeId);
      if (theme == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
      theme = themeService.changeThemeColor(theme, color);
    } catch (Exception e) {
      LOG.error("Could not get all Themes", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(theme, MediaType.APPLICATION_JSON).build();
  }

}
