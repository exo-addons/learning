package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.service.configuration.WorkerService;
import org.exoplatform.addon.elearning.service.dto.WorkerDTO;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("worker")
@Produces(MediaType.APPLICATION_JSON)
public class WorkerRestService  implements ResourceContainer {
  private static Log LOG = ExoLogger.getLogger(ExamRestService.class);

  @Inject
  private WorkerService workerService;

  public WorkerRestService() {
    workerService= CommonsUtils.getService(WorkerService.class);
  }

  @GET
  @Path("/all")
  public Response getAllWorkers() {
    try {
      List<WorkerDTO> allWorkers = workerService.getAllWorkers();

      return Response.ok(allWorkers, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all Workers ", e);

      return Response.serverError()
                     .entity("Error listing all Workers")
                     .build();
    }
  }
  @POST
  @Path("/add")
  public Response addWorker(WorkerDTO workerDTO) {
    try {
      workerDTO=workerService.addWorker(workerDTO);
      return Response.ok().entity(workerDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new worker")
                     .build();
    }

  }

  @POST
  @Path("/addNewUser")
  public Response addNewWorker(WorkerDTO workerDTO) {
    try {
      workerDTO=workerService.insertNewWorker(workerDTO);
      return Response.ok().entity(workerDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new worker because he is exist")
                     .build();
    }

  }

}
