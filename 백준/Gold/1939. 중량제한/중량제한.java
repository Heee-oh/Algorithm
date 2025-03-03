import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {


    static int[][] graph;
    static int[] parent;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 섬 개수
        int m = Integer.parseInt(st.nextToken()); // 다리 개수

        graph = new int [m][3];
        parent = IntStream.range(0, n + 1).toArray(); // 부모

        // 섬 그래프로 초기화
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken()); // 섬1
            graph[i][1] = Integer.parseInt(st.nextToken()); // 섬2
            graph[i][2] = Integer.parseInt(st.nextToken()); // 비용
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 비용 내림차순 정렬
        Arrays.sort(graph, (o1, o2) -> o2[2] - o1[2]);

        for (int i = 0; i < m; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) { // 서로 다른 부모 즉, 사이클이 생기지 않는다면
                union(graph[i][0], graph[i][1]);
                if (find(parent[start]) == find(parent[end])) { // 시작과 끝점의 부모가 같다면 현재 비용이 이동가능한 무게 최댓값
                    min = graph[i][2];
                    break;
                }
            }
        }

        System.out.println(min);
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로압축
    }
}