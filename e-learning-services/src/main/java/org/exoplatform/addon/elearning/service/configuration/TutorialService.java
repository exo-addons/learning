package org.exoplatform.addon.elearning.service.configuration;

import java.util.List;
import java.sql.Timestamp;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;


import org.exoplatform.addon.elearning.storage.TutorialDao;
import org.exoplatform.services.rest.resource.ResourceContainer;

@Path("tuto")
@Produces(MediaType.APPLICATION_JSON)
public class TutorialService implements ResourceContainer {

  private TutorialDao tutorialDao;

  public TutorialService(TutorialDao tutorialDao) {
    this.tutorialDao = tutorialDao;
  }

  @GET
  @Path("/test")
  public String hello() {
    return "Hello";
  }
 /*
  @POST
  @Path("/addCourse")
  public Response addCourse(@Context UriInfo uriInfo,
                            @QueryParam("courseTitle") String courseTitle,
                            @QueryParam("courseDescription") String courseDescription,
                            @QueryParam("courseAuthor") Long courseAuthor,
                            @QueryParam("modifiedDate") Timestamp modifiedDate,
                            @QueryParam("lastModifiedDate") Timestamp lastModifiedDate,
                            @QueryParam("courseIllustration") Long courseIllustration,
                            @QueryParam("courseMediaType") String courseMediaType,
                            @QueryParam("courseLevel") String courseLevel,
                            @QueryParam("courseDuration") String courseDuration,
                            @QueryParam("courseStatus") String courseStatus) throws Exception {
    CourseEntity course = new CourseEntity(courseTitle,
                                           courseDescription,
                                           courseAuthor,
                                           modifiedDate,
                                           lastModifiedDate,
                                           courseIllustration,
                                           courseMediaType,
                                           courseLevel,
                                           courseDuration,
                                           courseStatus);
    courseDao.create(course);
    JSONObject courseJson = new JSONObject();
    courseJson.put("id", course.getCourseId());
    courseJson.put("title", course.getCourseTitle());
    courseJson.put("description", course.getCourseDescription());
    courseJson.put("author", course.getCourseAuthor());
    courseJson.put("crDate", course.getCreatedDate());
    courseJson.put("moDate", course.getModifiedDate());
    courseJson.put("lmoDate", course.getLastModifiedDate());
    courseJson.put("illustration", course.getCourseIllustration());
    courseJson.put("media", course.getCourseMediaType());
    courseJson.put("level", course.getCourseLevel());
    courseJson.put("duration", course.getCourseDuration());
    courseJson.put("status", course.getCourseStatus());

    return Response.created(uriInfo.getAbsolutePath())
                   .entity(courseJson.toString())
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.OK)
                   .build();
  }

  @PUT
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

  @DELETE
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
*/
}
