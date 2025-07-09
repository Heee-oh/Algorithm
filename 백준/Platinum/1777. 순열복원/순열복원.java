import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] tree, arr, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        ans = new int[n + 1];

        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        tree = new int[1 << (h + 1)];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, n);


        for (int i = n; i > 0; i--) {
            int idx = query(1, 1, n, arr[i]);
            ans[idx] = i;
        }

        for (int i = n; i > 0; i--) {
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static int init(int treeNode, int s, int e) {
        if (s == e) {
            return tree[treeNode] = 1;
        }

        int mid = (s + e) >>> 1;

        return tree[treeNode] = init(treeNode * 2, s, mid) + init(treeNode * 2 + 1, mid + 1, e);
    }

    private static int query(int treeNode, int s, int e, int cnt) {
        tree[treeNode]--;

        if (s == e) {
            return s;
        }
        int mid = (s + e) >>> 1;


        return cnt < tree[treeNode * 2] ?
                query(treeNode * 2, s, mid, cnt) :
                query(treeNode * 2 + 1, mid + 1, e, cnt - tree[treeNode * 2]);
    }



}