import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String num = br.readLine();
        int N = Integer.parseInt(num);

        //각자리수 + M = N
        // N을 만드는 가장 작은 분해합(생성자) 구하기
        // 생성자의 길이 * 9 는 각 자리수의 최대 합이다.
        for (int i = 1; i <= N; i++) {

                int sum = i;
                int tmp = i;
                while (tmp != 0) {
                    sum += tmp % 10;
                    tmp /= 10;
                }

                if (sum == N) {
                    bw.write(i + "\n");
                    bw.flush();
                    bw.close();
                    return;
                }


        }
        bw.write("0");
        bw.flush();
        bw.close();

    }
}