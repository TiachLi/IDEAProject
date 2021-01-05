package com.li.domain;

import java.util.Arrays;

public class ClassTimeTable {
    Course course[]=new Course[4];
  public   class Course{
        private String name;
        private String subject;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "name='" + name + '\'' +
                    ", subject='" + subject + '\'' +
                    '}';
        }
    }

    public Course[] getCourse() {
        return course;
    }

    public void setCourse(Course[] course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ClassTimeTable{" +
                "course=" + Arrays.toString(course) +
                '}';
    }
}
