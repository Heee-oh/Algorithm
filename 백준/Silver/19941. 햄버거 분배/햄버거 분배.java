import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] eatCheck = new boolean[n];

        char[] tables = br.readLine().toCharArray();
        ArrayList<Integer> people = new ArrayList<>();
        // 사람의 인덱스를 저장
        for (int i = 0; i < n; i++) {
            if (tables[i] == 'P') {
                people.add(i);
            }
        }

        int cnt = 0;

        for (int idx : people) {

            for (int i = idx - k; i <= idx + k; i++) {
                if (i < 0 || i >= n) continue;

                // 햄버거이고 아직 안먹었다면 먹음 처리
                if (tables[i] == 'H' && !eatCheck[i]) {
                    eatCheck[i] = true;
                    cnt++;
                    break;
                }
            }
        }
        
        bw.write(cnt+ "");
        bw.flush();
        bw.close();
    }
}
