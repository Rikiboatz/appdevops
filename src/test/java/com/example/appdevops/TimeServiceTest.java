package com.example.appdevops;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.appdevops.service.TimeService;

public class TimeServiceTest {
    
    @Test
    void nowIso_shouldReturnNonEmpty() {
        var svc = new TimeService();
        Assertions.assertThat(svc.nowIso()).isNotBlank();
    }

}
