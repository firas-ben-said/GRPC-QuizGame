package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import quiz.QuizGameServiceGrpc;
import quiz.QuizOuterClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class QuizGameService extends QuizGameServiceGrpc.QuizGameServiceImplBase {
    private List<QuizOuterClass.Player> players = new ArrayList<>();
    private List<QuizOuterClass.Quiz> quizList = new ArrayList<>();

    @Override
    public void registerPlayer(QuizOuterClass.RegisterPlayerRequest request, StreamObserver<QuizOuterClass.RegisterPlayerResponse> responseObserver) {
        String playerName = request.getPlayerName();

        QuizOuterClass.RegisterPlayerResponse.Builder response = QuizOuterClass.RegisterPlayerResponse.newBuilder();

        QuizOuterClass.Player player = QuizOuterClass.Player.newBuilder()
                .setPlayerName(playerName)
                .setScore(0)
                .build();

        players.add(player);
        response.setResponseCode(0).setResponseMessage("Player registered successfully");

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getQuiz(QuizOuterClass.Empty request, StreamObserver<QuizOuterClass.QuizResponse> responseObserver) {
        if (quizList.isEmpty()) {
            loadQuizFromJson();
        }

        QuizOuterClass.QuizResponse.Builder response = QuizOuterClass.QuizResponse.newBuilder();

        for (QuizOuterClass.Quiz quiz : quizList) {
            response.addAllQuizzes(quiz);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

    }

    private void loadQuizFromJson() {
        try {
            // Use ClassLoader to get the resource as a stream
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Quiz.json");

            if (inputStream == null) {
                throw new FileNotFoundException("Resource Quiz.json not found in classpath");
            }

            // Create ObjectMapper instance to parse the JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON into a Quiz list
            QuizJsonWrapper quizJsonWrapper = objectMapper.readValue(inputStream, QuizJsonWrapper.class);

            // Convert the quiz data into the form needed by the gRPC response
            for (QuizJson quizJson : quizJsonWrapper.getQuiz()) {
                QuizOuterClass.Quiz.Builder quiz = QuizOuterClass.Quiz.newBuilder()
                        .setId(quizJson.getId())
                        .setQuestion(quizJson.getQuestion())
                        .addAllOptions(quizJson.getOptions())
                        .setAnswer(quizJson.getAnswer());

                quizList.add(quiz.build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getQuestion(QuizOuterClass.GetQuestionRequest request, StreamObserver<QuizOuterClass.Quiz> responseObserver) {
        long id = request.getId();

        for (QuizOuterClass.Quiz quiz : quizList) {
            if (quiz.getId() == id) {
                responseObserver.onNext(quiz);
                responseObserver.onCompleted();
                return;
            }
        }

        responseObserver.onError(new Throwable("Quiz not found"));
    }

    @Override
    public void play(QuizOuterClass.PlayRequest request, StreamObserver<QuizOuterClass.PlayResponse> responseObserver) {
        String playerName = request.getPlayerName();
        long id = request.getQuizId();
        int answer = request.getSelectedAnswer();

        QuizOuterClass.PlayResponse.Builder response = QuizOuterClass.PlayResponse.newBuilder();

        for (QuizOuterClass.Quiz quiz : quizList) {
            if (quiz.getId() == id) {
                if(quiz.getAnswer() == answer) {
                    response.setResponseCode(0).setResponseMessage("Correct Answer");

                    // Update the player's score
                    for (int i = 0; i < players.size(); i++) {
                        QuizOuterClass.Player player = players.get(i);
                        if (player.getPlayerName().equals(playerName)) {
                            QuizOuterClass.Player updatedPlayer = player.toBuilder()
                                    .setScore(player.getScore() + 1)
                                    .build();
                            players.set(i, updatedPlayer); // Replace the player with the updated version
                            break;
                        }
                    }
                } else {
                    response.setResponseCode(100).setResponseMessage("Wrong Answer");
                }
                break;
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getPlayerScores(QuizOuterClass.Empty request, StreamObserver<QuizOuterClass.PlayerScoresResponse> responseObserver) {
        QuizOuterClass.PlayerScoresResponse.Builder response = QuizOuterClass.PlayerScoresResponse.newBuilder();

        for (QuizOuterClass.Player player : players) {
            response.addPlayers(player);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
