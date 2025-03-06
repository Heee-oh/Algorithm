import java.io.*;
import java.util.*;

public class Main {

    // x행, y 열 (x,y)
    // 1,1 부터 n,n까지

    // 머리 = 심장 1칸 위
    // 왼 팔 = 심장 왼쪽, 오른판은 오른쪽
    // 허리 = 심장 아래
    // 다리 허리 양옆
    // 허리 팔 다리 길이 1, 너비는 무조건 1

    // 심장 위치 , (팔, 다리, 허리) 길이 구하기

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // 심장 위치 및 신체 배열로 저장
        String[] body = new String[n];
        int[] heartPos = {-1, -1};
        for (int i = 0; i < n; i++) {

            String line = br.readLine();
            if (heartPos[1] == -1 ) {
                heartPos[0] = i + 1; // 심장위치 + 1
                heartPos[1] = line.indexOf('*');
            }

            body[i] = line;
        }

        // 왼팔, 오른팔, 허리, 왼다리, 오른다리 순
        int[] answer = new int[5];

        // 양쪽 팔
        int start = body[heartPos[0]].indexOf('*');
        int end = body[heartPos[0]].lastIndexOf('*');

        answer[0] = heartPos[1] - start;
        answer[1] = end - heartPos[1];

        // 허리
        answer[2] = getLength(body, heartPos[0] + 1, heartPos[1], n);
        // 왼 다리
        answer[3] = getLength(body, heartPos[0] + answer[2] + 1, heartPos[1] - 1, n);
        // 오른 다리
        answer[4] = getLength(body, heartPos[0] + answer[2] + 1, heartPos[1] + 1, n);



        bw.write((heartPos[0] + 1) + " " + (heartPos[1] + 1) + "\n");
        bw.write(String.format("%d %d %d %d %d", answer[0], answer[1], answer[2], answer[3], answer[4]));
        bw.flush();
        bw.close();
    }

    private static int getLength(String[] body, int row, int col, int n) {

        int cnt = 0;
        for (int i = row; i < n; i++) {
            if (body[i].charAt(col) != '*') {
                break;
            }
            cnt++;
        }

        return cnt;
    }


}