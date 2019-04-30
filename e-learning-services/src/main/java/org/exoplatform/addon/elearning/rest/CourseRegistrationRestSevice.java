package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.CourseRegistrationService;
import org.exoplatform.addon.elearning.service.dto.CourseRegistrationDTO;
import org.exoplatform.addon.elearning.service.mapper.CourseRegistrationMapper;
import org.exoplatform.addon.elearning.storage.CourseRegistrationDao;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cregistration")
@Produces(MediaType.APPLICATION_JSON)
public class CourseRegistrationRestSevice implements ResourceContainer {
  protected IdentityManager identityManager = null;

  private static Log LOG = ExoLogger.getLogger(CourseRegistrationService.class);

  @Inject
  private CourseRegistrationService courseService;

  public CourseRegistrationRestSevice() {
    identityManager = CommonsUtils.getService(IdentityManager.class);
  }

  @GET
  @Path("/CoursesRegitered")
  public Response getAllCoursesRegitered() {


    try {
      String nameWorker = ConversationState.getCurrent().getIdentity().getUserId();

      List<CourseRegistrationDTO> allCourses = courseService.getAllCoursesRegitered(nameWorker);

      return Response.ok(allCourses, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all GamificationInformationsPortlet ", e);

      return Response.serverError()
                     .entity("Error listing all GamificationInformationsPortlet")
                     .build();
    }
  }


}
