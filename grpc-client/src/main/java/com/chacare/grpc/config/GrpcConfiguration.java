package com.chacare.grpc.config;

import com.chacare.grpc.SimpleGrpc;
import com.chacare.grpc.service.GrpcService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.client.inject.GrpcClientBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@GrpcClientBean(
    clazz = SimpleGrpc.SimpleBlockingStub.class,
    beanName = "blockingStub",
    client = @GrpcClient("Simple")
)
public class GrpcConfiguration {
  @Bean
  GrpcService grpcService(@Autowired SimpleGrpc.SimpleBlockingStub blockingStub) {
    return new GrpcService(blockingStub);
  }
}
