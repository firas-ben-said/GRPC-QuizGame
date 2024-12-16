package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import quiz.QuizGameServiceGrpc;
import quiz.QuizOuterClass;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class QuizGameUI extends JFrame {
    private ManagedChannel channel;
    private QuizGameServiceGrpc.QuizGameServiceBlockingStub blockingStub;

    // Main Layout
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Registration Panel Components
    private JTextField playerNameField;
    private JButton registerButton;

    // Quiz Panel Components
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton submitButton;
    private JLabel resultLabel;

    // Score Panel Components
    private JLabel finalScoreLabel;
    private JButton restartButton;

    // Game State
    private String playerName;
    private List<QuizOuterClass.Quiz> quizList;
    private QuizOuterClass.Quiz currentQuiz;
    private int currentQuizIndex = 0;
    private int currentScore = 0;

    public QuizGameUI() {
        // Set up gRPC channel
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        blockingStub = QuizGameServiceGrpc.newBlockingStub(channel);

        // Initialize Main Frame
        setTitle("Quiz Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add Panels
        mainPanel.add(createRegistrationPanel(), "Registration");
        mainPanel.add(createQuizPanel(), "Quiz");
        mainPanel.add(createScorePanel(), "Score");

        add(mainPanel);
        showRegistrationPage();
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        playerNameField = new JTextField(20);
        registerButton = new JButton("Register");

        panel.add(new JLabel("Enter Player Name:"));
        panel.add(playerNameField);
        panel.add(registerButton);

        registerButton.addActionListener(e -> registerPlayer());
        return panel;
    }

    private JPanel createQuizPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        panel.add(optionsPanel, BorderLayout.CENTER);

        JPanel submitPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit Answer");
        resultLabel = new JLabel();
        submitPanel.add(submitButton);
        submitPanel.add(resultLabel);
        panel.add(submitPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> submitAnswer());
        return panel;
    }

    private JPanel createScorePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        finalScoreLabel = new JLabel("Your final score: 0", SwingConstants.CENTER);
        finalScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(finalScoreLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        restartButton = new JButton("Restart");
        JButton leaderboardButton = new JButton("View Leaderboard");

        buttonPanel.add(restartButton);
        buttonPanel.add(leaderboardButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        restartButton.addActionListener(e -> restartGame());
        leaderboardButton.addActionListener(e -> showLeaderboard());

        return panel;
    }

    private void showLeaderboard() {
        // Fetch player scores via gRPC
        QuizOuterClass.PlayerScoresResponse scoresResponse = blockingStub.getPlayerScores(QuizOuterClass.Empty.newBuilder().build());

        // Sort players by score in descending order
        List<QuizOuterClass.Player> sortedPlayers = scoresResponse.getPlayersList().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                .collect(java.util.stream.Collectors.toList());

        // Create leaderboard dialog
        JDialog leaderboardDialog = new JDialog(this, "Leaderboard", true);
        leaderboardDialog.setSize(400, 300);
        leaderboardDialog.setLocationRelativeTo(this);

        JPanel leaderboardPanel = new JPanel(new BorderLayout());
        JTextArea leaderboardTextArea = new JTextArea();
        leaderboardTextArea.setEditable(false);
        leaderboardTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Create leaderboard text
        StringBuilder leaderboardText = new StringBuilder("LEADERBOARD\n");
        leaderboardText.append("Rank\tPlayer\t\tScore\n");
        leaderboardText.append("-".repeat(40)).append("\n");

        for (int i = 0; i < sortedPlayers.size(); i++) {
            QuizOuterClass.Player player = sortedPlayers.get(i);
            leaderboardText.append(String.format("%d\t%s\t\t%d\n",
                    i + 1,
                    player.getPlayerName(),
                    player.getScore()));
        }

        leaderboardTextArea.setText(leaderboardText.toString());
        leaderboardPanel.add(new JScrollPane(leaderboardTextArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> leaderboardDialog.dispose());
        leaderboardPanel.add(closeButton, BorderLayout.SOUTH);

        leaderboardDialog.add(leaderboardPanel);
        leaderboardDialog.setVisible(true);
    }

    private void showRegistrationPage() {
        cardLayout.show(mainPanel, "Registration");
    }

    private void showQuizPage() {
        cardLayout.show(mainPanel, "Quiz");
    }

    private void showScorePage() {
        cardLayout.show(mainPanel, "Score");
    }

    private void registerPlayer() {
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
            loadQuizzes();
            startQuiz();
            showQuizPage();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed: " + response.getResponseMessage());
        }
    }

    private void loadQuizzes() {
        QuizOuterClass.QuizResponse quizResponse = blockingStub.getQuiz(QuizOuterClass.Empty.newBuilder().build());
        quizList = quizResponse.getAllQuizzesList();
    }

    private void startQuiz() {
        currentQuizIndex = 0;
        currentScore = 0;
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuizIndex >= quizList.size()) {
            showFinalScore();
            return;
        }

        currentQuiz = quizList.get(currentQuizIndex);
        questionLabel.setText(currentQuiz.getQuestion());

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(currentQuiz.getOptions(i));
            optionButtons[i].setSelected(false);
        }
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

        QuizOuterClass.PlayRequest playRequest = QuizOuterClass.PlayRequest.newBuilder()
                .setPlayerName(playerName)
                .setQuizId(currentQuiz.getId())
                .setSelectedAnswer(selectedAnswer)
                .build();

        QuizOuterClass.PlayResponse playResponse = blockingStub.play(playRequest);

        if (playResponse.getResponseCode() == 0) {
            resultLabel.setText("Correct!");
            currentScore++;
        } else {
            resultLabel.setText("Incorrect!");
        }

        currentQuizIndex++;
        displayNextQuestion();
    }

    private void showFinalScore() {
        finalScoreLabel.setText("Your final score: " + currentScore);
        showScorePage();
    }

    private void restartGame() {
        showRegistrationPage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizGameUI client = new QuizGameUI();
            client.setVisible(true);
        });
    }
}
