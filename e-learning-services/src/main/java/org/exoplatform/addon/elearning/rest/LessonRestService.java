package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.LessonService;
import org.exoplatform.addon.elearning.service.dto.LessonDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
