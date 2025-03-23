import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        String[] pokeDex = new String[n + 1];

        for (int i = 1; i <= n; i++) {
            pokeDex[i] = br.readLine();
            map.put(pokeDex[i], i);
        }

        for (int i = 0; i < m; i++) {
            String nextQuestion = br.readLine();
            if (Character.isDigit(nextQuestion.charAt(0))) {
                sb.append(pokeDex[Integer.parseInt(nextQuestion)]).append("\n");
            } else {
                sb.append(map.get(nextQuestion)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
