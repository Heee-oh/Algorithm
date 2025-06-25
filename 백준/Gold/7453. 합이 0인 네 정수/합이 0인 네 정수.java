import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[n * n];
        int[] CD = new int[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long cnt = 0;
        int abPointer = 0, cdPointer = n * n - 1;

        while (abPointer < AB.length && cdPointer >= 0) {

            int cdSum = CD[cdPointer];
            int abSum = AB[abPointer];
            int sum = abSum + cdSum;

            // ABCD 합이 0일 경우
            if (sum == 0) {
                // AB에서 같은 값 개수를 센다.
                long abCnt = 0;
                while (abPointer < AB.length && AB[abPointer] == abSum) {
                    abCnt++;
                    abPointer++;
                }

                // CD에서도 같은 값 개수를 센다.
                long cdCnt = 0;
                while (cdPointer >= 0 && CD[cdPointer] == cdSum) {
                    cdCnt++;
                    cdPointer--;

                }

                // 합이 0인 같은 수들의 개수를 곱한다.
                cnt += abCnt * cdCnt;
                
            } else if (sum < 0) {
                abPointer++;
            } else {
                cdPointer--;
            }

        }


        System.out.println(cnt);
    }






}
