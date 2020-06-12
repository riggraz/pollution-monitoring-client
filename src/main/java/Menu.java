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

            choice = in.nextInt();
        } while (choice < 0 && choice > 3);

        // get (optional) n
        if (choice == 2 || choice == 3) {
            // get additional parameter
            while (n <= 0) {
                System.out.print("Insert n: ");
                n = in.nextInt();
            }
        }

        // build [choice, n] array
        int[] userInput = {choice, n};

        return userInput;
    }
}
