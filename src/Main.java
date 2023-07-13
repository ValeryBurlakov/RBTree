import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        final RedBlackTree tree = new RedBlackTree();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Your input: ");
                try {
                    int value = Integer.parseInt(reader.readLine());
                    tree.add(value);
                    System.out.println("GG:");
                    tree.printTree();
                } catch (Exception ignored) {
                    System.out.println("Please repeat your ******* corrected input, we have numbers here:");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}