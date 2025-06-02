import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        while (true) {
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            }

            char[] ch = str.toCharArray();
            boolean check = true;

            for (int i = 0; i < str.length(); i++) {
                if (ch[i] == '(' || ch[i] == '[') {
                    stack.push(ch[i]);

                } else if (ch[i] == ')' || ch[i] == ']') {

                    if (!stack.isEmpty()
                            && (stack.peek() == '(' && ch[i] ==')'
                            || stack.peek() == '[' && ch[i] ==']')) {
                        stack.pop();
                    } else {
                        check = false;
                        break;
                    }
                }

            }


            if (stack.isEmpty() && check) sb.append("yes");
            else sb.append("no");
            sb.append("\n");

            stack.clear();
        }

        System.out.println(sb.toString());
    }

}