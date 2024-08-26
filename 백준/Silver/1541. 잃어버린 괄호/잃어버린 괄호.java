import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String expression = br.readLine();
        String exp = expression.replaceAll("[0-9]", "");
        int[] num = new int[exp.length() + 1];
        int idx = 0;

        for (String n : expression.split("[-+]")) {
            num[idx++] = Integer.parseInt(n);
        }

        int sum = num[0], tmp = 0;
        boolean minus = false;

        for (int i = 1; i < num.length; i++) {
            if (exp.charAt(i - 1) == '-' ) {
                minus = true;
            }
            if (minus) {
                sum -= num[i];
            } else {
                sum += num[i];
            }
        }


        bw.write(sum +"");
        bw.flush();
        bw.close();
    }
}