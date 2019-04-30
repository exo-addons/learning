package org.exoplatform.addon.elearning.service.setting.course;

import org.exoplatform.addon.elearning.service.setting.course.model.CourseConfig;

public interface CourseRegistry {
  void addPlugin(CourseConfig badge);
  boolean remove(CourseConfig badgeConfig);
}
