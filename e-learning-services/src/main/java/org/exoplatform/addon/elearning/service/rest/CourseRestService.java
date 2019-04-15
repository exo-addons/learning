package org.exoplatform.addon.elearning.service.rest;

import org.exoplatform.addon.elearning.dto.CourseDTO;
import org.exoplatform.addon.elearning.entity.Course;
import org.exoplatform.addon.elearning.service.utils.CourseService;
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
  private   CacheControl cacheControl;
  private static Log          LOG = ExoLogger.getLogger(CourseRestService.class);

  @Inject
  private CourseService courseService;

  @GET
  @Path("/getAllCourses")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Course> getallCouses(){
  return courseService.getAllCourse();
}

  @GET @Path("/getCourse/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public CourseDTO select(@PathParam("id") Long id)
  {
    return courseService.getcours(id);
  }

  @POST
  @Path("/addCourse")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Course addCourse(CourseDTO course){
    LOG.info("/**********************rest service***************/");
    LOG.info(courseService.addCourse(course));
    return courseService.addCourse(course);
  }

}
