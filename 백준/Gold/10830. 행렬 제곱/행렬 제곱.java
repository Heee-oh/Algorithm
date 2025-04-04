import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] poweredA; // 제곱된 A 행렬 저장
    static int[][] operand;  // 제곱된 A 행렬을 복사하여 자기 자신을 곱하도록 만들 피연산 행렬
    static int[][][] dp; // 제곱 연산 결과 행렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        poweredA = new int[n][n];
        operand = new int[n][n];

        int maxSize = (int)(Math.log(100000000000.0) / Math.log(2)) + 2; // B의 최댓값을 log 연산하여 2의 몇제곱까지인지 사이즈를 얻는다.

        dp = new int[maxSize][n][n];
        int[][] result = new int[n][n];


        // 행렬 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dp[1][i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // pow값에 따라 값을 저장
        for (int i = 1; i < dp.length; i++) {
            matrixPower(i, n);
        }

        int pos = (int)(Math.log(Long.highestOneBit(b)) / Math.log(2));

        cloneMatrix(dp[pos + 1], result);



        // 비트가 1인 것을 찾아서 그 제곱과 현재 누적합을 곱셈 연산
        String bit = Long.toString(b, 2);

        for (int i = 1; i < bit.length(); i++) {
            if (bit.charAt(i) == '1') {
                result = matrixPowerTwo(result, bit.length() - i, n);
            }
        }

        // twoPowIdx 에 b - twoPowIdx 를 연산하면 끝

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    private static int[][] matrixPowerTwo(int[][] result, int target, int n) {
        cloneMatrix(dp[target], poweredA);

        int[][] powered = new int[n][n];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    powered[i][k] = (powered[i][k] + (result[i][j] * poweredA[j][k] % 1000)) % 1000;
                }
            }
        }
        return powered;
    }

    private static void matrixPower(int pow, int n) {
        cloneMatrix(dp[pow - 1], poweredA);
        cloneMatrix(dp[pow - 1], operand);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[pow][i][k] = (dp[pow][i][k] + (poweredA[i][j] * operand[j][k] % 1000)) % 1000;
                }
            }
        }
    }

    private static void cloneMatrix(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i].clone();
        }
    }

}