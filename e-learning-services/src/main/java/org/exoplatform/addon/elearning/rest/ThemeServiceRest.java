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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.exoplatform.addon.elearning.service.configuration.ThemeService;
import org.exoplatform.addon.elearning.service.dto.Theme;
import org.exoplatform.services.rest.resource.ResourceContainer;

@Path("theme")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeServiceRest implements ResourceContainer {

  private ThemeService themeService;

  public ThemeServiceRest(ThemeService themeService) {
    this.themeService = themeService;
  }

  @POST
  @Path("/addTheme")
  public Response addTheme(Theme theme) {
    try {
      theme = themeService.createTheme(theme);

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not create Theme").build();
    }
    return Response.status(Response.Status.OK).entity(theme).build();

  }

  @DELETE
  @Path("/deleteTheme/{id}")
  public Response deleteTheme(@PathParam("id") Long id) {

    try {
      themeService.deleteTheme(id);

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not delete Theme").build();
    }
    return Response.status(Response.Status.OK).entity("Theme with id " + id + " Deleted").build();
  }

  @PUT
  @Path("/updateTheme")
  public Response updateTheme(Theme theme) {

    try {
      theme = themeService.updateTheme(theme);

    } catch (Exception e) {

      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not update Theme").build();
    }
    return Response.status(Response.Status.OK).entity("Theme with id " + theme.getId() + " Updated").build();
  }

  @GET
  @Path("/getAllThemes")
  public Response getAllThemes() {
    List<Theme> themes = new ArrayList<Theme>();
    try {
      themes = themeService.getAllThemes();

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not get all Themes").build();
    }
    return Response.ok(themes, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getThemeById/{id}")
  public Response getThemeById(@PathParam("id") Long id) {
    Theme theme = new Theme();

    try {
      theme = themeService.getThemeById(id);

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No Theme found with id " + id).build();
    }
    return Response.ok(theme, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getAllThemeNames")
  public Response getAllThemeNames() {
    List<String> themes = new ArrayList<String>();
    try {
      themes = themeService.getAllThemeNames();

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not get all Theme Names").build();
    }
    return Response.ok(themes, MediaType.APPLICATION_JSON).build();

  }

}
