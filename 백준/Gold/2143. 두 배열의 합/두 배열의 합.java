import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static Map<Integer, Long> aPrefixMap = new HashMap<>();
    static Map<Integer, Long> bPrefixMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        // A배열 초기화
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // B배열 초기화
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 Map 생성
        addPrefixSumToMap(n, A, true);
        addPrefixSumToMap(m, B, false);

        long answer = 0;
        for (int prefix : bPrefixMap.keySet()) {
            int findPrefix = T - prefix;

            answer += bPrefixMap.get(prefix) * aPrefixMap.getOrDefault(findPrefix, 0L);
        }

        System.out.println(answer);
    }

    private static void addPrefixSumToMap(int len, int[] arr, boolean isA) {
        for (int i = 0; i < len; i++) {
            int sum = 0;

            for (int j = i; j < len; j++) {
                sum += arr[j];
                if (isA) {
                    aPrefixMap.put(sum, aPrefixMap.getOrDefault(sum, 0L) + 1L);
                } else {
                    bPrefixMap.put(sum, bPrefixMap.getOrDefault(sum, 0L) + 1L);
                }
            }
        }
    }

}
