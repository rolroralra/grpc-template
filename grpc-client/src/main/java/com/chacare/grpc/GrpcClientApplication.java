package com.chacare.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication extends SpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(GrpcClientApplication.class, args);
  }
}
