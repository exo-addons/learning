package org.exoplatform.addon.elearning.rest;

import org.exoplatform.addon.elearning.entities.TutorialEntity;
import org.exoplatform.addon.elearning.service.configuration.TutorialService;
import org.exoplatform.addon.elearning.service.dto.Tutorial;
import org.exoplatform.addon.elearning.storage.TutorialDao;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("tuto")
@Produces(MediaType.APPLICATION_JSON)
public class TutorialServiceRest implements ResourceContainer {

    private TutorialService tutorialService;

    public TutorialServiceRest(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @POST
    @Path("/addTuto")
    public Response addTutorial(/* @Context UriInfo uriInfo, */
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("authorId") Long authorId,
            @FormParam("status") String status) throws Exception {
        Tutorial tuto = new Tutorial(title, description, authorId, status);
        tutorialService.createTutorial(tuto);
        JSONObject tutoJson = new JSONObject();
        tutoJson.put("id", tuto.getId());
        tutoJson.put("title", tuto.getTitle());
        tutoJson.put("description", tuto.getDescription());
        tutoJson.put("authorId", tuto.getAuthorId());
        tutoJson.put("createdDate", tuto.getCreatedDate());
        tutoJson.put("status", tuto.getStatus());
        return Response.status(Response.Status.OK).entity(tutoJson.toString()).build();
    }

    @PUT
    @Path("/updateTuto/{id}")
    public Response updateTuto(@Context UriInfo uriInfo,
                               @PathParam("id") String id,
                               @FormParam("title") String title,
                               @FormParam("description") String description,
                               @FormParam("status") String status) throws Exception {
        TutorialEntity tuto = tutorialDao.find(Long.parseLong(id));
        if (tuto != null) {
            tuto.setTitle(title);
            tuto.setDescription(description);
            tuto.setStatus(status);
            tutorialDao.update(tuto);
            JSONObject tutoJson = new JSONObject();
            tutoJson.put("id", tuto.getId());
            tutoJson.put("title", tuto.getTitle());
            tutoJson.put("description", tuto.getDescription());
            tutoJson.put("authorId", tuto.getAuthorId());
            tutoJson.put("createdDate", tuto.getCreatedDate());
            tutoJson.put("status", tuto.getStatus());
            return Response.status(Response.Status.OK).entity(tutoJson.toString()).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No Tutorial found with id " + id).build();
    }

    @DELETE
    @Path("/deleteTuto/{id}")
    public Response deleteTuto(@Context UriInfo uriInfo, @PathParam("id") String id) throws Exception {
        TutorialEntity tuto = tutorialDao.find(Long.parseLong(id));
        if (tuto != null) {
            tutorialDao.delete(tuto);
            return Response.status(Response.Status.OK).entity("Tutorial with id " + id + " Deleted").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No Tutorial found with id " + id).build();
    }

    @GET
    @Path("/getAllTutos")
    public Response getAllTutos(/* @Context UriInfo uriInfo */) throws Exception {
        List<TutorialEntity> tutos = tutorialDao.findAll();
        JSONArray response = new JSONArray();
        for (TutorialEntity tuto : tutos) {
            JSONObject tutoJson = new JSONObject();
            tutoJson.put("id", tuto.getId());
            tutoJson.put("title", tuto.getTitle());
            tutoJson.put("description", tuto.getDescription());
            tutoJson.put("authorId", tuto.getAuthorId());
            tutoJson.put("createdDate", tuto.getCreatedDate());
            tutoJson.put("status", tuto.getStatus());
            response.put(tutoJson);
        }
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    @GET
    @Path("/getTutoById/{id}")
    public Response getTutoById(@Context UriInfo uriInfo, @PathParam("id") String id) throws Exception {
        TutorialEntity tuto = tutorialDao.find(Long.parseLong(id));
        if (tuto != null) {
            JSONObject tutoJson = new JSONObject();
            tutoJson.put("id", tuto.getId());
            tutoJson.put("title", tuto.getTitle());
            tutoJson.put("description", tuto.getDescription());
            tutoJson.put("authorId", tuto.getAuthorId());
            tutoJson.put("createdDate", tuto.getCreatedDate());
            tutoJson.put("status", tuto.getStatus());
            return Response.status(Response.Status.OK).entity(tutoJson.toString()).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No Tutorial found with id " + id).build();
    }

}
