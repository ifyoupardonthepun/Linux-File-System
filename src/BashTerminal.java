// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #5
// CSE214
// R.04 James Finn

import java.util.*;
public class BashTerminal {
    // Program begins here with the main method

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        DirectoryTree dt = new DirectoryTree();
        String[] cmd;
        System.out.println("Starting bash terminal.");
        while (true) {
            try {
                System.out.print("[112693183@System32]: $ "); //Delete your System32 to get instant victory in league ahaha.
                cmd = stdin.nextLine().trim().replaceAll("\\s+", " ").split(" ");
                switch (cmd[0].toLowerCase()) {
                    case "pwd":
                        System.out.println(dt.presentWorkingDirectory());
                        break;

                    case "ls":
                        try {
                            if (cmd.length == 2) {
                                if (cmd[1].equalsIgnoreCase("-R")) {
                                    dt.printDirectoryTree();
                                }
                            } else if (cmd.length == 1) {
                                System.out.println(dt.listDirectory());
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: Invalid parameters.");
                        }
                        break;

                    case "cd":
                        if (cmd.length == 2) {
                            if (cmd[1].equals("/")) {
                                dt.resetCursor();
                            } else {
                                try {
                                    dt.changeDirectory(cmd[1]);
                                } catch (NotADirectoryException e) {
                                    System.out.println("ERROR: Cannot change directory into a file.");
                                } catch (NoSuchDirectoryException e) {
                                    System.out.printf("ERROR: No such directory named '%s'.\n", cmd[1]);
                                } catch (IllegalArgumentException e){
                                    System.out.print("ERROR: Invalid parameters.");
                                }
                            }
                        }
                        break;

                    case "mkdir":
                        if (cmd.length > 2) {
                            throw new IllegalArgumentException("ERROR: The name of the directory"
                                    + " can not have any spaces or forward slashes.");
                        }
                        if (cmd.length == 1) {
                            throw new IllegalArgumentException("ERROR: Name not provided for directory.");
                        }
                        try {
                            dt.makeDirectory(cmd[1]);
                        }catch (FullDirectoryException e) {
                            System.out.println("ERROR: Present directory is full.");
                        }
                        break;

                    case "touch":
                        try {
                            dt.makeFile(cmd[1]);
                        } catch(IllegalArgumentException e) {
                            System.out.println("ERROR: Invalid name.");
                        } catch (FullDirectoryException e) {
                            System.out.println("ERROR: Present directory is full.");
                        }
                        break;

                    case "exit":
                        System.out.println("Program terminating normally");
                        System.exit(0);
                        break;

                    default:
                        throw new IllegalArgumentException("ERROR: Command not found.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}