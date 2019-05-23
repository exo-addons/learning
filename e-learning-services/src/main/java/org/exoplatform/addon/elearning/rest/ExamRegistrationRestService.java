package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.ExamRegistrationService;
import org.exoplatform.addon.elearning.service.dto.CourseRegistrationDTO;
import org.exoplatform.addon.elearning.service.dto.ExamRegistrationDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.social.core.manager.IdentityManager;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("eregistration")
@Produces(MediaType.APPLICATION_JSON)
public class ExamRegistrationRestService implements ResourceContainer {
  protected IdentityManager identityManager = null;

  private static Log LOG = ExoLogger.getLogger(ExamRegistrationService.class);

  @Inject
  private ExamRegistrationService examService;

  public ExamRegistrationRestService() {
    identityManager = CommonsUtils.getService(IdentityManager.class);
  }

  @GET
  @Path("/all")
  public Response getAllExamsRegitered() {


    try {
      String nameWorker = ConversationState.getCurrent().getIdentity().getUserId();

      List<ExamRegistrationDTO> allExams = examService.getAllExamsRegitered(nameWorker);

      return Response.ok(allExams, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all Exams registration  ", e);

      return Response.serverError()
                     .entity("Error listing all Exams registration")
                     .build();
    }
  }

  @POST
  @Path("/add")
  public Response addRegistredExam(ExamRegistrationDTO examRegistrationDTO) {
    try {
      examRegistrationDTO=examService.addExam(examRegistrationDTO);
      return Response.ok().entity(examRegistrationDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new exam registration")
                     .build();
    }

  }

  @GET
  @Path("/countNumberWorker/{id}")
  public Response getNumberWorker(@PathParam("id")Long id)
  {
    try {
      String count = examService.getNumberWorker(id).toString();
      JSONObject jo = new JSONObject();
      jo.put("counter_worker",count);
      return Response.ok(jo).build();

    } catch(Exception e) {
      LOG.error("Erreur to count worker"+e.getMessage());
      return  Response.serverError()
                      .entity("Error counting")
                      .build();
    }
  }

}
