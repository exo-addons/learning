package org.exoplatform.addon.perkstore.service.rest;

import org.exoplatform.addon.perkstore.dto.CourseDTO;
import org.exoplatform.addon.perkstore.entity.Course;
import org.exoplatform.addon.perkstore.service.utils.CourseService;
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
  private static Log          log = ExoLogger.getLogger(CourseRestService.class);

  @Inject
  private CourseService courseService;
  private CourseDTO courseDTO;

@GET
@Path("/getAllCourses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public List<Course> getallCouses(){
  return courseService.getAllCourse();
}

  @GET @Path("/getdto/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public CourseDTO select(@PathParam("id") Long id)
  {
    return courseService.getcours(id);
  }

}
