import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int length = 1;
        while(length <= 10) {

            recursion(length++, 10);



            if (list.size() > n) {
                System.out.println(list.get(n));
                return;
            }
        }

        System.out.println(-1);


    }


    private static void recursion(int depth, int pre) {

        if (depth == 0) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (pre > i) {
                sb.append(i);
                recursion(depth - 1, i);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}