package com.li.service;

import com.li.domain.ClassTimeTable;
import com.li.domain.Teacher;
import com.li.domain.TimeSlotClasses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

public class ClassTable {
    //每一个时间段对应的课程集合
    Map<Integer, TimeSlotClasses> timeMap=new HashMap<Integer, TimeSlotClasses>();
    //每一个年级的时间表
    Map<String,ClassTimeTable> timeSlot =new HashMap<String,ClassTimeTable>();


    public void arrayClasses(List<Teacher> teachers){

        boolean classExist=false;
        boolean timeExist=false;
        boolean tempClassExist=true;
        //遍历所有的老师
        for (Teacher teacher : teachers) {
            //得到老师所教年级
            String[] grades = teacher.getGrades();
            //得到老师所教科目
            String[] subject = teacher.getSubject();
            //遍历年级
            for (String grade : grades) {
                //得到每个年级的时间表
                ClassTimeTable classTimeTable = timeSlot.get(grade);
                //如果是二年级，要么只有上午要么只有下午
                if (grade.equals("2")){
                    return;
                }
                 //得到课程表数组
                if (classTimeTable==null){
                    ClassTimeTable tempClassTimeTable=new ClassTimeTable();
                    tempClassTimeTable.setCourse(new ClassTimeTable.Course[4]);
                    classTimeTable=tempClassTimeTable;
                }
                ClassTimeTable.Course[] course = classTimeTable.getCourse();
                //遍历课程表
                for (int i = 0; i <4 ; i++) {
                    //如果这节没课并且这个科目今天没上过并且这个时间段没有相同的老师
                    if (course[i]==null){
                        TimeSlotClasses timeSlotClasses = timeMap.get(i);
                        if (timeSlotClasses==null){
                            TimeSlotClasses temp=new TimeSlotClasses();
                            temp.setTeacherMap(new HashMap<String, Teacher>());
                            timeSlotClasses=temp;
                        }
                        Map<String, Teacher> teacherMap = timeSlotClasses.getTeacherMap();
                        Teacher teacher1 = teacherMap.get(teacher.getName());
                        //这个时间段没这个老师
                        if (teacher1==null){
                            //科目不重复
                            for (int j = 0; j <4 ; j++) {
                                for (int k = 0; k < subject.length; k++) {
                                    if (course[j]!=null&&course[j].getSubject().equals(subject[k])){
                                       tempClassExist=false;
                                       subject[k]=null;
                                       break;
                                    }
                                }
                            }
                            if (classExist){
                                for (int j = 0; j < subject.length; j++) {
                                    if (subject[i]!=null){
                                        course[i].setName(teacher.getName());
                                        course[i].setSubject(subject[j]);
                                    }
                                }
                            }
                        }
                    }
                }
                classTimeTable.setCourse(course);
                timeSlot.put(grade,classTimeTable);
            }

            for (Map.Entry entry:timeSlot.entrySet()){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
    }

}
