package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.addon.elearning.service.configuration.CourseService;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

  @Path("cours")
  @Produces(MediaType.APPLICATION_JSON)
  public class CourseRestService implements ResourceContainer{
  private static Log          LOG = ExoLogger.getLogger(CourseRestService.class);

  @Inject
  private CourseService courseService;
    public CourseRestService() {
      courseService= CommonsUtils.getService(CourseService.class);
    }
    @GET
    @Path("/all")
    public Response getAllCourses() {


      try {
        List<CourseDTO> allCourses = courseService.getAllCourses();

        return Response.ok(allCourses, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all GamificationInformationsPortlet ", e);

        return Response.serverError()
                       .entity("Error listing all GamificationInformationsPortlet")
                       .build();
      }
    }
    @POST
    @Path("/add")
    public Response addCours(CourseDTO coursDTO) {
      try {
        coursDTO=courseService.addCours(coursDTO);
        return Response.ok().entity(coursDTO).build();


      } catch (Exception e) {
        return Response.serverError()
                       .entity("Error adding new cours")
                       .build();
      }

    }

}
