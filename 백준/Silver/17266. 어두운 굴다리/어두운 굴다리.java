import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int lampIdx = Integer.parseInt(st.nextToken());
            list.add(lampIdx);
        }

        int front = 0, back = n;
        int min = 100001;

        while (front <= back) {
            
            int mid = (front + back) >>> 1;

            int loadStart = list.get(0) - mid; // 빛이 비춰지는 처음 시작 위치
            int loadEnd = 0; // 빛이 비춰지는 마지막 위치

            
            for (int lamp : list) {
                int left = lamp - mid; // 가로등의 왼쪽 비추는 부분

                // 현재 왼쪽 비친부분이 이전 비춰지는 마지막 위치안이라면 현재 비치는 마지막부분으로 갱신   
                if (left <= loadEnd) {
                    loadEnd = lamp + mid;
                }
            }

            // 비치는 부분이 길시작(0)보다 작거나 같고 끝부분보다 더 많이 비추면 모든 길을 비추므로 높이를 줄여서 최솟값을 찾음
            if (loadStart <= 0 && n <= loadEnd ) {
                min =  mid;
                back = mid - 1;
                // 다 못 밝혔다면 높이를 키움
            } else {
                front = mid + 1;
            }
        }

        bw.write(min + "");
        bw.flush();
        bw.close();
    }

}