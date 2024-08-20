import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());


        // 일단 에스트라뭐시기 체 로 소수 걸러내기

        recursion(0, 0);




        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


    private static void recursion(int depth,int prime) {
        if (depth == n) {

            sb.append(prime / 10).append("\n");
            return;
        }


        for (int i = 0; i < 10; i++) {
            if (isPrime(prime + i)) {
                recursion(depth + 1, (prime + i) * 10);
            }
        }
    }


    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;

        for (int i = 2; i < Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }



}