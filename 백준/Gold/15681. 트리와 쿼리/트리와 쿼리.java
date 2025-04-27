import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int num;
        List<Node> child;

        public Node(int num, List<Node> child) {
            this.num = num;
            this.child = child;
        }

        public void addChild(Node node) {
            child.add(node);
        }
    }

    static Node[] tree;
    static int[] size;
    static boolean[] visited;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new Node[n + 1];
        size = new int[n + 1];
        visited = new boolean[n + 1];

        // 트리 구성
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (tree[u] == null) {
                tree[u] = new Node(u, new ArrayList<>());
            }

            if (tree[v] == null) {
                tree[v] = new Node(v, new ArrayList<>());
            }

            tree[u].addChild(tree[v]);
            tree[v].addChild(tree[u]);
        }


        // 루트 5를 기준으로 서브 트리 노드 개수 구하기
        countSubtreeNodes(tree[r]);


        // 정점 U를 루트로하는 서브트리에 속한 정점의 수
        for (int i = 0; i < q; i++) {
            int subTreeRoot = Integer.parseInt(br.readLine());
            sb.append(size[subTreeRoot]).append("\n");
        }


        System.out.print(sb.toString());
    }


    private static int countSubtreeNodes(Node root) {
        size[root.num] = 1; // 서브트리의 노드는 루트인 자신도 포함

        // 자식이 없다면 자기자신만 반환
        if (root.child.isEmpty()) {
            return size[root.num];
        }

        visited[root.num] = true;

        for (Node node : root.child) {
            if (!visited[node.num]) {
                size[root.num] += countSubtreeNodes(node);
            }
        }
        
        return size[root.num];
    }
}