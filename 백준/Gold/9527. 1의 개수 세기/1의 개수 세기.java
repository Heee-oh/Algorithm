import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static BigInteger[] bigIntegers;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        bigIntegers = new BigInteger[64];
        arr = new long[63];

        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] * 2;
        }

        binary();


        BigInteger sum1 = getBigInteger(m);
        BigInteger sum2 = getBigInteger(n - 1);

        System.out.println(sum1.subtract(sum2));

    }

    private static BigInteger getBigInteger(long target) {
        String binaryStr = Long.toBinaryString(target);
        int length = binaryStr.length();
        int cnt = 0;
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < length; i++) {
            char c = binaryStr.charAt(i);
            if (c == '1') {

                long prefix = cnt * arr[length - i - 1];
                sum = sum.add(BigInteger.valueOf(prefix)).add(bigIntegers[length - i - 1]);
                cnt++;
            }
        }
        return sum;
    }

    private static void binary() {
        bigIntegers[0] = new BigInteger("1");
        bigIntegers[1] = new BigInteger("2");

        for (int i = 2; i <bigIntegers.length; i++) {
            int pow = i;
            int prefix = 0;

            BigInteger sum = new BigInteger("0");

            while (pow > 0) {
                pow--;
                // 이전 앞 1의 개수 * 현재 나오는 범위의 개수 + pow까지의 1의 개수
                sum = sum.add(BigInteger.valueOf((prefix * arr[pow]))).add(bigIntegers[pow]);
                prefix++;
            }
            sum = sum.add(BigInteger.valueOf(1L));
            bigIntegers[i] = sum;
        }
    }
}
