import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    static int[] segMin;
    static int[] segMax;

    static int[] arr;

    // 계산은 long

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        segMin = new int[4 * n];
        segMax = new int[4 * n];
        arr = new int[n + 1];



        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        build(1, 1, n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] result = query(1, 1, n, a, b);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }

        System.out.print(sb.toString());
    }
    private static int[] query(int node, int s, int e, int l, int r) {
        if (r < s || e < l) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        if (l <= s && e <= r) return new int[]{segMin[node], segMax[node]};

        int mid = (s + e) / 2;
        int[] left = query(node * 2, s, mid, l, r);
        int[] right = query(node * 2 + 1, mid + 1, e, l, r);

        return new int[]{
                Math.min(left[0], right[0]),
                Math.max(left[1], right[1])
        };

    }


    private static void build(int node, int s, int e) {
        if (s == e) {
            segMax[node] = segMin[node] = arr[s];
            return;
        }

        int mid = (s + e) / 2;
        build(node * 2, s, mid);
        build(node * 2 + 1, mid + 1, e);

        segMin[node] = Math.min(segMin[node * 2], segMin[node * 2 + 1]);
        segMax[node] = Math.max(segMax[node * 2], segMax[node * 2 + 1]);
    }


}
