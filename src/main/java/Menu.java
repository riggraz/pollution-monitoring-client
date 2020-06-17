import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner in;

    public Menu() {
        in = new Scanner(System.in);
    }

    public int[] getUserInput() {
        int choice = -1;
        int n = -1;

        // get choice
        do {
            System.out.println(
                    "\nChoose one of the following option:\n\n"
                    + "1. Get number of nodes in network\n"
                    + "2. Get last n measurements\n"
                    + "3. Get standard deviation and average of last n measurements\n"
                    + "0. Exit\n"
            );
            System.out.print("Your choice: ");

            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Illegal input, please try again.");
                in.next();
                choice = -1;
            }
        } while (choice < 0 && choice > 3);

        // get (optional) n
        if (choice == 2 || choice == 3) {
            while (true) {
                System.out.print("Insert n: ");

                try {
                    n = in.nextInt();

                    if (n <= 0) {
                        System.err.println("n must be strictly positive.");
                        continue;
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Illegal input, please try again.");
                    in.next();
                    n = -1;
                }
            }
        }

        // build [choice, n] array
        int[] userInput = {choice, n};

        return userInput;
    }
}
