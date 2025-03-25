import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            TreeSet<Integer> ts = new TreeSet<>();
            Map<Integer, Integer> map = new HashMap<>();
            int K = Integer.parseInt(br.readLine());

            for (int i = 0; i < K; i++) {
                String[] op = br.readLine().split(" ");

                if (op[0].equals("I")) {
                    int element = Integer.parseInt(op[1]);

                    map.put(element, map.getOrDefault(element, 0) + 1);
                    ts.add(element);

                    // D 이면서 큐가 비어있지 않다면
                } else if (!ts.isEmpty()) {
                    // 최소값
                    if (op[1].equals("-1")) {
                        int min = ts.first();
                        map.put(min, map.get(min) - 1);

                        if (map.get(min) == 0) {
                            ts.pollFirst();
                        }

                        // 최댓값
                    }else {
                        int max = ts.last();
                        map.put(max, map.get(max) - 1);

                        if (map.get(max) == 0) {
                            ts.pollLast();
                        }
                    }
                }

            }

            if (ts.isEmpty()) {
                sb.append("EMPTY");
            }else if (ts.size() == 1) {
                sb.append(ts.first()).append(" ").append(ts.first());
            } else {
                sb.append(ts.last()).append(" ").append(ts.first());
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }







}
