package com.example.appdevops.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appdevops.service.TimeService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
public class HomeController {
    private final TimeService timeService;

    public HomeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("home")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of("message", "DevOps from local!"));
    }

    @GetMapping("health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
    
    @GetMapping("api/time")
    public ResponseEntity<Map<String, String>> time() {
        return ResponseEntity.ok(Map.of("now", timeService.nowIso()));
    }
    
}
