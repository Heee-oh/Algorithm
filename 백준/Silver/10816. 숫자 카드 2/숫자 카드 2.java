import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }


        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());
            int idx = map.getOrDefault(key, 0);
            sb.append(idx).append(" ");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }




}


// 해쉬만을 사용한 풀이법
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }


        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());
            int idx = map.getOrDefault(key, 0);
            sb.append(idx).append(" ");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }




}
