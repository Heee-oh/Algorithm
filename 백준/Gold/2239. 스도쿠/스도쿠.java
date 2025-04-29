import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][][] unavailable;
    static List<Integer>[][] list;
    static List<int[]> zeroList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[9][9];
        list = new List[9][9];

        unavailable = new int[10][9][9];
        zeroList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 리스트 할당
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                list[i][j] = new ArrayList<>();
            }
        }


        // 각 자리 수의 행, 열, 3x3공간에 중복방지를 세팅
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] != 0) {
                    checkDuple(i, j, 1);

                }else {
                    zeroList.add(new int[]{i, j});
                }
            }
        }



        // 0이 저장된 빈 공간의 위치에 가능한 숫자들을 저장한다.
        for (int num = 1; num <= 9; num++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (unavailable[num][j][k] == 0) {
                        list[j][k].add(num);
                    }
                }
            }
        }




        // 백트래킹으로 가능한 방법을 모두 찾는다.
        backtracking(0);
        StringBuilder sb = new StringBuilder();
        for (int[] row : map) {
            for (int n : row) {
                sb.append(n);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
    private static void checkDuple(int r, int c, int op) {
        int startR = r / 3 * 3;
        int startC = c / 3 * 3;

        int num = map[r][c];
        int value = 1 * op;

        // 행 처리
        for (int i = 0; i < 9; i++) {
            unavailable[num][r][i] += value;
            unavailable[num][i][c] += value;
        }

        // 3x3 처리
        for (int i = startR; i < startR + 3; i++) {
            for (int j = startC; j < startC + 3; j++) {
                unavailable[num][i][j] += value;
            }
        }
    }


    static boolean flag = false;
    private static void backtracking(int idx) {
        if (zeroList.size() == idx) {
            flag = true;
            return;
        }

        int[] pos = zeroList.get(idx);
        int r = pos[0];
        int c = pos[1];

        // 가능한 숫자들
        for (int num : list[r][c]) {
            // 둘 수 있는지 확인
            if (unavailable[num][r][c] > 0) continue;
            map[r][c] = num;
            checkDuple(r,c, 1);
            backtracking(idx + 1);
            checkDuple(r,c, -1);
            if (flag) return;
        }


    }

}
