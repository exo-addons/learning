package org.exoplatform.addon.perkstore.service.rest;

import org.exoplatform.addon.perkstore.entity.Course;
import org.exoplatform.addon.perkstore.service.utils.CourseService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cours")
@Produces(MediaType.APPLICATION_JSON)
public class CourseRestService implements ResourceContainer{

  private static Log log = ExoLogger.getLogger(CourseRestService.class);

  @Inject
  private CourseService courseService;

  @Path("all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listCours() {
    List<Course> cours= courseService.getAllCourse();
    JSONArray jsonArray=new JSONArray();
    try {
      for (Course cr:cours) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id_course",cr.getIdCourse());
        jsonObject.put("name_course",cr.getNameCourse());
        jsonObject.put("visibility_course",cr.getVisibiltyCourse());
        jsonObject.put("date_start",cr.getDateStart());
        jsonObject.put("date_end",cr.getDateEnd());
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

}
