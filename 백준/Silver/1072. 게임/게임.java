import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken()); // 게임횟수
        int y = Integer.parseInt(st.nextToken()); // 이긴 게임 횟수

        // x판을 해서 y번 이겼다.  y/x가 이긴 확률이라 볼 수 있음
        // z의 정수부분이 변해야함. z = 승률
        int z = calculateWinRate(x, y);

        // 최소 몇판해야하는지 이분탐색

        int front = 1, back = x;
        int min = back;
        while(front <= back) {
            int mid = (front + back) >>> 1;
            if (z >= calculateWinRate(x + mid, y + mid)) {
                front = mid + 1;
            } else {
                min = Math.min(min, mid);
                back = mid - 1;
            }
        }

        System.out.println(min == back ? -1 : min);
    }

    private static int calculateWinRate(int total, int winCnt) {
        return (int) ((double) winCnt * 100 / total );
    }


}
