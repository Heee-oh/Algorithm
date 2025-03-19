import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        String first = words[0];


        // 알파벳에 따른 개수 세기
        int[][] alpha = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int idx = words[i].charAt(j) - 'A';
                alpha[i][idx]++;
            }
        }

        // 비교

        for (int i = 1; i < n; i++) {
            int difference = 0;
            if (first.equals(words[i])) continue;

            // 길이에 대한 체크
            if (words[i].length() < first.length() - 1
                    || first.length() + 1 < words[i].length()) {
                continue;
            }

            for (int j = 0; j < 26; j++) {
                difference += Math.abs(alpha[0][j] - alpha[i][j]);
            }

            if (difference > 2) {
                continue;
            }

            cnt++;
        }
        bw.write(cnt + "");
        bw.flush();
        bw.close();
    }



}
