import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수

        int[] check = new int[n + 1];
        ArrayList<Integer>[] list = new ArrayList[n + 1];


        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            list[front].add(back);
            check[back]++; // 진입 차수 더하기
        }

        // 0을 처음 시작으로
        for (int i = 1; i < check.length; i++) {
            if (check[i] == 0) {
                list[0].add(i);
                check[i]++;
            }
        }

        q.add(0);
        while (!q.isEmpty()) {
            int seq = q.poll();

            for (int i = 0; i < list[seq].size(); i++) {
                int tmp = list[seq].get(i);
                check[tmp]--;

                if (check[tmp] == 0 ) {
                    q.add(tmp);
                    bw.write(tmp + " ");
                }
            }
        }

        bw.flush();
        bw.close();
    }

}