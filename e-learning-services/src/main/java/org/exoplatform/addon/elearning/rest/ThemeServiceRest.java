package org.exoplatform.addon.elearning.rest;

import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addon.elearning.service.ThemeService;
import org.exoplatform.addon.elearning.dto.Theme;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
  /* @RolesAllowed("users") */
  public Response addTheme(Theme theme) {
    try {
      String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
      if (currentUser == null) {
        return Response.status(Response.Status.FORBIDDEN).build();
      }
      Space space = null;

      if (StringUtils.isNotBlank(theme.getSpaceName())) {
        space = spaceService.getSpaceByPrettyName(theme.getSpaceName());
        if (space == null) {
          LOG.warn("User {} attempts to createTutorial a project under a non existing space {}", currentUser, theme.getSpaceName());
          return Response.status(Response.Status.UNAUTHORIZED).build();
        }
      }

      theme = themeService.createTheme(theme);

    } catch (Exception e) {

      LOG.error("Could not createTutorial Theme", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity(theme).build();

  }

  @DELETE
  @Path("/deleteTheme/{id}")
  /* @RolesAllowed("users") */
  public Response deleteTheme(@PathParam("id") Long id) {

    try {
      themeService.deleteTheme(id);

    } catch (Exception e) {
      LOG.error("Could not deleteThemeById Theme with the Id {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Theme with id " + id + " Deleted").build();
  }

  @PUT
  @Path("/updateTheme")
  /* @RolesAllowed("users") */
  public Response updateTheme(Theme theme) {

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
