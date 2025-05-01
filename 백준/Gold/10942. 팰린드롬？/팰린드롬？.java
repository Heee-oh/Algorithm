import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            // 투 포인터로 s, e 지점에서 안쪽으로 모이는 형태로 비교한다.
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            boolean flag = false;

            do {
                if (seq[start] != seq[end]) {
                    flag = true;
                    break;
                }

                start++;
                end--;
            } while (start <= end);

            if (flag) {
                sb.append("0");
            } else {
                sb.append("1");
            }
            sb.append("\n");
        }


        System.out.print(sb.toString());
    }

}