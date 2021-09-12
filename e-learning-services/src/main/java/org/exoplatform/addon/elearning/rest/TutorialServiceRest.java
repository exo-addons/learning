package org.exoplatform.addon.elearning.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.rest.entity.TutorialsDataEntity;
import org.exoplatform.addon.elearning.service.TutorialService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tutorial")
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
      LOG.error("Could not createTutorial Tutorial", e);
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
      LOG.error("Could not deleteThemeById Tutorial\" with the Id {}", id, e);
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
  @Path("/getTutorialsByTheme/{themeId}")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("users")
  @ApiOperation(value = "Get Tutorials by theme", httpMethod = "GET", response = Response.class, notes = "This returns Tutorials")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response getTutorialsByTheme(@ApiParam(value = "themeId", required = true) @PathParam("themeId") Long themeId,
                                      @ApiParam(value = "Offset", defaultValue = "0") @QueryParam("offset") int offset,
                                      @ApiParam(value = "Limit", defaultValue = "-1") @QueryParam("limit") int limit) {
    if (limit == 0) {
      limit = -1;
    }
    TutorialsDataEntity dataEntities;
    List<Tutorial> tutorials;
    try {
      tutorials = tutorialService.getTutorialsByTheme(themeId, offset, limit);
      long count = tutorialService.countTutorialsByTheme(themeId);
      dataEntities = new TutorialsDataEntity(count, tutorials);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials by ThemeId {}", themeId, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(dataEntities, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getTutosByName/{id}/{tutoTitle}")
  /* @RolesAllowed("users") */
  public Response findTutosByName(@PathParam("id") Long id,
                                  @PathParam("tutoTitle") String tutoTitle,
                                  @ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                                  @ApiParam(value = "Limit", required = false, defaultValue = "-1") @QueryParam("limit") int limit) {
    List<Tutorial> tutos;
    try {
      tutos = tutorialService.findTutorialsByName(tutoTitle, id, offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials by The Name {}", tutoTitle, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutos, MediaType.APPLICATION_JSON).build();

  }

  @POST
  @Path("/addTutorialStep/{id}")
  @RolesAllowed("users")
  @ApiOperation(value = "Adds a tutorial step", httpMethod = "POST", response = Response.class, notes = "This adds a tutorial step")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response addTutorialStep(@ApiParam(value = "Tutorial id", required = true) @PathParam("id") Long id,
                                  @ApiParam(value = "Step to save", required = true) Step step) {
    String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
    if (step == null) {
      LOG.warn("User {} attempts to add a non valid step.", currentUser);
    }
    step = tutorialService.addTutorialStep(step, id);

    return Response.status(Response.Status.OK).entity(step).build();

  }

  @PUT
  @Path("/updateTutorialStep")
  @RolesAllowed("users")
  @ApiOperation(value = "Updates a tutorial step", httpMethod = "PUT", response = Response.class, notes = "This updates a tutorial step")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response updateTutorialStep(@ApiParam(value = "Step to save", required = true) Step step) {
    step = tutorialService.updateTutorialStep(step);
    return Response.status(Response.Status.OK).entity("Tutorial Step with id " + step.getId() + " Updated").build();
  }

  @DELETE
  @Path("/deleteTutorialStep/{id}")
  @RolesAllowed("users")
  @ApiOperation(value = "Deletes a tutorial step", httpMethod = "PUT", response = Response.class, notes = "This deletes a tutorial step")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response deleteTutorialStep(@ApiParam(value = "Tutorial step id", required = true) @PathParam("id") Long stepId) {
    tutorialService.deleteTutorialStep(stepId);
    return Response.status(Response.Status.OK).entity("Tutorial with id " + stepId + " Deleted").build();
  }

}
