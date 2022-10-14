package com.chacare.grpc.controller;

import com.chacare.grpc.service.GrpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grpc")
public class HelloController {
  private final GrpcService grpcService;

  @GetMapping
  public String helloGrpc(@RequestParam String name) {
    return grpcService.receiveGreeting(name);
  }
}
