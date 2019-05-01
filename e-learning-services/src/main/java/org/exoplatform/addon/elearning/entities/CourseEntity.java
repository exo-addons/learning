package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "ELearningCourse")
@ExoEntity
@Table(name = "ELEARNING_COURSE")
@NamedQueries({
    @NamedQuery(
        name = "ELearningCourse.findCourseByName",
        query = "SELECT course FROM ELearningCourse course where course.NameCourse = :courseName"
    ),
    @NamedQuery(
        name = "ELearningCourse.getCompletedCourseByUser",
        query = "SELECT course FROM ELearningCourse course where course.status = :COMPLETED and course.userName=:user"
    ),
    @NamedQuery(
        name = "ELearningCourse.getDraftedCourse",
        query = "SELECT course FROM ELearningCourse course where course.status = :DRAFTED and course.userName=:user"
    ),
    @NamedQuery(
        name = "ELearningCourse.getPublishedCourse",
        query = "SELECT course FROM ELearningCourse course where course.status = :PUBLISHED and course.userName=:user"
    ),
    @NamedQuery(
        name = "ELearningCourse.deleteCourseById",
        query = "DELETE FROM ELearningCourse course WHERE course.idCourse = :courseId "
    )
})

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
  private Date                     dateEnd;
  @Column(name ="NB_PERSON")
  private int                      nbPerson;
  @Column(name ="REWARD_COURSE")
  private String                   rewardCourse;
  @Column(name ="USERNAME_COURSE")
  private String                   userName;
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private CategoryEntity           category;
  @Enumerated(EnumType.STRING)
  private Status                   status;
  @Column(name="ICON_FILE_ID")
  private long                     iconFileId;
  @OneToMany(mappedBy="course",fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
  private Collection<CourseRegistrationEntity> coursesRegistrations;

  public CourseEntity() {
  }

  public CourseEntity(String nameCourse,
                      Boolean visibiltyCourse,
                      Date dateStart,
                      Date dateEnd,
                      int nbPerson,
                      String rewardCourse,
                      String userName,
                      CategoryEntity category,
                      Status status, long iconFileId) {
    NameCourse = nameCourse;
    this.visibiltyCourse = visibiltyCourse;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.nbPerson = nbPerson;
    this.rewardCourse = rewardCourse;
    this.userName = userName;
    this.category = category;
    this.status = status;
    this.iconFileId = iconFileId;
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

  public long getIconFileId() {
    return iconFileId;
  }

  public void setIconFileId(long iconFileId) {
    this.iconFileId = iconFileId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
