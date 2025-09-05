package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")


@RestController 
@RequestMapping("/students")


public class StudentController {
    private final ClassRoom classRoom;
    private final JSONREADER jsonReader;

    //constructor injecton 
    public StudentController(ClassRoom ourClassRoom,JSONREADER jsonReader) {
        this.classRoom=ourClassRoom;
        this.jsonReader=jsonReader;
    }

   // json test
@GetMapping("/test")
public List<Map<String, Object>> getJson() throws IOException {
    
    return jsonReader.populateClassRoom();
}
    

    //GET ALL STUDENTS 
    @GetMapping("/allstudents") 
    public ArrayList<Student> getStudents()  {
       
        return classRoom.showStudents();

    }
    // ADD NEW STUDENT 

    @PostMapping("/addstudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {

        try {
            String name=student.name;
            int age=student.age;
            int id=classRoom.classRoom.size()+1;
            Student newstudent=new Student(id, name, age);
            classRoom.addStudent(newstudent);

            return ResponseEntity.ok(newstudent); //200

        }catch(Exception err) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error",err.getMessage()));
        }
    }

    //DELETE STUDENT 
    @DeleteMapping("/deletestudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        try {
           return ResponseEntity.ok(classRoom.removeStudent(id));
        }catch(Exception err) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error",err.getMessage()));
        }
    }

    //UPDATE STUDENT 
    @PutMapping("/updatestudent/{id}") 
public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
    try {
        return ResponseEntity.ok(classRoom.updateStudent(id, updatedStudent));
    } catch (IllegalStateException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", e.getMessage()));
    } catch (IllegalArgumentException e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", e.getMessage()));
    } catch (Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Unexpected error: " + e.getMessage()));
    }
}

    @GetMapping("/getyoungest") 
    public ResponseEntity<?> youngenstStudent() {

        
        try {
            return ResponseEntity.ok(classRoom.getYoungestStudent());

        }catch(Exception err) {
            return ResponseEntity 
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error",err.getMessage()));
        }        
    }

    @GetMapping("/getoldest")
    public ResponseEntity<?> oldestStudent() {
        try {
            return ResponseEntity.ok(classRoom.getOldestStudent());
        }catch(Exception err) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error",err.getMessage()));
        }
    }
    


    


    
}
