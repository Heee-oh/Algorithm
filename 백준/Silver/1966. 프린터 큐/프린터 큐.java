import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int[] important = new int[10];
            TreeSet<Integer> ts = new TreeSet<>();
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                important[num]++; // 개수

                ts.add(num);
                q.add(new int[]{num, i});
            }

            int answer = 0;
            boolean flag = false;
            while (!ts.isEmpty()) {
                int last = ts.last();

                // 중요도 높은게 아직 있다면
                if (important[last] > 0) {
                    int size = q.size();

                    while (size-- > 0) {
                        int[] poll = q.poll();

                        // 해당 중요도 문서면 뽑아냄
                        if (poll[0] == last) {
                            important[last]--;
                            answer++;

                            // 찾는 문서라면 플래그 세우고 멈춤
                            if (poll[1] == M) {
                                flag = true;
                            }
                            break;

                        } else {
                            q.add(poll);
                        }
                    }

                    // 없다면 빼버림
                } else {
                    ts.pollLast();
                }


                if (flag) {
                    sb.append(answer + "\n");
                    break;
                }

            }

        }
        System.out.print(sb.toString());
    }

}

