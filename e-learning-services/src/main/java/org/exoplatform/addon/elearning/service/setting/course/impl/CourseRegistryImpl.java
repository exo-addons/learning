package org.exoplatform.addon.elearning.service.setting.course.impl;

import org.exoplatform.addon.elearning.service.configuration.CourseService;
import org.exoplatform.addon.elearning.service.dto.CourseDTO;
import org.exoplatform.addon.elearning.service.setting.course.CourseRegistry;
import org.exoplatform.addon.elearning.service.setting.course.model.CourseConfig;
import org.exoplatform.commons.file.model.FileItem;
import org.exoplatform.commons.file.services.FileService;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.picocontainer.Startable;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CourseRegistryImpl implements Startable, CourseRegistry {
  private static final Log                       LOG = ExoLogger.getLogger(CourseRegistryImpl.class);
  private        Map<String, CourseConfig> courseMap;

  private CourseService courseService;
  private FileService   fileService;

  public CourseRegistryImpl() {
  }

  public CourseRegistryImpl(FileService fileService) {
    this.courseMap=new HashMap<String,CourseConfig>();
    this.fileService = fileService;
  }

  @Override
  public void addPlugin(CourseConfig course) {
    courseMap.put(course.getCourseName(),course);
  }

  @Override
  public boolean remove(CourseConfig course) {
    courseMap.remove(course.getCourseName());
    return true;  }

  @Override
  public void start() {
    courseService = CommonsUtils.getService(CourseService.class);
    try {
      // Processing registered rules

      for (CourseConfig course : courseMap.values()) {
         CourseDTO courseDTO =courseService.findCourseByName(course.getCourseName());

        if (courseDTO == null) {
          store(course);
        }

      }


    } catch (Exception e) {
      LOG.error("Error when processing Rules ", e);
    }

  }

  private void store(CourseConfig courseConfig) {
    CourseDTO courseDTO=new CourseDTO();
    courseDTO.setNameCourse(courseConfig.getCourseName());
    courseDTO.setIconFileId(storeIcon(courseConfig.getIcon()));
    courseService.addCours(courseDTO);
  }

  private long storeIcon (String iconTitle) {
    InputStream inputStream;

    /** Upload badge's icon into DB */
    FileItem fileItem = null;

    long iconFiledId = 0;
    try {

      // Load icone's binary
      inputStream = CourseRegistryImpl.class.getClassLoader().getResourceAsStream("medias/images/"+iconTitle);

      fileItem = new FileItem(null,
                              iconTitle,
                              "image/png",
                              "gamification",
                              inputStream.available(),
                              new Date(),
                              "hassen",
                              false,
                              inputStream);

      fileItem = fileService.writeFile(fileItem);

      iconFiledId = fileItem.getFileInfo().getId();

    } catch (Exception e) {

      LOG.error("Enable to inject icon for Course {} ",iconTitle, e);

    }

    return iconFiledId;



  }


  @Override
  public void stop() {

  }
}
