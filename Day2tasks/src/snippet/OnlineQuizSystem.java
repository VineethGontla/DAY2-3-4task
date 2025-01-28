package snippet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OnlineQuizSystem {

    // User class to store user details
    static class User {
        String name;
        int score;

        public User(String name) {
            this.name = name;
            this.score = 0;  // User starts with a score of 0
        }

        public void incrementScore() {
            this.score++;
        }

        public int getScore() {
            return score;
        }
    }

    // Question class to represent each question in the quiz
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        // Check if the given answer is correct
        public boolean isCorrect(int answerIndex) {
            return answerIndex == correctAnswerIndex;
        }
    }

    // Quiz class to manage the quiz, questions, and user
    static class Quiz {
        List<Question> questions;
        User user;

        public Quiz(User user) {
            this.questions = new ArrayList<>();
            this.user = user;
        }

        // Add a question to the quiz
        public void addQuestion(String questionText, String[] options, int correctAnswerIndex) {
            Question question = new Question(questionText, options, correctAnswerIndex);
            questions.add(question);
        }

        // Take the quiz and calculate the user's score
        public void takeQuiz() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Quiz, " + user.name + "!");
            System.out.println("Please answer the following questions:\n");

            // Shuffle questions to make the quiz different each time
            Collections.shuffle(questions);

            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                System.out.println("Q" + (i + 1) + ": " + question.questionText);

                // Display options
                for (int j = 0; j < question.options.length; j++) {
                    System.out.println((j + 1) + ". " + question.options[j]);
                }

                // Get the user's answer
                System.out.print("Enter the number of your answer: ");
                int answer = scanner.nextInt();

                // Check if the answer is correct
                if (question.isCorrect(answer - 1)) {
                    user.incrementScore();
                }
            }

            System.out.println("\nQuiz completed! Your score is: " + user.getScore() + " out of " + questions.size());
        }
    }

    // Main method to create quiz and start it
    public static void main(String[] args) {
        // Create a user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        User user = new User(name);

        // Create a quiz for the user
        Quiz quiz = new Quiz(user);

        // Add questions to the quiz
        quiz.addQuestion("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2);
        quiz.addQuestion("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1);
        quiz.addQuestion("Who wrote 'Harry Potter'?", new String[]{"J.R.R. Tolkien", "J.K. Rowling", "George R.R. Martin", "Suzanne Collins"}, 1);
        quiz.addQuestion("What is the largest ocean on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3);

        // Start the quiz
        quiz.takeQuiz();
    }
}

