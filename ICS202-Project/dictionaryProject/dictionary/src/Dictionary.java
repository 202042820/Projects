import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary extends AVLTree {
    private AVLTree<String> avl;

    public Dictionary(String s) throws IOException, WordAlreadyExistsException, WordNotFoundException {
        this();
        addWord(s);
        list();
    }

    public Dictionary() {
        avl = new AVLTree<>();
    }

    public Dictionary(File f) throws IOException, WordAlreadyExistsException, WordNotFoundException {
        this();

        try {
            Scanner reader = new Scanner(f);
            String word = reader.nextLine();

            while (reader.hasNextLine()) {
                addWord(word);
                word = reader.nextLine();
            }
            addWord(word);
            System.out.println("dictionary loaded successfully.");
            list();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found!");
        }

    }

    public boolean addWord(String word) throws WordAlreadyExistsException {
        if (findWord(word))
            return false;

        avl.insertAVL(word);
        return true;
    }

    public boolean findWord(String s) {
        return avl.search(s);
    }

    public boolean deleteWord(String s) throws WordNotFoundException {
        if (!findWord(s))
            return false;

        avl.deleteAVL(s);
        return true;

    }

    public String[] findSimilar(String s) {
        String[] result = new String[53 * s.length() + 50];
        int len = s.length();
        int count = 0;
        // Check words with one less character
        for (int i = 0; i < len; i++) {
            String removed = s.substring(0, i) + s.substring(i + 1);
            result[count++] = removed;
        }

        // check words with the same length
        for (int i = 0; i < len; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != s.charAt(i)) {
                    String replaced = s.substring(0, i) + c + s.substring(i + 1);
                    result[count++] = replaced;
                }
            }
        }

        // Check words with one more character
        for (int i = 0; i <= len; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String extraLetterString = s.substring(0, i) + c + s.substring(i);
                result[count++] = extraLetterString;
            }
        }

        String[] resultOfSimilar = new String[result.length];
        count = 0;
        for (int i = 0; i < result.length; i++)
            if (result[i] != null && findWord(result[i]))
                resultOfSimilar[count++] = result[i];

        return resultOfSimilar;
    }

    public void save(File f) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        BTNode<String> root = avl.root;
        System.out.println(root.data);
        inOrderTraversal(root, writer);
        writer.flush();
        writer.close();
    }

    private void inOrderTraversal(BTNode<String> node, BufferedWriter writer) throws IOException {
        if (node == null)
            return;

        inOrderTraversal(node.left, writer);
        writer.write(node.data.toString() + "\n");
        inOrderTraversal(node.right, writer);
    }

    public void list() throws IOException, WordAlreadyExistsException, WordNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("------------------Dictionary------------------");
            System.out.println("[1] Add new word");
            System.out.println("[2] Search for word");
            System.out.println("[3] Remove word");
            System.out.println("[4] Search for similar words");
            System.out.println("[5] Save");
            System.out.println("[6] Exit");
            System.out.println("----------------------------------------------");
            System.out.print("Enter a number: ");

            int choice = input.nextInt();
            String word;

            switch (choice) {
                case 1:
                    try {
                        word = "empty";
                        while (!(word.equals("0"))) {
                            System.out.println("[0] Back to list");
                            System.out.print("add new word> ");
                            word = input.next();

                            if (word.equals("0"))
                                break;

                            if (!addWord(word))
                                throw new WordAlreadyExistsException();

                            System.out.println("word added successfully.");
                            System.out.println();
                        }
                    } catch (WordAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                 
                        word = "";
                        while (!(word.equals("0"))) {
                            System.out.println("[0] Back to list");
                            System.out.print("Check word> ");
                            word = input.next();

                            if (word.equals("0"))
                                break;

                           if (!findWord(word)){
                            System.out.println("Word not found.");
                            }
                        else 
                            System.out.println("Word found.");
                            System.out.println();
                        }
                        break;
                
                      
                    
            

                case 3:
                    try {
                        word = "";
                        while (!(word.equals("0"))) {
                            System.out.println("[0] Back to list");
                            System.out.print("Remove word> ");
                            word = input.next();

                            if (word.equals("0"))
                                break;

                            if (!deleteWord(word))
                                throw new WordNotFoundException();

                            System.out.println("word deleted successfully.");
                            System.out.println();
                        }
                    } catch (WordNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                word = "";
                while (!(word.equals("0"))) {
                    System.out.println("[0] Back to list");
                    System.out.print("find similar words of> ");
                    word = input.next();

                    if (word.equals("0"))
                        break;

                    String[] words = findSimilar(word);
                    for (String w : words)
                        if (w != null)
                            System.out.println(w);
                }
                break;

                case 5:
                    word = "";

                    System.out.print("Save updated dictionary(Y/N)> ");
                    word = input.next();
                    if (word.equalsIgnoreCase("N")) {
                        break;
                    } else if (word.equalsIgnoreCase("Y")) {
                        System.out.print("Enter filename> ");
                        word = input.next();
                        File name = new File(word);
                        save(name);
                        System.out.println("Dictionary saved successful.");
                    } else {
                        System.out.println("Wrong input");
                    }
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }

        input.close();
    }

    public static void main(String[] args) throws Exception {
        // ------------Case 1------------:
        Dictionary dict =new Dictionary();
        dict.list();

        // ------------Case 2------------:
        // Dictionary dict = new Dictionary("a");

        // ------------Case 3------------:
        // System.out.print("Enter filename> ");
        // Scanner input = new Scanner(System.in);
        // String fileName = input.nextLine();
        // File file = new File(System.getProperty("user.dir") + "/" + fileName );
        // Dictionary dict = new Dictionary(file);
        // input.close();

    }
}
