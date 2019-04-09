package org.exoplatform.addon.perkstore.service.rest;

import org.exoplatform.addon.perkstore.dto.CourseDTO;
import org.exoplatform.addon.perkstore.entity.Course;
import org.exoplatform.addon.perkstore.service.utils.CourseService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.InputStream;
import java.util.List;

@Path("cours")
@Produces(MediaType.APPLICATION_JSON)
public class CourseRestService implements ResourceContainer{
  private   CacheControl cacheControl;
  private static Log          log = ExoLogger.getLogger(CourseRestService.class);

  @Inject
  private CourseService courseService;
  private CourseDTO courseDTO;

  @Path("all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listCours() {
    List<CourseDTO> cours= courseService.getAllCourses();
    JSONArray jsonArray=new JSONArray();
    try {
      for (CourseDTO cr:cours) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id_course",cr.getIdCourse());
        jsonObject.put("name_course",cr.getNameCourse());
        jsonObject.put("visibility_course",cr.getVisibilityCourse());
        jsonObject.put("date_start",cr.getDateStart().toString());
        jsonObject.put("date_end",cr.getDateEnd().toString());
        jsonObject.put("nb_person",cr.getNbPerson());
        jsonObject.put("reward_course",cr.getRewardCourse());
        jsonObject.put("category_course",cr.getCategory().getIdCategory());
        jsonArray.add(jsonObject);
      }

    }catch (Exception e){
      log.error(e.getMessage(),e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                     .entity("An internal error has occurred When trying to import courses list")
                     .build();
    }
    return Response.ok(jsonArray.toString(), MediaType.APPLICATION_JSON).build();

  }
/*
  @Path("save")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveCourse(Course cr){

    try {
      courseService.addCourse(cr);
      log.info("Saving product");
    }
    catch(Exception e){
      log.error("Error saving product", e);
      return Response.status(500).build();
    }
    return Response.ok().build();

  }

*/
  @GET
  @Path("/allCourses")
  public Response getAllCourses(@Context UriInfo uriInfo) {

    ConversationState conversationState = ConversationState.getCurrent();

    if (conversationState != null) {

      try {
        List<CourseDTO> allCourses = courseService.getAllCourses();

        return Response.ok(allCourses, MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();

      } catch (Exception e) {

        log.error("Error listing all Courses ", e);

        return Response.serverError()
                       .cacheControl(cacheControl)
                       .entity("Error listing all Courses")
                       .build();
      }

    } else {
      return Response.status(Response.Status.UNAUTHORIZED)
                     .cacheControl(cacheControl)
                     .entity("Unauthorized user")
                     .build();
    }

  }


  @POST
  @Path("/add")
  public Response addCourse(CourseDTO courseDTO) {

      try {
        courseDTO=courseService.addCourse(courseDTO);
        return Response.ok().cacheControl(cacheControl).entity(courseDTO).build();


      } catch (Exception e) {

        //LOG.error("Error adding new badge {} by {} ", badgeDTO.getTitle(), currentUserName, e);

        return Response.serverError()
                       .cacheControl(cacheControl)
                       .entity("Error adding new badge")
                       .build();

      }finally{

      return Response.status(Response.Status.UNAUTHORIZED)
                     .cacheControl(cacheControl)
                     .entity("Unauthorized user")
                     .build();
    }

  }


}
