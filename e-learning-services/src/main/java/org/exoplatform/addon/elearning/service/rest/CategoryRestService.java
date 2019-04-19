package org.exoplatform.addon.elearning.service.rest;

import org.exoplatform.addon.elearning.dto.CategoryDTO;

import org.exoplatform.addon.elearning.entity.Category;
import org.exoplatform.addon.elearning.entity.Course;
import org.exoplatform.addon.elearning.service.utils.CategoryService;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRestService implements ResourceContainer {
  @Inject
  private                CategoryService categoryService;
  private static final Log             LOG = ExoLogger.getExoLogger(Category.class);


  public CategoryRestService() {
    categoryService= CommonsUtils.getService(CategoryService.class);

  }

  @GET
  @Path("/all")
  public Response getAllCategories() {


    try {
      List<CategoryDTO> allCategories = categoryService.getAllCategories();

      return Response.ok(allCategories, MediaType.APPLICATION_JSON).build();

    } catch (Exception e) {

      LOG.error("Error listing all GamificationInformationsPortlet ", e);

      return Response.serverError()
                     .entity("Error listing all GamificationInformationsPortlet")
                     .build();
    }

  }
  @POST
  @Path("/add")
  public Response addCategory(CategoryDTO categoryDTO) {
    try {
      categoryDTO=categoryService.addCategory(categoryDTO);
      return Response.ok().entity(categoryDTO).build();


    } catch (Exception e) {
      return Response.serverError()
                     .entity("Error adding new category")
                     .build();
    }

  }
}
