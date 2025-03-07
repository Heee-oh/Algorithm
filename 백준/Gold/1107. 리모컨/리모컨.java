import java.io.*;
import java.util.*;

class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int min = 500001;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String target = br.readLine();
        int n = Integer.parseInt(target);
        int m = Integer.parseInt(br.readLine());
        boolean[] brokenBtn = new boolean[10];

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                int idx = Integer.parseInt(st.nextToken());
                brokenBtn[idx] = true;
            }

        }

        for (int i = 0; i < 10; i++) {
            if (!brokenBtn[i]) {
                list.add(i);
            }
        }

        for (int i : list) {
            backTracking(n, 1, target.length(), i);
        }

        bw.write(Math.min(Math.abs(100 - n), min) + "");
        bw.flush();
        bw.close();
    }


    private static void backTracking(int target, int depth, int size, int num) {

        // size +2 는 범위를 벗어나므로 종료
        if (depth == size + 2) return;

        //  size보다 1작은 길이의 숫자 혹은 길이가 1큰 숫자도 고려해야함
        if (size - 1 <= depth && depth <= size + 1) {
            int abs = Math.abs(num - target);
            int len = depth;
            min = Math.min(abs + len, min);
            /**
             * 기존 코드 32퍼 실패 이유
             * 길이와 min 값의 합으로 비교하지 않았기 떄문
             *
             */
        }

        for (int n : list) {
            backTracking(target, depth + 1, size, num * 10 + n);
        }
    }
}


