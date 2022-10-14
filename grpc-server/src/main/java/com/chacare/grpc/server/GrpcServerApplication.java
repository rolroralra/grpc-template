package com.chacare.grpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcServerApplication extends SpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(GrpcServerApplication.class, args);
  }
}
