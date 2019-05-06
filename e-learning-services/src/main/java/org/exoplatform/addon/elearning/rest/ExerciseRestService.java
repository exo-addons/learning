package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.ExerciseService;
import org.exoplatform.addon.elearning.service.dto.ExerciseDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
