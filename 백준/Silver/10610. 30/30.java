import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String num = br.readLine();
        String[] numbers = num.split("");

        int sum = 0;
        boolean flag = false;
        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            if (n == 0) flag = true;
            sum += n;
        }

        if (sum % 3 == 0 && flag) {
            Arrays.sort(numbers, String::compareTo);
            for (int i = numbers.length - 1; i >= 0; i--) {
                bw.write(numbers[i]);
            }
        } else {
            bw.write("-1\n");
        }

        bw.flush();
        bw.close();
    }

}