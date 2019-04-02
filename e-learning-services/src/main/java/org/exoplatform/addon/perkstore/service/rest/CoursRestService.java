package org.exoplatform.addon.perkstore.service.rest;

import org.exoplatform.addon.perkstore.entity.Cours;
import org.exoplatform.addon.perkstore.service.utils.CoursService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("cours")
@Produces(MediaType.APPLICATION_JSON)
public class CoursRestService implements ResourceContainer{
  private static Log log = ExoLogger.getLogger(CoursRestService.class);

  @Inject
  private CoursService coursService;

  @Path("all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Cours> listUsers() {
    return coursService.getAllCours();
  }

  @Path("save")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Cours saveCours(Cours cr){
    return coursService.addCours(cr);

  }

}
