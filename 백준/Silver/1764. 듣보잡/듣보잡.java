import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Boolean> map = new HashMap<>();

        for (int i = 0; i < n + m; i++) {
            String name = br.readLine();
            Boolean check = map.getOrDefault(name, true);
            map.put(name, !check);
        }

        for (String name : map.keySet()) {
            if (map.get(name)) {
                list.add(name);
            }
        }

        list.sort(String::compareTo);

        bw.write(list.size() + "\n");
        for (String name : list) {
            bw.write(name + "\n");
        }
        
        bw.flush();
        bw.close();
    }

    
}