import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        sieveOfEratosthenes(n);

        int answer = 0;
        int sum = 0;

        int left = 0, right = 0;
        while (left <= right && right < prime.size()) {
            if (sum < n) {
                sum += prime.get(right);
                right++;

            } else if (sum > n) {
                sum -= prime.get(left);
                left++;

            }
            if (sum == n) {
                answer++;
                sum -= prime.get(left);
                left++;
            }

        }

        while (left < prime.size()) {
            sum -= prime.get(left);
            left++;
            if (sum == n) {
                answer++;
                break;
            }
        }



        bw.write(answer+"\n");
        bw.flush();
        bw.close();

    }

    private static void sieveOfEratosthenes(int max) {
        boolean[] isNotPrime = new boolean[max + 1];

        for (int i = 2; i * i <= max; i++) {
            if (isNotPrime[i]) continue; // 소수가 아니므로 할 필요없음

            // 소수의 배수들을 제외하는 과정
            for (int j = i * i; j <= max; j += i) {
                isNotPrime[j] = true;
            }
        }

        for (int i = 2; i <= max; i++) {
            if (!isNotPrime[i]) {
                prime.add(i);
            }
        }
    }

}
