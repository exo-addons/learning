package org.exoplatform.addon.elearning.service.setting.course.model;


import org.exoplatform.container.component.BaseComponentPlugin;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.container.xml.ValueParam;


public class CourseConfig extends BaseComponentPlugin {

  private String icon;
  private String courseName;

  public CourseConfig() {
  }

  public CourseConfig(InitParams params) throws Exception {
    ValueParam iconParam = params.getValueParam("course-icon");
    if (iconParam != null) {
      icon = iconParam.getValue();
    }
    ValueParam courseParam = params.getValueParam("course-name");
    if (iconParam != null) {
      courseName = courseParam.getValue();
    }

  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
}
