import java.util.Random;
import java.util.Scanner;

public class RandomNumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int totalAttempts = 0;
        int totalRounds = 0;

        System.out.println("Welcome to the Random Number Guessing Game!");

        do {
            int answer = random.nextInt(maxRange - minRange + 1) + minRange;
            int guess, attempts = 0;

            System.out.println("\nRound " + (totalRounds + 1));
            System.out.println("Guess the number between " + minRange + " and " + maxRange + ":");

            do {
                guess = getUserGuess(scanner);

                if (guess > answer) {
                    System.out.println("Too High! Try Again.");
                } else if (guess < answer) {
                    System.out.println("Too Low! Try Again.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                }

                attempts++;

            } while (guess != answer && attempts < maxAttempts);

            totalAttempts += attempts;
            totalRounds++;

            System.out.println("The correct number was: " + answer);
            displayRoundResults(attempts);

        } while (playAgain(scanner));

        displayGameResults(totalRounds, totalAttempts);

        scanner.close();
    }

    private static int getUserGuess(Scanner scanner) {
        System.out.print("Enter your guess: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    private static void displayRoundResults(int attempts) {
        System.out.println("Round Over!");
        System.out.println("Attempts taken: \n" + attempts);
    }

    private static boolean playAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private static void displayGameResults(int totalRounds, int totalAttempts) {
        System.out.println("\nGame Over!");
        System.out.println("Total Rounds: " + totalRounds);
        System.out.println("Total Attempts: " + totalAttempts);
        System.out.println("Average Attempts per Round: " + (double) totalAttempts / totalRounds);
        System.out.println("Thank you for playing!");
    }
}