import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> op = new Stack<>();
        String inorder = br.readLine();

        for (int i = 0; i < inorder.length(); i++) {

            char c = inorder.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                sb.append(c);

                // ( 면 일단 담는다.
            } else if (c == '(') {
                op.push(c);


                // (라면 ()안에 남은 연산자들을 꺼내서 출력하고, (를 제거한다.
            } else if (c == ')') {
                while (!op.isEmpty() && op.peek() != '(') {
                    sb.append(op.pop());
                }

                if (!op.isEmpty()) {
                    op.pop();
                }

                // 연산자들은 stack의 top위치 연산자가 현재 들어오는 연산자보다 우선순위가 같거나 크다면 다 꺼낸다.
            } else {
                while (!op.isEmpty() && getPrecedence(op.peek()) >= getPrecedence(c)) {
                    sb.append(op.pop());
                }

                // 현재 연산자를 저장한다.
                op.push(c);
            }

        }

        //나머지 연산자 다 출력
        while (!op.isEmpty()) {
            sb.append(op.pop());
        }

        System.out.println(sb.toString());
    }


    private static int getPrecedence(char c) {
        if (c == '*' || c == '/') return 2;
        if (c == '+' || c == '-') return 1;
        return 0;
    }
}
