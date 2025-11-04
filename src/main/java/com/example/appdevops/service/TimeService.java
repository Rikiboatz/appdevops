package com.example.appdevops.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
    private static final  DateTimeFormatter dft = DateTimeFormatter.ISO_INSTANT;
    public String nowIso() {
        return dft.format(Instant.now().atOffset(ZoneOffset.UTC));
    }
}
