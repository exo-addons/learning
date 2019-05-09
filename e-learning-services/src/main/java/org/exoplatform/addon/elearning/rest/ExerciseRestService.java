package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.entities.ExerciseEntity;
import org.exoplatform.addon.elearning.service.configuration.ExerciseService;
import org.exoplatform.addon.elearning.service.dto.ExerciseDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("exercise")
@Produces(MediaType.APPLICATION_JSON)
public class ExerciseRestService implements ResourceContainer {
  private static Log             LOG             = ExoLogger.getLogger(ExerciseRestService.class);
  protected      IdentityManager identityManager = null;



  @Inject
  private ExerciseService exerciseService;

  public ExerciseRestService() {
    exerciseService=CommonsUtils.getService(ExerciseService.class);
    identityManager = CommonsUtils.getService(IdentityManager.class);

  }


  @GET
  @Path("/all")
  public Response getAllExercises() {
    try {
      List<ExerciseDTO> allExercises = exerciseService.getAllExercises();

      return Response.ok(allExercises, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all Exercises ", e);

      return Response.serverError()
                     .entity("Error listing all Courses")
                     .build();
    }
  }

  @POST
  @Path("/add")
  public Response addExercise(ExerciseDTO exerciseDTO) {
    try {
      exerciseDTO=exerciseService.addExercise(exerciseDTO);
      return Response.ok().entity(exerciseDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new Exercise")
                     .build();
    }

  }
  @GET
  @Path("/getExercisesFromCourse/{idCourse}")
  public Response findExercisesByNameCourse(@PathParam("idCourse") Long idCourse) {
    try {
      String user= ConversationState.getCurrent().getIdentity().getUserId();
      List<ExerciseDTO> exerciseDTOS = exerciseService.findExercisesByCourseId(idCourse,user);
      return Response.ok(exerciseDTOS, MediaType.APPLICATION_JSON).build();
    } catch (Exception e) {

      LOG.error("Error listing the Exercises By Course name ", e);

      return Response.serverError()
                     .entity("Error listing Error listing the Exercises By Course name")
                     .build();
    }
  }
  @DELETE
  @Path("/delete/{id}")
  public  Response deltetidea(@PathParam("id") Long id ) {
    try {

      exerciseService.deleteExercise(id);

      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error delete exercise")
                     .build();

    }

    }

  @PUT
  @Path("/update")
  public Response updateExercise(ExerciseDTO exerciseDTO) {

    try {
      exerciseDTO = exerciseService.updateExercise(exerciseDTO);
      return Response.ok().entity(exerciseDTO).build();
    } catch (Exception e) {
      LOG.error("Error updating exercise {} by {} ", exerciseDTO.getQuestionExercise(), e);
      return Response.serverError()
                     .entity("Error updating a exercise")
                     .build();
    }

  }
}

