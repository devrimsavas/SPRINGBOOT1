package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class JSONREADER {

    // Read a JSON array into a List of Maps
    public List<Map<String, Object>> readJsonFromResources(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(filename);

        try (InputStream inputStream = resource.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, List.class);
        }
    }

    
    public List<Map<String, Object>> populateClassRoom() throws IOException {
        return this.readJsonFromResources("students.json");
    }
}
