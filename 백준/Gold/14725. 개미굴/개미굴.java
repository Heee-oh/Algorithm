import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] input = new String[n];
        Set<String>[] levels = new Set[16];

        for (int i = 0; i < 16; i++) {
            levels[i] = new HashSet<>();
        }

        for (int i = 0; i < n; i++) {
            input[i] = br.readLine();

            if (Character.isDigit(input[i].charAt(1))) {
                input[i] = input[i].substring(3);
            } else {
                input[i] = input[i].substring(2);
            }

        }

        Arrays.sort(input);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringBuilder check = new StringBuilder();
            int idx = 0;
            for (String str : input[i].split(" ")) {
                check.append(str);

                if (!levels[idx].contains(check.toString())) {
                    levels[idx].add(check.toString());
                    sb.append("--".repeat(idx)).append(str);
                    sb.append("\n");
                }
                idx++;
            }
        }

        System.out.print(sb.toString());


    }

}
