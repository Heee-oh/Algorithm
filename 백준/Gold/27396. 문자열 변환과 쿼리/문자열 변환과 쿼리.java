import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        int n = Integer.parseInt(st.nextToken());

        Map<Character, Character> map = new HashMap<>();

        for (char c = 'A'; c <= 'z'; c++) {
            map.put(c, c);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int choice = Integer.parseInt(st.nextToken());

            if (choice == 1) {
                char p = st.nextToken().charAt(0);
                char q = st.nextToken().charAt(0);

                for (Map.Entry<Character, Character> entry : map.entrySet()) {
                    if (entry.getValue() == p) {
                        entry.setValue(q);
                    }
                }

            } else {

                StringBuilder sb = new StringBuilder();
                for (char c : s.toCharArray()) {
                    sb.append(map.get(c));
                }
                System.out.println(sb);
            }
        }
    }
}
