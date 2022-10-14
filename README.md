# gRPC-Spring-Boot-Starter
[https://yidongnan.github.io/grpc-spring-boot-starter/en/](https://yidongnan.github.io/grpc-spring-boot-starter/en/)

## gRPC Interface

### Maven
<details>
    <summary>pom.xml</summary>
    <p>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <artifactId>grpc-template</artifactId>
    <groupId>com.chacare.grpc</groupId>
    <version>1.0-SNAPSHOT</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <artifactId>grpc-interface</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

    <protobuf.version>3.19.1</protobuf.version>
    <protobuf-plugin.version>0.6.1</protobuf-plugin.version>
    <grpc.version>1.49.2</grpc.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-stub</artifactId>
      <version>${grpc.version}</version>
    </dependency>

    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-protobuf</artifactId>
      <version>${grpc.version}</version>
    </dependency>

    <dependency>
      <!-- Java 9+ compatibility - Do NOT update to 2.0.0 -->
      <groupId>jakarta.annotation</groupId>
      <artifactId>jakarta.annotation-api</artifactId>
      <version>1.3.5</version>
      <optional>true</optional>
    </dependency>
  </dependencies>

  <build>
    <extensions>
      <extension>
        <groupId>kr.motd.maven</groupId>
        <artifactId>os-maven-plugin</artifactId>
        <version>1.7.0</version>
      </extension>
    </extensions>

    <plugins>
      <plugin>
        <groupId>org.xolstice.maven.plugins</groupId>
        <artifactId>protobuf-maven-plugin</artifactId>
        <version>${protobuf-plugin.version}</version>
        <configuration>
          <protocArtifact>com.google.protobuf:protoc:${protobuf.version}:exe:${os.detected.classifier}</protocArtifact>
          <pluginId>grpc-java</pluginId>
          <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}</pluginArtifact>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
              <goal>compile-custom</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>

</project>
```

    </p>
</details>

### ProtoBuffer
```protobuf
syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.example.grpc";
option java_outer_classname = "HelloWorldProto";

service Simple {
  rpc SayHello (HelloRequest) returns (HelloReply) {}
}

message HelloRequest {
  string name = 1;
}

message HelloReply {
  string message = 1;
}

```

## gRPC Server
### Maven
<details>
    <summary>pom.xml</summary>
    <p>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <artifactId>grpc-template</artifactId>
    <groupId>com.chacare.grpc</groupId>
    <version>1.0-SNAPSHOT</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.chacare.grpc.server</groupId>
  <artifactId>grpc-server</artifactId>

  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <grpc.spring.boot.starter.version>2.13.1.RELEASE</grpc.spring.boot.starter.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>net.devh</groupId>
      <artifactId>grpc-server-spring-boot-starter</artifactId>
      <version>${grpc.spring.boot.starter.version}</version>
    </dependency>

    <dependency>
      <groupId>com.chacare.grpc</groupId>
      <artifactId>grpc-interface</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
    <dependency>
      <groupId>com.chacare.grpc</groupId>
      <artifactId>grpc-interface</artifactId>
      <version>1.0-SNAPSHOT</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.24</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>

</project>
```

    </p>
</details>

### gRPC Server
```java
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
```

### application.yaml
```yaml
grpc:
  server:
    port: 8088
```

## gRPC Client
### Maven
<details>
    <summary>pom.xml</summary>
    <p>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <artifactId>grpc-template</artifactId>
    <groupId>com.chacare.grpc</groupId>
    <version>1.0-SNAPSHOT</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.chacare.grpc.server</groupId>
  <artifactId>grpc-server</artifactId>

  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <grpc.spring.boot.starter.version>2.13.1.RELEASE</grpc.spring.boot.starter.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>net.devh</groupId>
      <artifactId>grpc-client-spring-boot-starter</artifactId>
      <version>${grpc.spring.boot.starter.version}</version>
    </dependency>

    <dependency>
      <groupId>com.chacare.grpc</groupId>
      <artifactId>grpc-interface</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.24</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <version>2.7.4</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>

</project>
```

    </p>
</details>

### Configuration
```java
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
```

### GrpcClient
```java
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
```

### application.yaml
```yaml
grpc:
  client:
    blockingStub:
      address: "static://127.0.0.1:9090"
      negotiation-type: plaintext

```
