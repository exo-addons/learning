package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.LessonService;
import org.exoplatform.addon.elearning.service.dto.LessonDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("lesson")
@Produces(MediaType.APPLICATION_JSON)
public class LessonRestService implements ResourceContainer {
  private static Log LOG = ExoLogger.getLogger(CourseRestService.class);

  @Inject
  private LessonService lessonService;

  public LessonRestService() {
    lessonService=CommonsUtils.getService(LessonService.class);

  }

  @GET
  @Path("/all")
  public Response getAllLessons() {


    try {
      List<LessonDTO> allCourses = lessonService.getAllLessons();

      return Response.ok(allCourses, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all Lesson Portlet ", e);

      return Response.serverError()
                     .entity("Error listing all Lesson Portlet")
                     .build();
    }
  }
  @POST
  @Path("/add")
  public Response addLesson(LessonDTO lessonDTO) {
    try {
      lessonDTO=lessonService.addLesson(lessonDTO);
      return Response.ok().entity(lessonDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new lesson")
                     .build();
    }

  }
  @GET
  @Path("/getLessonsByIdCourse/{id}")
  public Response findLessonsByIdCourse(@PathParam("id")Long id){
    try {
      List<LessonDTO> lessonDTOS = lessonService.findLessonsByCourseId(id);
      return Response.ok(lessonDTOS, MediaType.APPLICATION_JSON).build();
    }catch(Exception e){
      LOG.error("Error listing the Lessons By id course ", e);

      return Response.serverError()
                     .entity("Error listing Error listing the Lessons By id course")
                     .build();
    }

  }
  @PUT
  @Path("/update")
  public Response updateLesson(LessonDTO lessonDTO){
    try{
      lessonDTO=lessonService.updateLesson(lessonDTO);
      return Response.ok().entity(lessonDTO).build();
    }catch (Exception e){
      LOG.error("Error updating lesson {} by {} ", lessonDTO.getTitleLesson(), e);
      return Response.serverError()
                     .entity("Error updating a lesson")
                     .build();

    }
  }

  @DELETE
  @Path("/delete/{id}")
  public  Response deleteLesson(@PathParam("id") Long id ) {
    try {

      lessonService.deleteLesson(id);

      return Response.ok().build();
    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error delete lesson")
                     .build();

    }

  }

}
