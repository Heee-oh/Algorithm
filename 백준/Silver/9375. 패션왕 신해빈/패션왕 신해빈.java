import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-->0) {
            Map<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());




            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();

                int cnt = map.getOrDefault(type, 0) + 1;
                map.put(type, cnt);
            }
            
            int[] cloth = map.values().stream().mapToInt(Integer::intValue).toArray();

            long tmp = 1;
            for (int i = 0; i < map.size(); i++) {
                tmp *= (cloth[i] + 1);
            }

            sb.append(tmp - 1).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }





}
