import java.io.*;
import java.util.*;

public class Main {
    static char[][] stars;
    static long a;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());


        // 올림 내림 해서 power 이 1이 될때 return 하고 이를 각각 곱한 뒤 모듈러 연산을 해준다.
        long answer = recursion(b);
        // 제곱근으로 나눠서 정복 ?
        // 루트 n 의 이상  이하로 정수형에 맞춰서 정복

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }



    private static long recursion(int pow) {

        if (pow == 0) return 1;

        if (pow % 2 == 1) return a * recursion(pow - 1) % c;
        else {
            long half = recursion(pow / 2) % c;
            return (half * half) % c;
        }

    }

    //        if (a >= Math.sqrt(Integer.MAX_VALUE) || Math.pow(a, pow) > Integer.MAX_VALUE) {
//        if (a >= Math.sqrt(Integer.MAX_VALUE) && pow >= Math.sqrt(Integer.MAX_VALUE)) {
//            int odd = (pow / 2) + pow % 2 == 0 ? 1 : 0;
//            int even = pow / 2;
//
//            return (recursion(odd) * recursion(even)) % c;
//
//        } else {
//
//            long tmp = 1;
//            for (int i = 0; i < pow; i++) {
//                tmp *= a;
//                tmp %= c;
//            }
//
//            return tmp;
//        }

}