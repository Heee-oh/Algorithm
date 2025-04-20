import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int f = Integer.parseInt(br.readLine());

        //n의 마지막 두자리를 가능한 가장 작게, 나눠떨어지도록 변경
        // 100으로 나머지연산해서 뒤에 두자리 수를 구하고 이 수를 00~99까지 변경

        int remainder = n % 100;

        for (int i = 0; i < 100; i++) {
            int newN = n - remainder + i;
            if (newN % f == 0) {
                int lastTwoN = newN % 100;
                System.out.println(lastTwoN < 10 ? "0" + lastTwoN : lastTwoN);
                return;
            }

        }
    }
}
