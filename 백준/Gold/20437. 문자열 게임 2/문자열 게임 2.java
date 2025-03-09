import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T--> 0) {
            char[] w = br.readLine().toCharArray();
            int k = Integer.parseInt(br.readLine());
            ArrayList<Integer>[] list = new ArrayList[26];

            for (int i = 0; i < 26; i++) {
                list[i] = new ArrayList<>();
            }

            // 각 문자에 대한 인덱스 저장
            for (int i = 0; i < w.length; i++) {
                list[w[i] - 'a'].add(i);
            }

            int max = -1;
            int min = Integer.MAX_VALUE;
            // 3. 가장 짧은거는 중앙에서부터 투포인터 탐색
            // 4. 가장 긴 거는 양 끝에서부터 탐색
            for (int i = 0; i < list.length; i++) {
                if (list[i].size() < k) continue; // k개 미만이면 조건에 만족하지 않음

                // 각 문자의 k개씩 묶어서 거리를 재고, min, max값 탐색
                for (int j = 0; j < list[i].size() - k + 1; j++) {
                    int length = list[i].get(j + k - 1) - list[i].get(j) + 1;
                    max = Math.max(max, length);
                    min = Math.min(min, length);
                }
            }

            if (max == -1 && min == Integer.MAX_VALUE) {
                sb.append("-1\n");
            } else {
                sb.append(min).append(" ").append(max).append("\n");
            }
        }



        bw.write(sb.toString() + "");
        bw.flush();
        bw.close();
    }

}