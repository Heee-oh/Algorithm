import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 신발끈 정리
        long[] a = new long[n + 1];
        long[] b = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st.nextToken());
        }

        // 처음 정점을 또 추가
        a[a.length - 1] = a[0];
        b[b.length - 1] = b[0];


        double result = (double) Math.abs(multiply(n, a, b) - multiply(n, b, a)) / 2;
        System.out.println(String.format("%.1f", result));

    }

    private static long multiply(int n, long[] a, long[] b) {
        long bSum = 0;
        for (int i = 0; i < n; i++) {
            bSum += a[i + 1] * b[i];
        }
        return bSum;
    }

}