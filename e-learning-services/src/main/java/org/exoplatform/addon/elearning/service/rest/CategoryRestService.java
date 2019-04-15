package org.exoplatform.addon.elearning.service.rest;

import org.exoplatform.addon.elearning.dto.CategoryDTO;

import org.exoplatform.addon.elearning.entity.Category;
import org.exoplatform.addon.elearning.entity.Course;
import org.exoplatform.addon.elearning.service.utils.CategoryService;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRestService implements ResourceContainer {
  @Inject
  private CategoryService categoryService;
  @POST
  @Path("/addCategory")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Category addCategory(CategoryDTO category){
    return categoryService.addCategory(category);
  }
  @GET
  @Path("/allCatgories")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<CategoryDTO> getAllCategories(){
    return categoryService.getAllCategoryDtos();
  }
}
