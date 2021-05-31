package org.exoplatform.addon.elearning.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.exoplatform.addon.elearning.service.configuration.ThemeService;
import org.exoplatform.addon.elearning.service.dto.Theme;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import io.swagger.annotations.ApiParam;

@Path("theme")
@Produces(MediaType.APPLICATION_JSON)
/*@RolesAllowed("users")*/
public class ThemeServiceRest implements ResourceContainer {

  private static final Log LOG           = ExoLogger.getLogger(ThemeServiceRest.class);
  
  private ThemeService themeService;

  public ThemeServiceRest(ThemeService themeService) {
    this.themeService = themeService;
  }

  @POST
  @Path("/addTheme")
  /*@RolesAllowed("users")*/
  public Response addTheme(Theme theme) {
    try {
      theme = themeService.createTheme(theme);

    } catch (Exception e) {
      
      LOG.error("Could not create Theme", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity(theme).build();

  }

  @DELETE
  @Path("/deleteTheme/{id}")
  /*@RolesAllowed("users")*/
  public Response deleteTheme(@PathParam("id") Long id) {

    try {
      themeService.deleteTheme(id);

    } catch (Exception e) {
      LOG.error("Could not delete Theme with the Id {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Theme with id " + id + " Deleted").build();
  }

  @PUT
  @Path("/updateTheme")
  /*@RolesAllowed("users")*/
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
  /*@RolesAllowed("users")*/
  public Response getAllThemes(@ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                               @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit) {
    List<Theme> themes = new ArrayList<Theme>();
    try {
      themes = themeService.getAllThemes(offset,limit);

    } catch (Exception e) {
      LOG.error("Could not get all Themes", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(themes, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getThemeById/{id}")
  /*@RolesAllowed("users")*/
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
  @Path("/getAllThemeNames")
  /*@RolesAllowed("users")*/
  public Response getAllThemeNames(@ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                                   @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit) {
    List<String> themes = new ArrayList<String>();
    try {
      themes = themeService.getAllThemeNames(offset,limit);

    } catch (Exception e) {
      LOG.error("Could not get all Theme Names", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(themes, MediaType.APPLICATION_JSON).build();

  }

}
