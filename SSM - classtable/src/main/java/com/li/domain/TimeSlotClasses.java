package com.li.domain;

import java.util.HashMap;
import java.util.Map;

public class TimeSlotClasses {
    Map<String,Teacher> teacherMap =new HashMap<String, Teacher>();

    public Map<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }
}
