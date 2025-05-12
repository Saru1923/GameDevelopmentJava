import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        String[] options = {"Rock", "Paper", "Scissors"};
        String playAgain = "yes"; // Initialize playAgain with a default value

        do {
            System.out.println("Enter your choice (Rock, Paper, Scissors): ");
            String userChoice = scanner.nextLine().trim();

            // Validate user input
            if (!isValidChoice(userChoice, options)) {
                System.out.println("Invalid choice! Please choose Rock, Paper, or Scissors.");
                continue;
            }

            // Generate computer choice
            int computerIndex = random.nextInt(3);
            String computerChoice = options[computerIndex];
            System.out.println("Computer chose: " + computerChoice);

            // Determine the winner
            String result = determineWinner(userChoice, computerChoice);
            System.out.println(result);

            // Ask if the user wants to play again
            System.out.println("Do you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().trim().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    private static boolean isValidChoice(String choice, String[] options) {
        for (String option : options) {
            if (option.equalsIgnoreCase(choice)) {
                return true;
            }
        }
        return false;
    }

    private static String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equalsIgnoreCase(computerChoice)) {
            return "It's a tie!";
        }

        switch (userChoice.toLowerCase()) {
            case "rock":
                return computerChoice.equalsIgnoreCase("scissors") ? "You win!" : "You lose!";
            case "paper":
                return computerChoice.equalsIgnoreCase("rock") ? "You win!" : "You lose!";
            case "scissors":
                return computerChoice.equalsIgnoreCase("paper") ? "You win!" : "You lose!";
            default:
                return "Invalid choice!";
        }
    }
}