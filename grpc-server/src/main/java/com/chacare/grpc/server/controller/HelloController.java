package com.chacare.grpc.server.controller;

import com.chacare.grpc.HelloReply;
import com.chacare.grpc.HelloRequest;
import com.chacare.grpc.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloController extends SimpleGrpc.SimpleImplBase {

  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    HelloReply response = HelloReply.newBuilder()
        .setMessage("Hello " + request.getName())
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
