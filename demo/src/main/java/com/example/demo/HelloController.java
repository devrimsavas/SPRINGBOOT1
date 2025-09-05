package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController 


public class HelloController {
    @GetMapping("/") 
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/name/{personName}") 
    public String getName(@PathVariable String personName) {
        return "hello dear"+personName;
    }

    @GetMapping("/add/{number1}/{number2}")
    public String addNumbers(@PathVariable float number1, @PathVariable float number2) {
        float result=number1+number2;
        return Float.toString(result);
    }

    @PostMapping("/multiple")
    public float multipleNumbers(@RequestBody Multiple multiple) {
        float result=multiple.number1*multiple.number2;

        return  result;
    }

    @GetMapping("/showarray") 
    public String[] showArray() {
        try {
            ShowArray showArray=new ShowArray();
           
            return showArray.students;

        } catch(Error err) {
            return new String[] {"Error"+err.getMessage()};
        }
        
    }



    
}
