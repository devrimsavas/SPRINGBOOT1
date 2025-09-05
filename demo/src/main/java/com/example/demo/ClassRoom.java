package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.Map;

import jakarta.annotation.PostConstruct;

//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service 


public class ClassRoom {    

    public String className;

    public ArrayList<Student> classRoom=new ArrayList<Student>();
    //get service 
    private final JSONREADER jsonReader;

    //inject to constructor 

    public ClassRoom(JSONREADER jsonReader) {
        this.className="our class";
        this.jsonReader=jsonReader;

    }

    @PostConstruct
    void initFromJson() {
        try {
            List<Map<String,Object>> rows=jsonReader.readJsonFromResources("students.json");
            for (Map<String, Object> row:rows ) {
                String name=(String) row.get("name");
                Integer age=row.get("age")==null 
                ? null 
                :((Number) row.get("age")).intValue();
                int newId=classRoom.size()+1;
                classRoom.add(new Student(newId,name,age !=null ? age:0));
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    //SHOW STUDENTS 

    public ArrayList<Student> showStudents()  {
        //add later empty check 
        return classRoom; 
    }

    //ADD STUDENT 

    public void addStudent(Student student) {

        if (student.name=="" || student.age<0 ) {
            throw new IllegalStateException("Invalid Entry try again");
        }
        classRoom.add(student);
    }

    //REMOVE STUDENT 

    public Student removeStudent(int id) {

        if (classRoom.isEmpty()) {
            throw new IllegalStateException("No student in classRoom");
        }

        // find the student with matching id
        Student studentToRemove = classRoom.stream()
            .filter(s -> s.id == id)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("There is no student with id " + id));

        classRoom.remove(studentToRemove);

        System.out.printf("student with id: %d and name: %s removed%n",
                studentToRemove.id, studentToRemove.name);

        return studentToRemove;
        
    }
    // UPDATE STUDENT 
    public Student updateStudent(int id,Student updatedStudent) {
        if (classRoom.isEmpty()) {
            throw new IllegalStateException("No student in classRoom");
        }
        Student studentToUpdate=classRoom.stream()
        .filter(s->s.id==id)
        .findFirst()
        .orElseThrow(()-> new IllegalStateException("There is not student with id "+id));
        //update partial if it is null 
        studentToUpdate.age=Objects.requireNonNullElse(updatedStudent.age,studentToUpdate.age);
        studentToUpdate.name=Objects.requireNonNullElse(updatedStudent.name,studentToUpdate.name);
        return studentToUpdate;



    }



    //EXTRA METHODS FILTERS 

    

    //GET OLDEST 

    public List<Student> getOldestStudent() {
        if (classRoom.isEmpty()) {
            throw new IllegalStateException("No student in classRoom");
        }
        int maxAge=classRoom.stream()
        .mapToInt(s->s.age)
        .max()
        .orElseThrow();

        return classRoom.stream()
        .filter(s->s.age==maxAge)
        .toList();
    }

    public List<Student> getYoungestStudent() {
        if (classRoom.isEmpty()) {
            throw new IllegalStateException("No student in classRoom");
        }
        int minAge=classRoom.stream()
        .mapToInt(s->s.age)
        .min()
        .orElseThrow();
        
        //return Collections.min(classRoom,Comparator.comparingInt(s->s.age)); this is if there is first max or min value 

        return classRoom.stream()
        .filter(s->s.age==minAge)
        .toList();        
    }


    

    
}
