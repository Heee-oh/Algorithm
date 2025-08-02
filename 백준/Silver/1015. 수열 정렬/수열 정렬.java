import java.io.*;
import java.util.*;

public class Main {



    static int[] num = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] sorted = new int[n];
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sorted[i] = arr[i];
        }

        Arrays.fill(answer, -1);
        Arrays.sort(sorted);

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (sorted[j] == arr[i]) {
                    int idx = j;

                    while (idx < n && visited[idx]) {
                        idx++;
                    }

                    visited[idx] = true;
                    answer[i] = idx;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString().trim());



    }
}
