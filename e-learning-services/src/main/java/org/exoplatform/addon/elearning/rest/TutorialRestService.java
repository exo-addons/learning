package org.exoplatform.addon.elearning.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.exoplatform.addon.elearning.dto.Step;
import org.exoplatform.addon.elearning.dto.Tutorial;
import org.exoplatform.addon.elearning.entity.Status;
import org.exoplatform.addon.elearning.rest.entity.SpaceDataEntity;
import org.exoplatform.addon.elearning.rest.entity.TutorialsDataEntity;
import org.exoplatform.addon.elearning.service.TutorialService;
import org.exoplatform.commons.utils.HTMLSanitizer;
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
import java.util.List;

@Path("tutorial")
@Produces(MediaType.APPLICATION_JSON)
/* @RolesAllowed("users") */
public class TutorialRestService implements ResourceContainer {

  private static final Log LOG = ExoLogger.getLogger(TutorialRestService.class);

  private TutorialService tutorialService;

  private SpaceService spaceService;

  public TutorialRestService(TutorialService tutorialService, SpaceService spaceService) {
    this.tutorialService = tutorialService;
    this.spaceService = spaceService;
  }

  @POST
  @Path("/addTutorial")
  @RolesAllowed("users")
  @ApiOperation(value = "Adds a tutorial", httpMethod = "POST", response = Response.class, notes = "This Adds a tutorial")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response addTutorial(Tutorial tutorial) {
    try {
      String currentUser = ConversationState.getCurrent().getIdentity().getUserId();
      tutorial.setAuthor(currentUser);
      tutorial.setStatus(Status.DRAFT.name());
      tutorial = tutorialService.createTutorial(tutorial);

    } catch (Exception e) {
      LOG.error("Could not create Tutorial:", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutorial, MediaType.APPLICATION_JSON).build();

  }

  @DELETE
  @Path("/deleteTutorial/{id}")
  @RolesAllowed("users")
  @ApiOperation(value = "Delete tutorial", httpMethod = "DELETE", response = Response.class, notes = "This deletes the tutorial", consumes = "application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Tutorial deleted"),
          @ApiResponse(code = 400, message = "Invalid query input"),
          @ApiResponse(code = 401, message = "User not authorized to delete the Project"),
          @ApiResponse(code = 500, message = "Internal server error")})
  public Response deleteTutorial(@PathParam("id") Long id) {

    try {
      tutorialService.deleteTutorial(id);

    } catch (Exception e) {
      LOG.error("Could not deleteThemeById Tutorial\" with the Id {}", id, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.status(Response.Status.OK).entity("Tutorial with id " + id + " Deleted").build();
  }

  @PUT
  @Path("/updateTutorial")
  @RolesAllowed("users")
  @ApiOperation(value = "Update tutorial", httpMethod = "PUT", response = Response.class, notes = "This Update Tutorial info")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response updateTutorial(Tutorial tutorial) {

    try {
      tutorial = tutorialService.updateTutorial(tutorial);
    } catch (Exception e) {
      LOG.error("Could not update Tutorial", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutorial, MediaType.APPLICATION_JSON).build();
  }

  @PUT
  @Path("/postTutorial")
  @RolesAllowed("users")
  @ApiOperation(value = "Publish tutorial", httpMethod = "PUT", response = Response.class, notes = "This Publishes Tutorial")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response postTutorial(Tutorial tutorial) {
    try {
      tutorial = tutorialService.getTutorialById(tutorial.getId());
      tutorial.setStatus(Status.PUBLISHED.name());
      tutorial = tutorialService.updateTutorial(tutorial);
    } catch (Exception e) {
      LOG.error("Could not update Tutorial", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutorial, MediaType.APPLICATION_JSON).build();
  }

  @PUT
  @Path("/archiveTutorial")
  @RolesAllowed("users")
  @ApiOperation(value = "Archive tutorial", httpMethod = "PUT", response = Response.class, notes = "This archives Tutorial")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response archiveTutorial(Tutorial tutorial) {
    try {
      tutorial = tutorialService.getTutorialById(tutorial.getId());
      tutorial.setStatus(Status.ARCHIVED.name());
      tutorial = tutorialService.updateTutorial(tutorial);
    } catch (Exception e) {
      LOG.error("Could not update Tutorial", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutorial, MediaType.APPLICATION_JSON).build();
  }

  @GET
  @Path("/getTutorialById/{tutorialId}")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("users")
  @ApiOperation(value = "Get Tutorial by id", httpMethod = "GET", response = Response.class, notes = "This returns Tutorial by id")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response getTutorialById(@ApiParam(value = "tutorialId", required = true) @PathParam("tutorialId") Long tutorialId) {
    Tutorial tutorial;
    try {
      tutorial = tutorialService.getTutorialById(tutorialId);
    } catch (Exception e) {
      LOG.error("Could not get Tutorial by id {}", tutorialId, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutorial, MediaType.APPLICATION_JSON).build();

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
                                      @ApiParam(value = "Space Name", defaultValue = "") @QueryParam("spaceName") String spaceName,
                                      @ApiParam(value = "Search term", defaultValue = "") @QueryParam("q") String query,
                                      @ApiParam(value = "Offset", defaultValue = "0") @QueryParam("offset") int offset,
                                      @ApiParam(value = "Limit", defaultValue = "-1") @QueryParam("limit") int limit) {
    if (limit == 0) {
      limit = -1;
    }
    TutorialsDataEntity dataEntities;
    SpaceDataEntity spaceDataEntity = new SpaceDataEntity();
    List<Tutorial> tutorials;
    try {
      tutorials = tutorialService.getTutorialsByTheme(themeId, offset, limit);
      long count = tutorialService.countTutorialsByTheme(themeId);
      if (StringUtils.isNotEmpty(spaceName)) {
        Space space = spaceService.getSpaceByPrettyName(spaceName);
        spaceDataEntity = new SpaceDataEntity(space.getId(), space.getDisplayName(), space.getPrettyName(), space.getAvatarUrl(), space.getUrl(), space.getGroupId());
      }
      // TODO check permissions
      dataEntities = new TutorialsDataEntity(count, true, spaceDataEntity, tutorials);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials by ThemeId {}", themeId, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(dataEntities, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getTutorialsByName/{id}/{tutorialTitle}")
  @RolesAllowed("users")
  @ApiOperation(value = "Search Tutorial by name.", httpMethod = "GET", response = Response.class, notes = "This returns Tutorials by name")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response findTutorialsByName(@PathParam("id") Long id,
                                      @PathParam("tutorialTitle") String tutorialTitle,
                                      @ApiParam(value = "Offset", required = false, defaultValue = "0") @QueryParam("offset") int offset,
                                      @ApiParam(value = "Limit", required = false, defaultValue = "-1") @QueryParam("limit") int limit) {
    List<Tutorial> tutorials;
    try {
      tutorials = tutorialService.findTutorialsByName(tutorialTitle, id, offset, limit);

    } catch (Exception e) {
      LOG.error("Could not get all Tutorials by The Name {}", tutorialTitle, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(tutorials, MediaType.APPLICATION_JSON).build();

  }

  //Step rest services

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
    try {
      step = tutorialService.updateTutorialStep(step);
    } catch (Exception e) {
      LOG.error("Could not update Step", e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(step, MediaType.APPLICATION_JSON).build();
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

  @GET
  @Path("/getTutorialSteps/{tutorialId}")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("users")
  @ApiOperation(value = "Get All Tutorial Steps in order", httpMethod = "GET", response = Response.class, notes = "This returns Tutorial Steps in order")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response getTutorialSteps(@ApiParam(value = "tutorialId", required = true) @PathParam("tutorialId") Long tutorialId) {
    List<Step> steps;
    try {
      steps = tutorialService.getTutorialSteps(tutorialId);
    } catch (Exception e) {
      LOG.error("Could not get Steps of Tutorial with id {}", tutorialId, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(steps, MediaType.APPLICATION_JSON).build();

  }

  @GET
  @Path("/getTutorialStepByOrder/{tutorialId}/{stepOrder}")
  @Produces(MediaType.APPLICATION_JSON)
  @RolesAllowed("users")
  @ApiOperation(value = "Get Tutorial Step by order", httpMethod = "GET", response = Response.class, notes = "This returns Tutorial Step by order")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Request fulfilled"),
          @ApiResponse(code = 400, message = "Invalid query input"), @ApiResponse(code = 403, message = "Unauthorized operation"),
          @ApiResponse(code = 404, message = "Resource not found")})
  public Response getTutorialStepByOrder(@ApiParam(value = "tutorialId", required = true) @PathParam("tutorialId") Long tutorialId,
                                         @ApiParam(value = "stepOrder", required = true) @PathParam("stepOrder") int stepOrder) {
    Step step;
    try {
      step = tutorialService.getTutorialStepByOrder(tutorialId, stepOrder);
      step.setContent(HTMLSanitizer.sanitize(step.getContent()));
    } catch (Exception e) {
      LOG.error("Could not get Tutorial by id {}", tutorialId, e);
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(step, MediaType.APPLICATION_JSON).build();

  }

}
