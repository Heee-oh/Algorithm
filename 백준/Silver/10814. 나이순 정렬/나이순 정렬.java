import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static class Info {
        int age;
        String name;

        public Info(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            map.putIfAbsent(age, new ArrayList<>());
            map.get(age).add(name);
        }


        ArrayList<Map.Entry<Integer, ArrayList<String>>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o1.getKey() - o2.getKey());

        for (Map.Entry<Integer, ArrayList<String>> entry : list) {

            for (String name : entry.getValue()) {
                sb.append(entry.getKey()).append(" ").append(name).append("\n");


            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}