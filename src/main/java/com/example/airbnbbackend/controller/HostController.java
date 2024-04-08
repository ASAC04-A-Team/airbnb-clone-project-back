package com.example.airbnbbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/host")
@Slf4j
@Tag(name = "Host", description = "Host API")
public class HostController {
}
