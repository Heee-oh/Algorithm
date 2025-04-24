import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static final long MOD = 1000000007;
    static final long[][] origin = {{1, 1}, {1, 0}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long[][] A = {{1, 1}, {1, 0}};


        System.out.println(pow(A, n-1)[0][0]);
    }


    private static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] result = new long[2][2];
        result[0][0] = (o1[0][0] * o2[0][0] + o1[0][1] * o2[1][0]) % MOD;
        result[0][1] = (o1[0][0] * o2[0][1] + o1[0][1] * o2[1][1]) % MOD;
        result[1][0] = (o1[1][0] * o2[0][0] + o1[1][1] * o2[1][0]) % MOD;
        result[1][1] = (o1[1][0] * o2[0][1] + o1[1][1] * o2[1][1]) % MOD;

        return result;
    }

    private static long[][] pow(long[][] A, long exp) {
        if (exp == 1 || exp == 0) {
            return A;
        }

        // 지수 절반인 행렬을 제곱한다.
        long[][] ret = pow(A, exp / 2);
        ret = multiply(ret, ret);

        // 홀수 지수일 경우 A^1을 곱해준다.
        if (exp % 2 == 1L) {
            ret = multiply(ret, origin);
        }

        return ret;

    }
}