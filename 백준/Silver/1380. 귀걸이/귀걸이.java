import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 0;
        int scenarioNum = 0;
        do {
            scenarioNum++; // 시나리오 번호

            n = Integer.parseInt(br.readLine());
            String[] students = new String[n];
            boolean[] check = new boolean[n + 1];

            // 이름 배열에 담기
            for (int i = 0; i < n; i++) {
                students[i] = br.readLine();
            }

            for (int i = 0; i < (n * 2) - 1; i++) {
                String[] split = br.readLine().split(" ");
                int idx = Integer.parseInt(split[0]);

                if (check[idx]) {
                    check[idx] = false;
                } else {
                    check[idx] = true; // true 인 학생이 잃어버린 학생
                }
            }

            for (int i = 1; i < check.length; i++) {
                if (check[i]) {
                    bw.write(scenarioNum + " " + students[i-1] + "\n");
                    break;
                }
            }


        } while (n != 0);

        bw.flush();
        bw.close();
    }

}