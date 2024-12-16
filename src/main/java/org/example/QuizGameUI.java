package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import quiz.QuizGameServiceGrpc;
import quiz.QuizOuterClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuizGameUI extends JFrame {
    private ManagedChannel channel;
    private QuizGameServiceGrpc.QuizGameServiceBlockingStub blockingStub;

    // GUI Components
    private JTextField playerNameField;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;

    // Game State
    private String playerName;
    private List<QuizOuterClass.Quiz> quizList;
    private QuizOuterClass.Quiz currentQuiz;
    private int currentQuizIndex = 0;

    public QuizGameUI() {
        // Set up gRPC channel
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // Only for development
                .build();
        blockingStub = QuizGameServiceGrpc.newBlockingStub(channel);

        // Set up the main frame
        setTitle("Quiz Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Player Registration Panel
        JPanel registrationPanel = new JPanel(new FlowLayout());
        playerNameField = new JTextField(20);
        JButton registerButton = new JButton("Register");
        registrationPanel.add(new JLabel("Player Name:"));
        registrationPanel.add(playerNameField);
        registrationPanel.add(registerButton);
        add(registrationPanel, BorderLayout.NORTH);

        // Question Panel
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel("Welcome! Register to start the quiz.", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionPanel.add(questionLabel, BorderLayout.CENTER);

        // Options Panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        questionPanel.add(optionsPanel, BorderLayout.SOUTH);
        add(questionPanel, BorderLayout.CENTER);

        // Submit Panel
        JPanel submitPanel = new JPanel(new BorderLayout());
        submitButton = new JButton("Submit Answer");
        submitButton.setEnabled(false);
        resultLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);

        submitPanel.add(submitButton, BorderLayout.NORTH);
        submitPanel.add(resultLabel, BorderLayout.CENTER);
        submitPanel.add(scoreLabel, BorderLayout.SOUTH);

        add(submitPanel, BorderLayout.SOUTH);

        // Register Player Action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPlayer();
            }
        });

        // Submit Answer Action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAnswer();
            }
        });
    }

    private void registerPlayer() {
        try {
            playerName = playerNameField.getText().trim();
            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a player name");
                return;
            }

            // Register player via gRPC
            QuizOuterClass.RegisterPlayerRequest request = QuizOuterClass.RegisterPlayerRequest.newBuilder()
                    .setPlayerName(playerName)
                    .build();

            QuizOuterClass.RegisterPlayerResponse response = blockingStub.registerPlayer(request);

            if (response.getResponseCode() == 0) {
                // Load quizzes
                loadQuizzes();

                // Start the first quiz
                startQuiz();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed: " + response.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering player: " + e.getMessage());
        }
    }

    private void loadQuizzes() {
        // Fetch quizzes via gRPC
        QuizOuterClass.QuizResponse quizResponse = blockingStub.getQuiz(QuizOuterClass.Empty.newBuilder().build());
        quizList = quizResponse.getAllQuizzesList();
    }

    private void startQuiz() {
        if (quizList == null || quizList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No quizzes available");
            return;
        }

        currentQuizIndex = 0;
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuizIndex >= quizList.size()) {
            endQuiz();
            return;
        }

        currentQuiz = quizList.get(currentQuizIndex);

        // Update question and options
        questionLabel.setText(currentQuiz.getQuestion());
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(currentQuiz.getOptions(i));
            optionButtons[i].setSelected(false);
        }

        // Enable submit button
        submitButton.setEnabled(true);
        resultLabel.setText("");
    }

    private void submitAnswer() {
        int selectedAnswer = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedAnswer = i;
                break;
            }
        }

        if (selectedAnswer == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer");
            return;
        }

        // Submit answer via gRPC
        QuizOuterClass.PlayRequest playRequest = QuizOuterClass.PlayRequest.newBuilder()
                .setPlayerName(playerName)
                .setQuizId(currentQuiz.getId())
                .setSelectedAnswer(selectedAnswer)
                .build();

        QuizOuterClass.PlayResponse playResponse = blockingStub.play(playRequest);

        // Update result and score
        if (playResponse.getResponseCode() == 0) {
            resultLabel.setText("Correct!");
            resultLabel.setForeground(Color.GREEN);
        } else {
            resultLabel.setText("Incorrect. The correct answer was: " +
                    currentQuiz.getOptions(currentQuiz.getAnswer()));
            resultLabel.setForeground(Color.RED);
        }

        // Move to next question
        currentQuizIndex++;

        // Disable submit button temporarily
        submitButton.setEnabled(false);

        // Update player scores
        updatePlayerScores();

        // Schedule next question
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNextQuestion();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void updatePlayerScores() {
        QuizOuterClass.PlayerScoresResponse scoresResponse =
                blockingStub.getPlayerScores(QuizOuterClass.Empty.newBuilder().build());

        for (QuizOuterClass.Player player : scoresResponse.getPlayersList()) {
            if (player.getPlayerName().equals(playerName)) {
                scoreLabel.setText("Score: " + player.getScore());
                break;
            }
        }
    }

    private void endQuiz() {
        questionLabel.setText("Quiz Completed!");
        submitButton.setEnabled(false);

        // Show final score
        QuizOuterClass.PlayerScoresResponse scoresResponse =
                blockingStub.getPlayerScores(QuizOuterClass.Empty.newBuilder().build());


        for (QuizOuterClass.Player player : scoresResponse.getPlayersList()) {
            if (player.getPlayerName().equals(playerName)) {
                JOptionPane.showMessageDialog(this,
                        "Quiz Finished!\nYour final score: " + player.getScore());
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuizGameUI client = new QuizGameUI();
                client.setVisible(true);
            }
        });
    }

    // Shutdown method to close the gRPC channel
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
