package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.ExamService;
import org.exoplatform.addon.elearning.service.dto.ExamDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.social.core.manager.IdentityManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("exam")
@Produces(MediaType.APPLICATION_JSON)
public class ExamRestService implements ResourceContainer {
  protected      IdentityManager identityManager = null;
  private static Log             LOG             = ExoLogger.getLogger(ExamRestService.class);

  @Inject
  private ExamService examService;

  public ExamRestService() {
    examService = CommonsUtils.getService(ExamService.class);
    identityManager = CommonsUtils.getService(IdentityManager.class);
  }


  @GET
  @Path("/all")
  public Response getAllExams() {
    try {
      List<ExamDTO> allExams = examService.getAllExams();

      return Response.ok(allExams, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all Exams ", e);

      return Response.serverError()
                     .entity("Error listing all Exams")
                     .build();
    }
  }

  @POST
  @Path("/add")
  public Response addExam(ExamDTO examDTO) {
    try {
      examDTO=examService.addExam(examDTO);
      return Response.ok().entity(examDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new exam")
                     .build();
    }

  }
}
