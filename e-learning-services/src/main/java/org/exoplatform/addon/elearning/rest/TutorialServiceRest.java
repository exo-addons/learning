package org.exoplatform.addon.elearning.rest;

import io.swagger.annotations.ApiParam;
import org.exoplatform.addon.elearning.service.configuration.TutorialService;
import org.exoplatform.addon.elearning.service.dto.Tutorial;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("tuto")
@Produces(MediaType.APPLICATION_JSON)
/* @RolesAllowed("users") */
public class TutorialServiceRest implements ResourceContainer {

  private static final Log LOG = ExoLogger.getLogger(TutorialServiceRest.class);

  private TutorialService tutorialService;

  public TutorialServiceRest(TutorialService tutorialService) {
    this.tutorialService = tutorialService;
  }

  @POST
  @Path("/addTuto")
  /* @RolesAllowed("users") */
  public Response addTutorial(Tutorial tuto) {
    try {
      String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
      tuto.setAuthor(currentUser);
      tuto = tutorialService.createTutorial(tuto);

    } catch (Exception e) {
      LOG.error("Could not create Tutorial", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity(tuto).build();

  }

  @DELETE
  @Path("/deleteTuto/{id}")
  /* @RolesAllowed("users") */
  public Response deleteTuto(@PathParam("id") Long id) {

    try {
      tutorialService.deleteTutorial(id);

    } catch (Exception e) {
      LOG.error("Could not delete Tutorial\" with the Id {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Tutorial with id " + id + " Deleted").build();
  }

  @PUT
  @Path("/updateTuto")
  /* @RolesAllowed("users") */
  public Response updateTuto(Tutorial tuto) {

    try {
      tuto = tutorialService.updateTutorial(tuto);

    } catch (Exception e) {

      LOG.error("Could not update Tutorial", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Tutorial with id " + tuto.getId() + " Updated").build();
  }

  @GET
  @Path("/getAllTutos")
  /* @RolesAllowed("users") */
  public Response getAllTutos(@ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                              @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit) {
    List<Tutorial> tutos = new ArrayList<Tutorial>();
    try {
      tutos = tutorialService.getAllTutos(offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutos, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getTutoById/{id}")
  /* @RolesAllowed("users") */
  public Response getTutoById(@PathParam("id") Long id) {
    Tutorial tuto = new Tutorial();

    try {
      tuto = tutorialService.getTutoById(id);

    } catch (Exception e) {
      LOG.error("No Tutorial found with id {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tuto, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getAllTutosByTheme/{id}")
  /* @RolesAllowed("users") */
  public Response getAllTutosByTheme(@PathParam("id") Long id,
                                     @ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                                     @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit) {
    List<Tutorial> tutos = new ArrayList<Tutorial>();
    try {
      tutos = tutorialService.getAllTutosByTheme(id, offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials by ThemeId {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutos, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getTutosByName/{id}/{tutoTitle}")
  /* @RolesAllowed("users") */
  public Response findTutosByName(@PathParam("id") Long id,
                                  @PathParam("tutoTitle") String tutoTitle,
                                  @ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                                  @ApiParam(value = "Limit", required = false, defaultValue = "20") @QueryParam("limit") int limit) {
    List<Tutorial> tutos = new ArrayList<Tutorial>();
    try {
      tutos = tutorialService.findTutosByName(tutoTitle, id, offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials by The Name {}", tutoTitle, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutos, MediaType.APPLICATION_JSON).build();

  }

}
