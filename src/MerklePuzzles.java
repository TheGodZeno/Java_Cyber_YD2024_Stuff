import MerklesPuzzles.Alice;
import MerklesPuzzles.Bob;
import MerklesPuzzles.Eve;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MerklePuzzles {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^([a-zA-Z_]+)\\[(\\d+)\\]\\[(\\d+)\\]$");

    public static void main(String args[]) {
        Alice alice = new Alice();
        Bob bob = new Bob();
        Eve eve = new Eve();
        Scanner scanner = new Scanner(System.in);

        String[] commands = {"Alice_createPuzzles[K][N]", "Bob_choosePuzzle", "Alice_findKey[number]", "Eve_findKey[number]", "fullTest[K][N]", "Exit"};
        boolean validFlag = false;

        System.out.println("Enter one of the following commands: ");
        printCommands();

        while (true) {
            String userCommand = scanner.nextLine();

            Matcher matcher = COMMAND_PATTERN.matcher(userCommand);
            if (matcher.matches()) {
                String command = matcher.group(1);
                int arg1 = Integer.parseInt(matcher.group(2));
                int arg2 = Integer.parseInt(matcher.group(3));

                switch (command) {
                    case "Alice_createPuzzles":
                        alice.createPuzzles(arg2, arg1);
                        validFlag = true;
                        break;

                    case "Bob_choosePuzzle":
                        if (!validFlag) {
                            System.out.println("You need to run the first command first.");
                            break;
                        }
                        String[] bobRes = bob.choosePuzzle(alice.getPuzzles());
                        System.out.println("The serial number is: " + bobRes[0] + ".\nThe key is: " + bobRes[1] + ".");
                        break;

                    case "Alice_findKey":
                        System.out.println("Choose a Serial Number:");
                        try {
                            int aliceSerialNumber = scanner.nextInt();
                            String[] aliceRes = alice.findKey(aliceSerialNumber);
                            System.out.println("The key is: " + aliceRes[2] + ".\nThe number of comparisons is " + aliceRes[0] + ".");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        } finally {
                            scanner.nextLine(); // Consume the remaining newline
                        }
                        break;

                    case "Eve_findKey":
                        System.out.println("Choose a Serial Number:");
                        try {
                            int eveSerialNumber = scanner.nextInt();
                            String[] eveRes = eve.findKey(alice.getPuzzles(), Integer.toBinaryString(eveSerialNumber));
                            System.out.println("Eve found that for serial_number that bob chose: " + eveRes[0] + ".\nThe corresponding key is: " + eveRes[1] + ".\nThe number of comparisons for eve is " + eveRes[2]);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        } finally {
                            scanner.nextLine(); // Consume the remaining newline
                        }
                        break;

                    case "fullTest":
                        alice.createPuzzles(arg2, arg1);
                        validFlag = true;
                        String[] bobRes_2 = bob.choosePuzzle(alice.getPuzzles());
                        System.out.println("The serial number is: " + bobRes_2[0] + ".\nThe key is: " + bobRes_2[1]);
                        String[] aliceRes = alice.findKey(Integer.parseInt(bobRes_2[0], 2));
                        System.out.println("Alice:\nThe serial number is: " + Integer.toBinaryString(Integer.parseInt(aliceRes[1])) + ".\nThe key is: " + aliceRes[2] + ".\nThe number of comparisons is " + aliceRes[0] + ".");
                        String[] eveRes = eve.findKey(alice.getPuzzles(), bobRes_2[0]);
                        System.out.println("Eve:\nEve found that for the serial number that bob chose: " + eveRes[0] + ".\nThe corresponding key is: " + eveRes[1] + ".\nThe number of comparisons for eve is " + eveRes[2] + ".");
                        return;

                    case "Exit":
                        return;

                    default:
                        System.out.println("Enter a valid command from this list.");
                }
                printCommands();
            } else {
                System.out.println("Enter a valid command from this list.");
                printCommands();
            }
        }
    }

    public static void printCommands() {
        System.out.println("1. Alice_createPuzzles[K][N].");
        System.out.println("2. Bob_choosePuzzle.");
        System.out.println("3. Alice_findKey[number].");
        System.out.println("4. Eve_findKey[number].");
        System.out.println("5. fullTest[K][N].");
        System.out.println("6. Exit.");
    }
}
