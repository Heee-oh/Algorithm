import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

class Edges {
    HashSet<Integer> set;
    Edges() { set = new HashSet<>(); }
}

class Solution {

    public static int getMin(int[] dist, boolean[] vertices) {
        int min = Integer.MAX_VALUE;
        int mIdx = -1;

        for (int i = 1; i < vertices.length; i++) {
            if (!vertices[i] && dist[i] < min) {
                min = dist[i];
                mIdx = i;
            }
        }
        return mIdx;
    }   

    public int solution(int n, int[][] edge) {
        int answer = 0;

        Edges[] adj = new Edges[n + 1];
        boolean[] included = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // adj Max size : 400KB
        for (int[] e : edge) {
            if (adj[e[0]] == null) adj[e[0]] = new Edges();
            if (adj[e[1]] == null) adj[e[1]] = new Edges();
            adj[e[0]].set.add(e[1]);
            adj[e[1]].set.add(e[0]);
        }

        // root Node : vertex '1'
        dist[1] = 0;
        for (int i = 1; i <= n; i++) {
            int now = getMin(dist, included);
            included[now] = true;
            Iterator<Integer> it = adj[now].set.iterator();
            while (it.hasNext()) {
                int next = it.next();
                if (!included[next])
                    if (dist[next] > dist[now] + 1)
                        dist[next] = dist[now] + 1;
            }
        }

        int max = 0;
        for (int i = 1; i <=n; i++) {
            if (dist[i] > max) {
                answer = 1;
                max = dist[i];
            } else if (dist[i] == max) {
                answer++;
            }
        }

        return answer;
    }
}
