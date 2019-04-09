package org.exoplatform.addon.perkstore.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@ExoEntity
@Table(name = "ELEARNING_COURSE")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_COURSE")
  private Long                     idCourse;
  @Column(name ="NAME_COURSE")
  private String                   NameCourse;
  @Column(name ="VISIBILITY_COURSE")
  private Boolean                  visibiltyCourse;
  @Column(name ="DATE_START")
  private Date                     dateStart;
  @Column(name ="DATE_END")
  private Date                     dateEnd;
  @Column(name ="NB_PERSON")
  private int                      nbPerson;
  @Column(name ="REWARD_COURSE")
  private String                   rewardCourse;
  @OneToMany(cascade=CascadeType.ALL,mappedBy = "course")
  private List<CourseRegistration> registrations;
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private Category                 category;

  public Course() {
  }

  public Course(String nameCourse,
                Boolean visibiltyCourse,
                Date dateStart,
                Date dateEnd,
                int nbPerson,
                String rewardCourse,
                Category category) {
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

  public List<CourseRegistration> getRegistrations() {
    return registrations;
  }

  public void setRegistrations(List<CourseRegistration> registrations) {
    this.registrations = registrations;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
