package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(9090).addService(new QuizGameService()).build();

        try {
            server.start();
            System.out.println("Server started at " + server.getPort());
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}