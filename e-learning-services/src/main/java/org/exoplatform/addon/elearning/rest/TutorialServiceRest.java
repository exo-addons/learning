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

import org.exoplatform.addon.elearning.service.configuration.TutorialService;
import org.exoplatform.addon.elearning.service.dto.Tutorial;
import org.exoplatform.services.rest.resource.ResourceContainer;

@Path("tuto")
@Produces(MediaType.APPLICATION_JSON)
public class TutorialServiceRest implements ResourceContainer {

  private TutorialService tutorialService;

  public TutorialServiceRest(TutorialService tutorialService) {
    this.tutorialService = tutorialService;
  }

  @POST
  @Path("/addTuto")
  public Response addTutorial(Tutorial tuto) {
    try {
      tuto = tutorialService.createTutorial(tuto);

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not create Tutorial").build();
    }
    return Response.status(Response.Status.OK).entity(tuto).build();

  }

  @DELETE
  @Path("/deleteTuto/{id}")
  public Response deleteTuto(@PathParam("id") Long id) {

    try {
      tutorialService.deleteTutorial(id);

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not delete Tutorial").build();
    }
    return Response.status(Response.Status.OK).entity("Tutorial with id " + id + " Deleted").build();
  }

  @PUT
  @Path("/updateTuto")
  public Response updateTuto(Tutorial tuto) {

    try {
      tuto = tutorialService.updateTutorial(tuto);

    } catch (Exception e) {

      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not update Tutorial").build();
    }
    return Response.status(Response.Status.OK).entity("Tutorial with id " + tuto.getId() + " Updated").build();
  }

  @GET
  @Path("/getAllTutos")
  public Response getAllTutos() {
    List<Tutorial> tutos = new ArrayList<Tutorial>();
    try {
      tutos = tutorialService.getAllTutos();

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not get all Tutorials").build();
    }
    return Response.ok(tutos, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getTutoById/{id}")
  public Response getTutoById(@PathParam("id") Long id) {
    Tutorial tuto = new Tutorial();

    try {
      tuto = tutorialService.getTutoById(id);

    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No Tutorial found with id " + id).build();
    }
    return Response.ok(tuto, MediaType.APPLICATION_JSON).build();

  }

}
