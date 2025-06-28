import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        Map<String, Node> map;

        public Node() {
            this.map = new TreeMap<>();
        }

        public Node addChild(String child) {
            Node childNode = new Node();
            map.putIfAbsent(child, childNode); // 존재하면 존해하는 키에 대한 value를 반환, 아니라면 새로 생성후 반환
            return map.get(child);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Node root = new Node();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            Node currentNode = root;
            for (int j = 0; j < cnt; j++) {
                String feed = st.nextToken();
                // 다음 노드로 이동
                currentNode = currentNode.addChild(feed);
            }
        }

        dfs(root, 0);
        System.out.println(sb.toString());

    }

    static StringBuilder sb = new StringBuilder();
    private static void dfs(Node root, int level) {
        if (root.map.isEmpty()) {
            return;
        }

        for (String child : root.map.keySet()) {
            Node current = root.map.get(child);
            sb.append(("--").repeat(level)).append(child).append("\n");
            dfs(current, level + 1);
        }
    }

}
