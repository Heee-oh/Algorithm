import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n > 0) {
            st = new StringTokenizer(br.readLine());
        }

        int box = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (box + book > m) {
                box = book;
                cnt++;

            } else if (box + book == m) {
                box = 0;
                cnt++;

            } else {
                box += book;
            }
        }


        System.out.println(cnt + (box == 0 ? 0 : 1));
    }



}
