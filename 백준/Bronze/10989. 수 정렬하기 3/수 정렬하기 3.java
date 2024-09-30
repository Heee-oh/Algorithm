import java.io.*;
import java.util.*;

public class Main {

    // 이 문제의 경우 입력이 1천만이기에 O(NLogN)도 시간 초과한다.
    // 1백만이 NLogN = 1백만 x 1000 = 10억
    // 따라서 기수정렬로 O(N)으로 처리한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());


        int[] nums = new int[10001];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[num]++;
        }


        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i).append("\n");
            }
        }



        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }




}
