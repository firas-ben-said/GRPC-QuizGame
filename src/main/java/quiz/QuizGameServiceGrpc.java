package quiz;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: quiz.proto")
public final class QuizGameServiceGrpc {

  private QuizGameServiceGrpc() {}

  public static final String SERVICE_NAME = "quiz.QuizGameService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<quiz.QuizOuterClass.Empty,
      quiz.QuizOuterClass.QuizResponse> getGetQuizMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getQuiz",
      requestType = quiz.QuizOuterClass.Empty.class,
      responseType = quiz.QuizOuterClass.QuizResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<quiz.QuizOuterClass.Empty,
      quiz.QuizOuterClass.QuizResponse> getGetQuizMethod() {
    io.grpc.MethodDescriptor<quiz.QuizOuterClass.Empty, quiz.QuizOuterClass.QuizResponse> getGetQuizMethod;
    if ((getGetQuizMethod = QuizGameServiceGrpc.getGetQuizMethod) == null) {
      synchronized (QuizGameServiceGrpc.class) {
        if ((getGetQuizMethod = QuizGameServiceGrpc.getGetQuizMethod) == null) {
          QuizGameServiceGrpc.getGetQuizMethod = getGetQuizMethod = 
              io.grpc.MethodDescriptor.<quiz.QuizOuterClass.Empty, quiz.QuizOuterClass.QuizResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "quiz.QuizGameService", "getQuiz"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.QuizResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new QuizGameServiceMethodDescriptorSupplier("getQuiz"))
                  .build();
          }
        }
     }
     return getGetQuizMethod;
  }

  private static volatile io.grpc.MethodDescriptor<quiz.QuizOuterClass.GetQuestionRequest,
      quiz.QuizOuterClass.Quiz> getGetQuestionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getQuestion",
      requestType = quiz.QuizOuterClass.GetQuestionRequest.class,
      responseType = quiz.QuizOuterClass.Quiz.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<quiz.QuizOuterClass.GetQuestionRequest,
      quiz.QuizOuterClass.Quiz> getGetQuestionMethod() {
    io.grpc.MethodDescriptor<quiz.QuizOuterClass.GetQuestionRequest, quiz.QuizOuterClass.Quiz> getGetQuestionMethod;
    if ((getGetQuestionMethod = QuizGameServiceGrpc.getGetQuestionMethod) == null) {
      synchronized (QuizGameServiceGrpc.class) {
        if ((getGetQuestionMethod = QuizGameServiceGrpc.getGetQuestionMethod) == null) {
          QuizGameServiceGrpc.getGetQuestionMethod = getGetQuestionMethod = 
              io.grpc.MethodDescriptor.<quiz.QuizOuterClass.GetQuestionRequest, quiz.QuizOuterClass.Quiz>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "quiz.QuizGameService", "getQuestion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.GetQuestionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.Quiz.getDefaultInstance()))
                  .setSchemaDescriptor(new QuizGameServiceMethodDescriptorSupplier("getQuestion"))
                  .build();
          }
        }
     }
     return getGetQuestionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<quiz.QuizOuterClass.RegisterPlayerRequest,
      quiz.QuizOuterClass.RegisterPlayerResponse> getRegisterPlayerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registerPlayer",
      requestType = quiz.QuizOuterClass.RegisterPlayerRequest.class,
      responseType = quiz.QuizOuterClass.RegisterPlayerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<quiz.QuizOuterClass.RegisterPlayerRequest,
      quiz.QuizOuterClass.RegisterPlayerResponse> getRegisterPlayerMethod() {
    io.grpc.MethodDescriptor<quiz.QuizOuterClass.RegisterPlayerRequest, quiz.QuizOuterClass.RegisterPlayerResponse> getRegisterPlayerMethod;
    if ((getRegisterPlayerMethod = QuizGameServiceGrpc.getRegisterPlayerMethod) == null) {
      synchronized (QuizGameServiceGrpc.class) {
        if ((getRegisterPlayerMethod = QuizGameServiceGrpc.getRegisterPlayerMethod) == null) {
          QuizGameServiceGrpc.getRegisterPlayerMethod = getRegisterPlayerMethod = 
              io.grpc.MethodDescriptor.<quiz.QuizOuterClass.RegisterPlayerRequest, quiz.QuizOuterClass.RegisterPlayerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "quiz.QuizGameService", "registerPlayer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.RegisterPlayerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.RegisterPlayerResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new QuizGameServiceMethodDescriptorSupplier("registerPlayer"))
                  .build();
          }
        }
     }
     return getRegisterPlayerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<quiz.QuizOuterClass.PlayRequest,
      quiz.QuizOuterClass.PlayResponse> getPlayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "play",
      requestType = quiz.QuizOuterClass.PlayRequest.class,
      responseType = quiz.QuizOuterClass.PlayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<quiz.QuizOuterClass.PlayRequest,
      quiz.QuizOuterClass.PlayResponse> getPlayMethod() {
    io.grpc.MethodDescriptor<quiz.QuizOuterClass.PlayRequest, quiz.QuizOuterClass.PlayResponse> getPlayMethod;
    if ((getPlayMethod = QuizGameServiceGrpc.getPlayMethod) == null) {
      synchronized (QuizGameServiceGrpc.class) {
        if ((getPlayMethod = QuizGameServiceGrpc.getPlayMethod) == null) {
          QuizGameServiceGrpc.getPlayMethod = getPlayMethod = 
              io.grpc.MethodDescriptor.<quiz.QuizOuterClass.PlayRequest, quiz.QuizOuterClass.PlayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "quiz.QuizGameService", "play"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.PlayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.PlayResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new QuizGameServiceMethodDescriptorSupplier("play"))
                  .build();
          }
        }
     }
     return getPlayMethod;
  }

  private static volatile io.grpc.MethodDescriptor<quiz.QuizOuterClass.Empty,
      quiz.QuizOuterClass.PlayerScoresResponse> getGetPlayerScoresMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPlayerScores",
      requestType = quiz.QuizOuterClass.Empty.class,
      responseType = quiz.QuizOuterClass.PlayerScoresResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<quiz.QuizOuterClass.Empty,
      quiz.QuizOuterClass.PlayerScoresResponse> getGetPlayerScoresMethod() {
    io.grpc.MethodDescriptor<quiz.QuizOuterClass.Empty, quiz.QuizOuterClass.PlayerScoresResponse> getGetPlayerScoresMethod;
    if ((getGetPlayerScoresMethod = QuizGameServiceGrpc.getGetPlayerScoresMethod) == null) {
      synchronized (QuizGameServiceGrpc.class) {
        if ((getGetPlayerScoresMethod = QuizGameServiceGrpc.getGetPlayerScoresMethod) == null) {
          QuizGameServiceGrpc.getGetPlayerScoresMethod = getGetPlayerScoresMethod = 
              io.grpc.MethodDescriptor.<quiz.QuizOuterClass.Empty, quiz.QuizOuterClass.PlayerScoresResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "quiz.QuizGameService", "getPlayerScores"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  quiz.QuizOuterClass.PlayerScoresResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new QuizGameServiceMethodDescriptorSupplier("getPlayerScores"))
                  .build();
          }
        }
     }
     return getGetPlayerScoresMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QuizGameServiceStub newStub(io.grpc.Channel channel) {
    return new QuizGameServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QuizGameServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QuizGameServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QuizGameServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QuizGameServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class QuizGameServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getQuiz(quiz.QuizOuterClass.Empty request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.QuizResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetQuizMethod(), responseObserver);
    }

    /**
     */
    public void getQuestion(quiz.QuizOuterClass.GetQuestionRequest request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.Quiz> responseObserver) {
      asyncUnimplementedUnaryCall(getGetQuestionMethod(), responseObserver);
    }

    /**
     */
    public void registerPlayer(quiz.QuizOuterClass.RegisterPlayerRequest request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.RegisterPlayerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterPlayerMethod(), responseObserver);
    }

    /**
     */
    public void play(quiz.QuizOuterClass.PlayRequest request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.PlayResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPlayMethod(), responseObserver);
    }

    /**
     */
    public void getPlayerScores(quiz.QuizOuterClass.Empty request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.PlayerScoresResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPlayerScoresMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetQuizMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                quiz.QuizOuterClass.Empty,
                quiz.QuizOuterClass.QuizResponse>(
                  this, METHODID_GET_QUIZ)))
          .addMethod(
            getGetQuestionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                quiz.QuizOuterClass.GetQuestionRequest,
                quiz.QuizOuterClass.Quiz>(
                  this, METHODID_GET_QUESTION)))
          .addMethod(
            getRegisterPlayerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                quiz.QuizOuterClass.RegisterPlayerRequest,
                quiz.QuizOuterClass.RegisterPlayerResponse>(
                  this, METHODID_REGISTER_PLAYER)))
          .addMethod(
            getPlayMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                quiz.QuizOuterClass.PlayRequest,
                quiz.QuizOuterClass.PlayResponse>(
                  this, METHODID_PLAY)))
          .addMethod(
            getGetPlayerScoresMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                quiz.QuizOuterClass.Empty,
                quiz.QuizOuterClass.PlayerScoresResponse>(
                  this, METHODID_GET_PLAYER_SCORES)))
          .build();
    }
  }

  /**
   */
  public static final class QuizGameServiceStub extends io.grpc.stub.AbstractStub<QuizGameServiceStub> {
    private QuizGameServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QuizGameServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuizGameServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QuizGameServiceStub(channel, callOptions);
    }

    /**
     */
    public void getQuiz(quiz.QuizOuterClass.Empty request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.QuizResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetQuizMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getQuestion(quiz.QuizOuterClass.GetQuestionRequest request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.Quiz> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetQuestionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerPlayer(quiz.QuizOuterClass.RegisterPlayerRequest request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.RegisterPlayerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterPlayerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void play(quiz.QuizOuterClass.PlayRequest request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.PlayResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPlayMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPlayerScores(quiz.QuizOuterClass.Empty request,
        io.grpc.stub.StreamObserver<quiz.QuizOuterClass.PlayerScoresResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPlayerScoresMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QuizGameServiceBlockingStub extends io.grpc.stub.AbstractStub<QuizGameServiceBlockingStub> {
    private QuizGameServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QuizGameServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuizGameServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QuizGameServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public quiz.QuizOuterClass.QuizResponse getQuiz(quiz.QuizOuterClass.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetQuizMethod(), getCallOptions(), request);
    }

    /**
     */
    public quiz.QuizOuterClass.Quiz getQuestion(quiz.QuizOuterClass.GetQuestionRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetQuestionMethod(), getCallOptions(), request);
    }

    /**
     */
    public quiz.QuizOuterClass.RegisterPlayerResponse registerPlayer(quiz.QuizOuterClass.RegisterPlayerRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterPlayerMethod(), getCallOptions(), request);
    }

    /**
     */
    public quiz.QuizOuterClass.PlayResponse play(quiz.QuizOuterClass.PlayRequest request) {
      return blockingUnaryCall(
          getChannel(), getPlayMethod(), getCallOptions(), request);
    }

    /**
     */
    public quiz.QuizOuterClass.PlayerScoresResponse getPlayerScores(quiz.QuizOuterClass.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetPlayerScoresMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QuizGameServiceFutureStub extends io.grpc.stub.AbstractStub<QuizGameServiceFutureStub> {
    private QuizGameServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QuizGameServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuizGameServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QuizGameServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<quiz.QuizOuterClass.QuizResponse> getQuiz(
        quiz.QuizOuterClass.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetQuizMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<quiz.QuizOuterClass.Quiz> getQuestion(
        quiz.QuizOuterClass.GetQuestionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetQuestionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<quiz.QuizOuterClass.RegisterPlayerResponse> registerPlayer(
        quiz.QuizOuterClass.RegisterPlayerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterPlayerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<quiz.QuizOuterClass.PlayResponse> play(
        quiz.QuizOuterClass.PlayRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getPlayMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<quiz.QuizOuterClass.PlayerScoresResponse> getPlayerScores(
        quiz.QuizOuterClass.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPlayerScoresMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_QUIZ = 0;
  private static final int METHODID_GET_QUESTION = 1;
  private static final int METHODID_REGISTER_PLAYER = 2;
  private static final int METHODID_PLAY = 3;
  private static final int METHODID_GET_PLAYER_SCORES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QuizGameServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QuizGameServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_QUIZ:
          serviceImpl.getQuiz((quiz.QuizOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<quiz.QuizOuterClass.QuizResponse>) responseObserver);
          break;
        case METHODID_GET_QUESTION:
          serviceImpl.getQuestion((quiz.QuizOuterClass.GetQuestionRequest) request,
              (io.grpc.stub.StreamObserver<quiz.QuizOuterClass.Quiz>) responseObserver);
          break;
        case METHODID_REGISTER_PLAYER:
          serviceImpl.registerPlayer((quiz.QuizOuterClass.RegisterPlayerRequest) request,
              (io.grpc.stub.StreamObserver<quiz.QuizOuterClass.RegisterPlayerResponse>) responseObserver);
          break;
        case METHODID_PLAY:
          serviceImpl.play((quiz.QuizOuterClass.PlayRequest) request,
              (io.grpc.stub.StreamObserver<quiz.QuizOuterClass.PlayResponse>) responseObserver);
          break;
        case METHODID_GET_PLAYER_SCORES:
          serviceImpl.getPlayerScores((quiz.QuizOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<quiz.QuizOuterClass.PlayerScoresResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class QuizGameServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QuizGameServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return quiz.QuizOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QuizGameService");
    }
  }

  private static final class QuizGameServiceFileDescriptorSupplier
      extends QuizGameServiceBaseDescriptorSupplier {
    QuizGameServiceFileDescriptorSupplier() {}
  }

  private static final class QuizGameServiceMethodDescriptorSupplier
      extends QuizGameServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QuizGameServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (QuizGameServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QuizGameServiceFileDescriptorSupplier())
              .addMethod(getGetQuizMethod())
              .addMethod(getGetQuestionMethod())
              .addMethod(getRegisterPlayerMethod())
              .addMethod(getPlayMethod())
              .addMethod(getGetPlayerScoresMethod())
              .build();
        }
      }
    }
    return result;
  }
}
