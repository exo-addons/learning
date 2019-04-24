package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@ExoEntity
@Table(name = "ELEARNING_COURSE")

/*@NamedQueries({

    @NamedQuery(
        name = "CourseEntity.getCategoryCours",
        query = "SELECT c.idCategory FROM CourseEntity cr,CategoryEntity c where c.idCategory = cr.idCourse AND cr.idCourse:=id"
    )
})*/
public class CourseEntity {

  public enum Status{
    DRAFTED,
    COMPLETED,
    PUBLISHED,
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_COURSE")
  private Long                     idCourse;
  @Column(name ="NAME_COURSE")
  private String         NameCourse;
  @Column(name ="VISIBILITY_COURSE")
  private Boolean        visibiltyCourse;
  @Column(name ="DATE_START")
  private Date           dateStart;
  @Column(name ="DATE_END")
  private Date           dateEnd;
  @Column(name ="NB_PERSON")
  private int            nbPerson;
  @Column(name ="REWARD_COURSE")
  private String         rewardCourse;
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private CategoryEntity category;
  @Enumerated(EnumType.STRING)
  private Status status;

  public CourseEntity() {
  }

  public CourseEntity(String nameCourse,
                      Boolean visibiltyCourse,
                      Date dateStart,
                      Date dateEnd,
                      int nbPerson,
                      String rewardCourse, CategoryEntity category) {
    this.NameCourse = nameCourse;
    this.visibiltyCourse = visibiltyCourse;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.nbPerson = nbPerson;
    this.rewardCourse = rewardCourse;
    this.category = category;
  }

  public Long getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(Long idCourse) {
    this.idCourse = idCourse;
  }

  public String getNameCourse() {
    return NameCourse;
  }

  public void setNameCourse(String nameCourse) {
    NameCourse = nameCourse;
  }

  public Boolean getVisibiltyCourse() {
    return visibiltyCourse;
  }

  public void setVisibiltyCourse(Boolean visibiltyCourse) {
    this.visibiltyCourse = visibiltyCourse;
  }

  public Date getDateStart() {
    return dateStart;
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  public Date getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  public int getNbPerson() {
    return nbPerson;
  }

  public void setNbPerson(int nbPerson) {
    this.nbPerson = nbPerson;
  }

  public String getRewardCourse() {
    return rewardCourse;
  }

  public void setRewardCourse(String rewardCourse) {
    this.rewardCourse = rewardCourse;
  }
  public CategoryEntity getCategory() {
    return category;
  }

  public void setCategory(CategoryEntity category) {
    this.category = category;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
