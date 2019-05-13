package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.entities.CourseEntity;
import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.addon.elearning.service.configuration.CourseService;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.commons.file.services.FileService;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.upload.UploadService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

  @Path("cours")
  @Produces(MediaType.APPLICATION_JSON)
  public class CourseRestService implements ResourceContainer {
    protected IdentityManager identityManager = null;

    private static Log LOG = ExoLogger.getLogger(CourseRestService.class);

    private static final String DEFAULT_COURSE_ICON_NAME = "DEFAULT_COURSE_ICON";

    private static final String DEFAULT_COURSE_ICON_MIME_TYPE = "image/png";

    private static final String DEFAULT_COURSE_ICON_NAMESPACE = "elearning";

    @Inject
    private CourseService courseService;

    protected FileService fileService = null;

    protected UploadService uploadService = null;

    public CourseRestService() {
      courseService = CommonsUtils.getService(CourseService.class);
      identityManager = CommonsUtils.getService(IdentityManager.class);
      fileService = CommonsUtils.getService(FileService.class);
      uploadService = CommonsUtils.getService(UploadService.class);

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
        String user= ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allOtherPublishedCourse = courseService.getOtherPublishedCourse(PUBLISHED,user);
        return Response.ok(allOtherPublishedCourse, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all other published course", e);

        return Response.serverError()
                       .entity("Error listing all other published course")
                       .build();
      }
    }
    @GET
    @Path("/allCompletedByUser/{COMPLETED}")
    public Response getCompletedCourseByUser(@PathParam("COMPLETED") CourseEntity.Status COMPLETED) {
      try {
        String user= ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allCourseCompletedByUser = courseService.getCompletedCourseByUser(COMPLETED,user);
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
        String user= ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allCoursePublishedByUser = courseService.getCompletedCourseByUser(PUBLISHED,user);
        return Response.ok(allCoursePublishedByUser, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all Course Published by user ", e);

        return Response.serverError()
                       .entity("Error listing all Course Published user")
                       .build();
      }
    }

    @GET
    @Path("/allDrafetByUser/{DRAFET}")
    public Response getDrafetCourseByUser(@PathParam("DRAFET") CourseEntity.Status DRAFET) {
      try {
        String user= ConversationState.getCurrent().getIdentity().getUserId();
        List<CourseDTO> allCourseDrafetByUser = courseService.getDrafetCourseByUser(DRAFET,user);
        return Response.ok(allCourseDrafetByUser, MediaType.APPLICATION_JSON).build();

      } catch (Exception e) {

        LOG.error("Error listing all Course Drafet by user ", e);

        return Response.serverError()
                       .entity("Error listing all Course drafet user")
                       .build();
      }
    }
    @DELETE
    @Path("/delete/{idCourse}")
    public Response deleteCourseById( @PathParam("idCourse") Long idCourse) {

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

      try {

        /** END upload */

        //--- Add Course
        coursDTO = courseService.addCours(coursDTO);

        return Response.ok().entity(coursDTO).build();

      } catch (Exception e) {

        LOG.error("Error adding new course {} by {} ", coursDTO.getNameCourse(), e);

        return Response.serverError()
                       .entity("Error adding new course")
                       .build();

      }

    }

  }
