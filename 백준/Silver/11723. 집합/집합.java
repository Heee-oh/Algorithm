import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(br.readLine());
        boolean[] S = new boolean[21];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if (op.equals("add")) add(S, Integer.parseInt(st.nextToken()));
            else if (op.equals("remove")) remove(S, Integer.parseInt(st.nextToken()));
            else if (op.equals("check")) check(S, Integer.parseInt(st.nextToken()));
            else if (op.equals("toggle")) toggle(S, Integer.parseInt(st.nextToken()));
            else if (op.equals("all")) all(S);
            else empty(S);
        }
        

        


        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
    
    private static void add(boolean[] s, int x) {
        s[x] = true;
    }
    
    private static void remove(boolean[] s, int x) {
        s[x] = false;
    }
    
    private static void check(boolean[] s, int x) {
        sb.append(s[x] ? 1 : 0).append("\n");
    }

    private static void toggle(boolean[] s, int x) {
        s[x] = !s[x];
    }

    private static void all(boolean[] s) {
        Arrays.fill(s,true);
    }

    private static void empty(boolean[] s) {
        Arrays.fill(s, false);
    }
    
}