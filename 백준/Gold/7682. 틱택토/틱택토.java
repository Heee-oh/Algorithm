import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // x-2>= o
        // o - 1 >= x
        // x가 빙고했는데 o도 빙고했고 개수가 같다면
        while (true) {

            String game = br.readLine();

            if (game.equals("end"))
                break;

            char[][] map = new char[3][3];
            int countX = 0;
            int countO = 0;

            for (int i = 0; i < 9; i++) {
                map[i / 3][i % 3] = game.charAt(i);
                if (game.charAt(i) == 'X') {
                    countX++;
                } else if (game.charAt(i) == 'O') {
                    countO++;
                }

            }

            // 1. 개수가 맞지 않다면
            if (countX - 2 >= countO || countO - 1 >= countX) {
                sb.append("invalid\n");
                continue;
            }

            // 2. 빙고 체크
            boolean xBingoCheck = false;
            boolean oBingoCheck = false;

            for (int i = 0; i < 3; i++) {
                // 가로
                if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {

                    if (map[i][0] == 'X') {
                        xBingoCheck = true;
                    } else if (map[i][0] == 'O') {
                        oBingoCheck = true;
                    }

                }
                // 세로
                if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                    if (map[0][i] == 'X') {
                        xBingoCheck = true;
                    } else if (map[0][i] == 'O') {
                        oBingoCheck = true;
                    }
                }
            }

            // 대각선
            if (map[0][0] == map[1][1] && map[1][1] == map[2][2]
                    || map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                if (map[1][1] == 'X') {
                    xBingoCheck = true;
                } else if (map[1][1] == 'O') {
                    oBingoCheck = true;
                }
            }

            // x또는 o 둘중 하나가 빙고해야하며 x가 빙고 했고, x -1 == O 이거나 o가 빙고했고 x == O면
            if (xBingoCheck != oBingoCheck
                    && (xBingoCheck && countO == countX - 1
                    || oBingoCheck && countO == countX)) {
                sb.append("valid\n");
                continue;

            }

            // 게임이 끝날때 둘다 빙고일 경우를 따로 처리해줘야함
            // testcase
            // XXO
            // OXO
            // XXO

            //XXO
            //OOX
            //XOX

            // true true만 피하면됨
            // 위에서 x O 둘다 빙고가 아니면서 로 처리했기에
            // 여기서도 xo 둘다 빙고가 아닐때를 생각해야한다.
            // 위에서 빙고 처리를 했기에 여기서는 둘다 빙고인 경우만 처리하면 됨
            // 3. 게임이 안전하게 끝났다면
            if (!xBingoCheck && !oBingoCheck
                    && countX + countO == 9) {
                sb.append("valid\n");
                continue;
            }

            // 불가능한 상황
            sb.append("invalid\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }



}
