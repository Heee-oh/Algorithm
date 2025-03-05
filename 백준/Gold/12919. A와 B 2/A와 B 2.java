import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean check = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        // 문자열 읽기
        String S = br.readLine();
        String T = br.readLine();
        sb.append(S);
        backtracking(T);

        bw.write((check ? 1 : 0) + "");
        bw.flush();
        bw.close();
    }

    //  X/2 = X
    private static void backtracking(String target) {

        if (!target.contains(sb.toString())) {
            if (!target.contains(sb.reverse().toString())) {
                sb.reverse();
                return;
            }
            sb.reverse();
        }

        if (sb.length() == target.length()) {
            if (sb.toString().equals(target)) {
                check = true;
            }
            return;
        }



        sb.append('A');
        backtracking(target);
        sb.deleteCharAt(sb.length() - 1);

        sb.append('B');
        sb.reverse();
        backtracking(target);
        sb.reverse();
        sb.deleteCharAt(sb.length() - 1);




    }



}


