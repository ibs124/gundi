package ibs124.gundi.service.seed.impl;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
class JsonReader {

    private final ObjectMapper objectMapper;

    public JsonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode readByClassPath(String path) {
        try {
            if (path == null) {
                throw new NullPointerException("Class path resource is null.");
            }

            Resource resource = new ClassPathResource(path);

            return this.objectMapper.readTree(resource.getInputStream());

        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Class path resource not found: " + path, e);
        }
    }

}
