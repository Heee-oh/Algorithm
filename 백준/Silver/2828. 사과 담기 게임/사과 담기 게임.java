import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int appleCount = Integer.parseInt(br.readLine());

        boolean[] arr = new boolean[N+1];

        // 처음 1~M 칸 바구니

        int left = 1;
        int right = M;
        int moveCount = 0;
        //
        for (int i = 0; i < appleCount; i++) {
            int apple = Integer.parseInt(br.readLine());
            if (apple > right) {
                int tmp = (apple - right);
                moveCount += tmp;
                right += tmp;
                left += tmp;

            } else if (apple < left) {
                int tmp = (left - apple);
                moveCount += tmp;
                right -= tmp;
                left -= tmp;
            }

        }
        System.out.println(moveCount);


    }

}