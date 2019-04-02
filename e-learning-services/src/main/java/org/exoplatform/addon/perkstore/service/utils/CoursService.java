package org.exoplatform.addon.perkstore.service.utils;

import org.exoplatform.addon.perkstore.dao.CoursDao;
import org.exoplatform.addon.perkstore.entity.Cours;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.Date;
import java.util.List;

public class CoursService{
  private              CoursDao coursDao;
  private static final Log      LOG = ExoLogger.getExoLogger(Cours.class);

  public CoursService(CoursDao coursDao) {
    this.coursDao = coursDao;
  }
 /*public Cours addCours(String libellecours,
                        Boolean visibiliteCours,
                        Date dateDebut,
                        Date dateFin,
                        int nbPersonne,
                        String Recompense){
    Cours addCours=null;
    if(!libellecours.equals("")&&(dateDebut.before(dateFin)||dateDebut.equals(dateFin))) {
      Cours cours = new Cours(libellecours, visibiliteCours, dateDebut,dateFin, nbPersonne, Recompense);
      try {
        addCours = coursDao.create(cours);
      } catch (Exception e) {
        LOG.error("Cannot Create the cours", e);
      }
    }
    return null;
  }*/
 public Cours addCours(Cours cr){
   LOG.info("add Cours Service");
   return coursDao.create(cr);
 }
  public List<Cours> getAllCours(){
    LOG.info("get All Cours Service");
    return coursDao.findAll();
  }
}
