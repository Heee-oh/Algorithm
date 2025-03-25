import java.io.*;
import java.util.*;

public class Main {

    // 슬라이딩 윈도우로 2개이하를 유지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] fruits = new int[n];

        for (int i = 0; i < n; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>(); // 과일 종류 카운팅 집합
        int[] cnt = new int[10]; // 현재 슬라이딩 윈도우에 있는 각 과일별 개수
        int max = 0;

        while (right < n) {
            if (set.size() <= 2) {
                set.add(fruits[right]);
                cnt[fruits[right]]++;
                right++;

            }

            if (set.size() > 2) {
                cnt[fruits[left]]--;
                if (cnt[fruits[left]] == 0) {
                    set.remove(fruits[left]);
                }

                left++;
            }

            max = Math.max(max, right - left);
        }

        bw.write(max + "");
        bw.flush();
        bw.close();
    }



}
