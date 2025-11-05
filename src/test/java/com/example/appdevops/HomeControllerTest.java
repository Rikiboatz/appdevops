package com.example.appdevops;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.example.appdevops.controller.HomeController;
import com.example.appdevops.service.TimeService;

public class HomeControllerTest {
    
    @Test
    void health_shouldOk() {
        var c = new HomeController(new TimeService());
        ResponseEntity<Map<String, String>> res = c.health();
        Assertions.assertThat(res.getBody()).containsEntry("status", "ok");
    }
}
