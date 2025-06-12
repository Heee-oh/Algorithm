import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] indexing; // 중앙 위치를 빠르게 찾기 위한 인덱싱
    static int[] inorder;
    static int[] postorder;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        inorder = new int[n + 1];
        postorder = new int[n + 1];
        indexing = new int[n + 1];
        StringTokenizer stInOrder = new StringTokenizer(br.readLine());
        StringTokenizer stPostOrder = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(stInOrder.nextToken());
            postorder[i] = Integer.parseInt(stPostOrder.nextToken());
            indexing[inorder[i]] = i;
        }

        // 이진트리의 최상 중앙, 왼쪽, 오른쪽 구하기
        createBinaryTree(1, n, 1, n);

        System.out.println(sb.toString());
    }

    static StringBuilder sb = new StringBuilder();

    private static void createBinaryTree(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;
        // 중앙 즉, 현재 최상위 부모
        int mid = postorder[postEnd];
        sb.append(mid).append(" ");

        int postSize = indexing[mid] - inStart;

        // 중앙 기준
        createBinaryTree(inStart, indexing[mid] - 1, postStart, postStart + postSize - 1); // 왼쪽
        createBinaryTree(indexing[mid] + 1, inEnd, postStart + postSize, postEnd - 1); // 오른쪽

    }
}
