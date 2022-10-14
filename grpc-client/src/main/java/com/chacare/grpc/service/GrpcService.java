package com.chacare.grpc.service;

import com.chacare.grpc.HelloRequest;
import com.chacare.grpc.SimpleGrpc.SimpleBlockingStub;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service("blockingStub")
@AllArgsConstructor
public class GrpcService {

  @GrpcClient("blockingStub")
  private SimpleBlockingStub stub;

  public String receiveGreeting(String name) {
    HelloRequest request = HelloRequest.newBuilder()
        .setName(name)
        .build();

    return stub.sayHello(request).getMessage();
  }
}
