import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int t = 0; t < n; t++) {
            int num = Integer.parseInt(br.readLine());
            List<int[]> list = new ArrayList<>();

            // 모든 약수 쌍 수집 (1 ~ sqrt(num) 포함)
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    int a = i;
                    int b = num / i;
                    list.add(new int[]{a, b});
                }
            }

            boolean isNasty = false;

            // nasty 조건 검사
            for (int i = 0; i < list.size(); i++) {
                int a = list.get(i)[0];
                int b = list.get(i)[1];
                int diff = Math.abs(a - b);

                for (int j = 0; j < list.size(); j++) {
                    if (i == j) continue;
                    int x = list.get(j)[0];
                    int y = list.get(j)[1];
                    int sum = x + y;

                    if (diff == sum) {
                        isNasty = true;
                        break;
                    }
                }

                if (isNasty) break;
            }

            sb.append(num + (isNasty ? " is nasty\n" : " is not nasty\n"));
        }

        System.out.print(sb.toString());
    }
}
