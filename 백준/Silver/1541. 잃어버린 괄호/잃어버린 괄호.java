import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();

        String s = br.readLine();
        String[] number = s.split("[+-]");
        String sign = s.replaceAll("[0-9]", "");
        Stack<Character> stack = new Stack<>();
        int first = 0;

        if (sign.contains("-")) {
            first = sign.indexOf("-");
        }

        for (String string : number) {
            list.add(Integer.parseInt(string));
        }

        for (int i = 0; i < sign.length(); i++) {
            if (sign.charAt(i) == '+') {
                int value = list.get(i) + list.get(i + 1);
                list.set(i, 0);
                list.set(i+1, value);
            } else {
                stack.push('-');
            }

        }

        int sum = 0;
        for (int i = first + 1; i < list.size(); i++) {
            sum += list.get(i);
        }

        if (!stack.isEmpty()) {
            sum *= -1;
        }


        System.out.println(sum + list.get(first));

    }
}