package com.example.springjenkins.controller;

import com.example.springjenkins.service.JenkinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class JenkinsController {
    @Autowired
    private JenkinsService jenkinsService;

    @GetMapping("/jenkins/jobs")
    public List<String> listJobs() {
        return jenkinsService.getJobNames();
    }
}
