package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.addon.elearning.service.configuration.CourseService;
import org.exoplatform.commons.file.model.FileInfo;
import org.exoplatform.commons.file.services.FileStorageException;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

  @Path("cours")
  @Produces(MediaType.APPLICATION_JSON)
  public class CourseRestService implements ResourceContainer {
    protected IdentityManager identityManager = null;

    private static Log LOG = ExoLogger.getLogger(CourseRestService.class);


    @Inject
    private CourseService courseService;


    public CourseRestService() {
      courseService = CommonsUtils.getService(CourseService.class);
      identityManager = CommonsUtils.getService(IdentityManager.class);

    }

    @GET
    @Path("/getCourse/{nameCourse}")
    public Response findCourseByName(@PathParam("nameCourse") String courseName) {
      try {
        CourseDTO courseDTO = courseService.findCourseByName(courseName);

        return Response.ok(courseDTO, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing the Course By name ", e);

        return Response.serverError()
                       .entity("Error listing Error listing the Course By name")
                       .build();
      }
    }

    @GET
    @Path("/all")
    public Response getAllCourses() {
      try {
        List<CourseDTO> allCourses = courseService.getAllCourses();

        return Response.ok(allCourses, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all Courses ", e);

        return Response.serverError()
                       .entity("Error listing all Courses")
                       .build();
      }
    }

    @GET
    @Path("/allOtherPublishedCourse/{PUBLISHED}")
    public Response getOtherPublishedCourse(@PathParam("PUBLISHED") CourseEntity.Status PUBLISHED) {
      try {
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allOtherPublishedCourse = courseService.getOtherPublishedCourse(PUBLISHED, user);
        return Response.ok(allOtherPublishedCourse, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all other published course", e);

        return Response.serverError()
                       .entity("Error listing all other published course")
                       .build();
      }
    }

    @GET
    @Path("/allArchivedByUser/{ARCHIVED}")
    public Response getArchivedCourseByUser(@PathParam("ARCHIVED") CourseEntity.Status ARCHIVED) {
      try {
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allCourseCompletedByUser = courseService.getArchivedCourseByUser(ARCHIVED, user);
        return Response.ok(allCourseCompletedByUser, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all Course Completed by user ", e);

        return Response.serverError()
                       .entity("Error listing all Course Completed user")
                       .build();
      }
    }

    @GET
    @Path("/allPublishedByUser/{PUBLISHED}")
    public Response getPublishedCourseByUser(@PathParam("PUBLISHED") CourseEntity.Status PUBLISHED) {
      try {
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allCoursePublishedByUser = courseService.getPublishedCourseByUser(PUBLISHED, user);
        return Response.ok(allCoursePublishedByUser, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all Course Published by user ", e);

        return Response.serverError()
                       .entity("Error listing all Course Published user")
                       .build();
      }
    }

    @GET
    @Path("/allDraftByUser/{DRAFT}")
    public Response getDraftCourseByUser(@PathParam("DRAFT") CourseEntity.Status DRAFT) {
      try {
        String user = ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allCourseDrafetByUser = courseService.getDraftCourseByUser(DRAFT, user);
        return Response.ok(allCourseDrafetByUser, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all Course Draft by user ", e);

        return Response.serverError()
                       .entity("Error listing all Course draft user")
                       .build();
      }
    }

    @DELETE
    @Path("/delete/{idCourse}")
    public Response deleteCourseById(@PathParam("idCourse") Long idCourse) {

      try {
        //--- Remove the course
        courseService.deleteCourseById(idCourse);

        return Response.ok("Deleted ", MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error deleting course ", e);

        return Response.serverError()
                       .entity("Error deleting course")
                       .build();
      }

    }

    @POST
    @Path("/add")
    public Response add(CourseDTO coursDTO) {
      try{
          coursDTO = courseService.addCours(coursDTO);

          return Response.ok().entity(coursDTO).build();
      } catch (Exception e) {

        LOG.error("Error adding new course {} by {} ", coursDTO.getNameCourse(), e);

        return Response.serverError()
                       .entity("Error adding new course")
                       .build();

      }

    }
    @PUT
    @Path("/update")
    public Response updateCourse(CourseDTO courseDTO) {

      try {
        courseDTO = courseService.updateCourse(courseDTO);
        return Response.ok().entity(courseDTO).build();
      } catch (Exception e) {
        LOG.error("Error updating course {} by {} ", courseDTO.getNameCourse(), e);
        return Response.serverError()
                       .entity("Error updating a course")
                       .build();
      }

    }

  }
