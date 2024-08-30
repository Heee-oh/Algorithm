import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

//        HashSet<String> set = new HashSet<>();
        TreeSet<String> set = new TreeSet<>((o1, o2) -> o1.length() == o2.length() ?
                o1.compareTo(o2) : o1.length() - o2.length());

        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }


        for (String s : set) {
            sb.append(s).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}