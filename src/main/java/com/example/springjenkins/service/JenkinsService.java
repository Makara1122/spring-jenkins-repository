package com.example.springjenkins.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JenkinsService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String jenkinsUrl = "https://jenkins.istad.co/";
    private final String jenkinsUser = "makara";
    private final String jenkinsToken = "1179bc8f15b27a3f06c7bfd7c7c578334d";

    public List<String> getJobNames() {
        List<String> jobNames = new ArrayList<>();
        try {
            String url = jenkinsUrl + "api/json";

            String auth = jenkinsUser + ":" + jenkinsToken;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodedAuth);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode jobsNode = rootNode.get("jobs");

            if (jobsNode != null) {
                for (JsonNode jobNode : jobsNode) {
                    jobNames.add(jobNode.get("name").asText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobNames;
    }
}
