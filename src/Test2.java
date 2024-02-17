import MerklesPuzzles.Alice;
import MerklesPuzzles.Bob;
import MerklesPuzzles.Eve;

import java.util.Arrays;
import java.util.Scanner;

public class Test2 {
    public static void main(String args[]){
        Alice alice = new Alice();
        Bob bob = new Bob();
        Eve eve = new Eve();
        Scanner scanner = new Scanner(System.in);
        boolean validFlag = false;
        String[] commands = {"Alice_createPuzzles[K][N]", "Bob_choosePuzzle", "Alice_findKey[number]", "Eve_findKey[number]", "fullTest[K][N]", "Exit"};
        System.out.println("Enter one of the following commands: ");
        printCommands();
        while(true){
            String user_command = scanner.nextLine();
            if(Arrays.asList(commands).contains(user_command)){
                if(user_command.equals(commands[0])){
                    System.out.println("Choose K:");
                    int K = scanner.nextInt();
                    System.out.println("Choose N:");
                    int N = scanner.nextInt();
                    alice.createPuzzles(N, K);
                    validFlag = true;
                    printCommands();
                } else if(!validFlag && !user_command.equals(commands[4])){
                    System.out.println("You need to run the first command first.");
                    printCommands();
                } else if(user_command.equals(commands[1])) {
                    String[] bob_res = bob.choosePuzzle(alice.getPuzzles());
                    System.out.println("The serial number is: " + bob_res[0] + ".\nThe key is: " + bob_res[1] + ".");
                    printCommands();
                } else if(user_command.equals(commands[2])) {
                    System.out.println("Choose a Serial Number:");
                    String alice_serial_number = scanner.nextLine();
                    String[] alice_res = alice.findKey(Integer.parseInt(alice_serial_number, 2));
                    System.out.println("The key is: " + alice_res[2] + ".\nThe number of comparisons is " + alice_res[0] + ".");
                    printCommands();
                } else if(user_command.equals(commands[3])){
                    System.out.println("Choose a Serial Number:");
                    String eve_serial_number = scanner.nextLine();
                    String[] eve_res = eve.findKey(alice.getPuzzles(), eve_serial_number);
                    System.out.println("Eve found that for serial_number that bob chose: " + eve_res[0] + ".\nThe corresponding key is: " + eve_res[1] + ".\nThe number of comparisons for eve is " + eve_res[2]);
                    printCommands();
                } else if(user_command.equals(commands[4])){
                    System.out.println("Choose K:");
                    int K = scanner.nextInt();
                    System.out.println("Choose N:");
                    int N = scanner.nextInt();
                    alice.createPuzzles(N, K);
                    validFlag = true;
                    String[] bob_res = bob.choosePuzzle(alice.getPuzzles());
                    System.out.println("The serial number is: " + bob_res[0] + ".\nThe key is: " + bob_res[1]);
                    String[] alice_res = alice.findKey(Integer.parseInt(bob_res[0], 2));
                    System.out.println("Alice:\nThe serial number is: " + Integer.toBinaryString(Integer.parseInt(alice_res[1])) + ".\nThe key is: " + alice_res[2] + ".\nThe number of comparisons is " + alice_res[0] + ".");
                    String[] eve_res = eve.findKey(alice.getPuzzles(), bob_res[0]);
                    System.out.println("Eve:\nEve found that for the serial number that bob chose: " + eve_res[0] + ".\nThe corresponding key is: " + eve_res[1] + ".\nThe number of comparisons for eve is " + eve_res[2] + ".");
                    break;
                } else if(user_command.equals(commands[5])){
                    break;
                } else{
                    System.out.println("Enter a valid command from this list.");
                    printCommands();
                }
            } else {
                System.out.println("Enter a valid command from this list.");
                printCommands();
            }
        }
    }

    public static void printCommands(){
        System.out.println("1. Alice_createPuzzles[K][N].");
        System.out.println("2. Bob_choosePuzzle.");
        System.out.println("3. Alice_findKey[number].");
        System.out.println("4. Eve_findKey[number].");
        System.out.println("5. fullTest[K][N].");
        System.out.println("6. Exit.");
    }
}
