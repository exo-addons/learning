package org.exoplatform.addon.elearning.entities;

import org.exoplatform.commons.api.persistence.ExoEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name="ElearningExam")
@ExoEntity
@Table(name = "ELEARNING_EXAM")
@NamedQueries({
    @NamedQuery(
        //it is a function to search course by it id
        name = "ElearningExam.findExamById",
        query = "SELECT exam FROM ElearningExam exam where exam.idExam=:id"
    ),
    @NamedQuery(
        //it is a function to search course by it id
        name = "ElearningExam.findExamByUserName",
        query = "SELECT exam FROM ElearningExam exam where exam.userName=:user"
    ),

})
    public class ExamEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="ID_EXAM")
  private Long                                 idExam;
  @Column(name ="NAME_EXAM")
  private String                               nameExam;
  @Column(name ="DATE_START_EXAM")
  private Date                                 dateStartExam;
  @Column(name ="DATE_END_EXAM")
  private Date                                 dateEndExam;
  @Column(name ="NB_BID_EXAM")
  private Long                                 nbBidExam;
  @Column(name = "REWARD_EXAM")
  private String                               rewardExam;
  @Column(name ="USERNAME_EXAM")
  private String                               userName;
  @OneToMany(mappedBy="exam",fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  private Collection<ExerciseEntity> exercises;
  @ManyToOne
  @JoinColumn(name = "COURSE_ID")
  private CourseEntity course;


  public ExamEntity() {
  }

  public ExamEntity(String nameExam,
                    Date dateStartExam,
                    Date dateEndExam,
                    Long nbBidExam,
                    String rewardExam,
                    String userName, CourseEntity course) {
    this.nameExam = nameExam;
    this.dateStartExam = dateStartExam;
    this.dateEndExam = dateEndExam;
    this.nbBidExam = nbBidExam;
    this.rewardExam = rewardExam;
    this.userName = userName;
    this.course = course;
  }

  public Long getIdExam() {
    return idExam;
  }

  public void setIdExam(Long idExam) {
    this.idExam = idExam;
  }

  public String getNameExam() {
    return nameExam;
  }

  public void setNameExam(String nameExam) {
    this.nameExam = nameExam;
  }

  public Date getDateStartExam() {
    return dateStartExam;
  }

  public void setDateStartExam(Date dateStartExam) {
    this.dateStartExam = dateStartExam;
  }

  public Date getDateEndExam() {
    return dateEndExam;
  }

  public void setDateEndExam(Date dateEndExam) {
    this.dateEndExam = dateEndExam;
  }

  public Long getNbBidExam() {
    return nbBidExam;
  }

  public void setNbBidExam(Long nbBidExam) {
    this.nbBidExam = nbBidExam;
  }

  public String getRewardExam() {
    return rewardExam;
  }

  public void setRewardExam(String rewardExam) {
    this.rewardExam = rewardExam;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public CourseEntity getCourse() {
    return course;
  }

  public void setCourse(CourseEntity course) {
    this.course = course;
  }
}
