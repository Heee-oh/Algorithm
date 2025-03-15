import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String boomStr = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if(stack.size() >= boomStr.length()
                    && str.charAt(i) == boomStr.charAt(boomStr.length() - 1)) {
                int tmp = boomStr.length();
                while (tmp-->0) {
                    sb.append(stack.pop());
                }

                // 폭발 문자열이 아니면 다시 스택에 넣음
                if (!sb.reverse().toString().equals(boomStr)) {
                    for (int j = 0; j < sb.length(); j++) {
                        stack.push(sb.charAt(j));
                    }
                }

                sb.delete(0, sb.length());
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        bw.write(sb.length() == 0 ? "FRULA" : sb.reverse().toString());
        bw.flush();
        bw.close();
    }

}
