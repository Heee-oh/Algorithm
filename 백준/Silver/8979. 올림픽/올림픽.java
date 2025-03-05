import java.io.*;
import java.util.*;

public class Main {

    // 자신보다 더 잘한 나라 수 + 1이 등수
    // 공동 있음
//     비어있는 등수가 있을 수 있음
    static class Medal implements Comparable<Medal> {
        int countryNum;
        int gold;
        int silver;
        int bronze;
        String total;

        public Medal(int countryNum, int gold, int silver, int bronze) {
            this.countryNum = countryNum;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
            total = "" + gold + silver + bronze;
        }


        @Override
        public int compareTo(Medal o) {
            if (this.gold != o.gold) {
                return o.gold - this.gold;
            }

            if (this.silver != o.silver) {
                return o.silver - this.silver;
            }

            return o.bronze - this.bronze;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Medal> pq = new PriorityQueue<>();
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());



        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int country = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            pq.add(new Medal(country, gold, silver, bronze));
        }

        String answer = "";
        while ((!pq.isEmpty())) {
            Medal nextCountry = pq.poll();

            ArrayList<Integer> list = map.getOrDefault(nextCountry.total, new ArrayList<>());
            list.add(nextCountry.countryNum);
            map.put(nextCountry.total, list);

            if (nextCountry.countryNum == k) {
                answer = nextCountry.total;
                break;
            }
        }

        int all = 1;

        for (String key : map.keySet()) {
            if(key.equals(answer)) continue;
            all += map.get(key).size();
        }


        bw.write(all + "");
        bw.flush();
        bw.close();
    }

}


