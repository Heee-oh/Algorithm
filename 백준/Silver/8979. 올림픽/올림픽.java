import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    // 자신보다 더 잘한 나라 수 + 1이 등수
    // 공동 있음
    // 비어있는 등수가 있을 수 있음
//    static class Medal implements Comparable<Medal> {
//        int gold;
//        int silver;
//        int bronze;
//
//        public Medal(int gold, int silver, int bronze) {
//            this.gold = gold;
//            this.silver = silver;
//            this.bronze = bronze;
//        }
//
//        public String getMedal() {
//            return "" +gold + silver + bronze;
//        }
//
//        @Override
//        public int compareTo(Medal o) {
//            if (this.gold != o.gold) {
//                return o.gold - this.gold;
//            }
//
//            if (this.silver != o.silver) {
//                return o.silver - this.silver;
//            }
//
//            return o.bronze - this.bronze;
//        }
//    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeSet<String> ts = new TreeSet<>();
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();



        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] rank = new String[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int country = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            sb.append(gold).append(silver).append(bronze);
            rank[country] = sb.toString();

            int count = map.getOrDefault(sb.toString(), 0) + 1;
            map.put(sb.toString(), count);
            ts.add(sb.toString());

            sb.delete(0, sb.length());
        }

        String kMedal = rank[k];
        int cnt = 0;

        // 정렬된 트리셋에서 가장 큰 것(메달 순위가 높은 것)부터 꺼내서 공동 우승자들을 카운트한다.
        while (!ts.isEmpty()) {
            String key = ts.pollLast();
            if (kMedal.equals(key)) { // 등수를 알고싶은 k국가라면 자신보다 잘한 나라 수 + 1을 해서 반환한다.
                bw.write((cnt + 1) +  "");
            }
            cnt += map.get(key);
        }

        bw.flush();
        bw.close();
    }

}


