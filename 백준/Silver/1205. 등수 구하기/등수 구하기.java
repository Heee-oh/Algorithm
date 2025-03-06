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
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int myNewScore = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        // 몇등하는지, 랭킹리스트에 못올랐는지 확인
        int idx = -1;
        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int score = Integer.parseInt(st.nextToken());
            if (idx == - 1 && score < myNewScore) { // 새 점수를 자리에 맞게 넣음
                list.add(myNewScore);
                idx = i;
            }
            list.add(score);
        }

        // 꼴등일대 넣어주기
        if (idx == -1) {
            list.add(myNewScore);
            idx = list.size() - 1;
        }

        // 랭킹 개수 p 안에 못들어갔다면 -1
        if (idx + 1 > p) {
            System.out.println(-1);
            return;
        }

        // 맨 처음은 랭킹 1위
        int[] rank = new int[n + 1];
        rank[0] = 1;
        for (int i = 1; i <= n; i++) {
            // 앞 점수와 같다면 같은 랭킹
            if (list.get(i - 1).equals(list.get(i))) {
                rank[i] = rank[i - 1];
            } else {
                rank[i] = i + 1;
            }
        }

        System.out.println(rank[idx]);

    }
}