package org.exoplatform.addon.elearning.service.configuration;


import org.exoplatform.addon.elearning.storage.CourseDao;
import org.exoplatform.addon.elearning.entities.CourseEntity;

import java.util.logging.Logger;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import org.json.JSONObject;
import org.json.JSONArray;

public class CourseService {

  private CourseDao courseDao;

  public CourseService(CourseDao courseDao) {
    this.courseDao = courseDao;
  }

  @GET
  @Path("/addCourse")
  public Response addCourse(@Context UriInfo uriInfo,
                             @QueryParam("courseTitle") String courseTitle,
                             @QueryParam("courseDescription") String courseDescription) throws Exception {
    CourseEntity course = new CourseEntity(courseTitle, courseDescription);
    courseDao.create(course);
    JSONObject courseJson = new JSONObject();
    courseJson.put("id", course.getCourseId());
    courseJson.put("title", course.getCourseTitle());
    courseJson.put("description", course.getCourseDescription());

    Logger LOGGER = Logger.getLogger("LOG");
    LOGGER.info("Added");
    
    return Response.created(uriInfo.getAbsolutePath())
                   .entity(courseJson.toString())
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.OK)
                   .build();
  }
  
  
  
  @GET
  @Path("/updateCourse/{courseId}")
  public Response updateCourse(@Context UriInfo uriInfo,
                                @PathParam("courseId") String courseId,
                                @QueryParam("courseTitle") String courseTitle,
                                @QueryParam("courseDescription") String courseDescription) throws Exception {
    CourseEntity course = courseDao.find(Long.parseLong(courseId));
    if (course != null) {
      course.setCourseTitle(courseTitle);
      course.setCourseDescription(courseDescription);
      courseDao.update(course);
      JSONObject courseJson = new JSONObject();
      courseJson.put("id", course.getCourseId());
      courseJson.put("title", course.getCourseTitle());
      courseJson.put("description", course.getCourseDescription());
      return Response.created(uriInfo.getAbsolutePath())
                     .entity(courseJson.toString())
                     .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                     .status(Response.Status.OK)
                     .build();
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity("CourseEntity " + courseId + " is not found")
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.NOT_FOUND)
                   .build();
  }
  
  
  @GET
  @Path("/deleteCourse/{courseId}")
  public Response deleteCourse(@Context UriInfo uriInfo, @PathParam("courseId") String courseId) throws Exception {
    CourseEntity course = courseDao.find(Long.parseLong(courseId));
    if (course != null) {
      JSONObject courseJson = new JSONObject();
      courseJson.put("id", course.getCourseId());
      courseJson.put("title", course.getCourseTitle());
      courseJson.put("description", course.getCourseDescription());
      courseDao.delete(course);
      return Response.created(uriInfo.getAbsolutePath())
                     .entity(courseJson.toString())
                     .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                     .status(Response.Status.OK)
                     .build();
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity("CourseEntity " + courseId + " is not found")
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.NOT_FOUND)
                   .build();
  }
  
  
  @GET
  @Path("/getAllCourses")
  public Response getAllCourses(@Context UriInfo uriInfo) throws Exception {
    List<CourseEntity> courses = courseDao.findAll();
    JSONArray response = new JSONArray();
    for (CourseEntity course : courses) {
      JSONObject courseJson = new JSONObject();
      courseJson.put("id", course.getCourseId());
      courseJson.put("title", course.getCourseTitle());
      courseJson.put("description", course.getCourseDescription());
      response.put(courseJson);
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity(response.toString())
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.OK)
                   .build();
  }
  
  
  @GET
  @Path("/getCourseById/{courseId}")
  public Response getCourseById(@Context UriInfo uriInfo, @PathParam("courseId") String courseId) throws Exception {
    CourseEntity course = courseDao.find(Long.parseLong(courseId));
    if (course != null) {
      JSONObject courseJson = new JSONObject();
      courseJson.put("id", course.getCourseId());
      courseJson.put("title", course.getCourseTitle());
      courseJson.put("description", course.getCourseDescription());
      return Response.created(uriInfo.getAbsolutePath())
                     .entity(courseJson.toString())
                     .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                     .status(Response.Status.OK)
                     .build();
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity("CourseEntity " + courseId + " is not found")
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.NOT_FOUND)
                   .build();
  }

}
