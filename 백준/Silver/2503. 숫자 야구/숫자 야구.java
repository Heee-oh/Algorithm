import java.io.*;
import java.util.*;

public class Main {

    static class Game {
        int strike, ball;
        char[] num;

        public Game(int strike, int ball, char[] num) {
            this.strike = strike;
            this.ball = ball;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Game> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] numbers = st.nextToken().toCharArray();

            // 스트라이크, 볼
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.add(new Game(s, b, numbers));
        }


        int answer = 0;

        for (int i = 123; i < 1000; i++) {
            char[] checkNum = String.valueOf(i).toCharArray();


            if (checkNum[0] == '0' || checkNum[1] == '0' || checkNum[2] =='0'
                    || checkNum[0] == checkNum[1] || checkNum[1] == checkNum[2] || checkNum[2] == checkNum[0]) continue;


            boolean flag = true;

            for (int j = 0; j < n; j++) {
                Game game = list.get(j);
                int strike = 0, ball = 0;

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (game.num[k] == checkNum[l] && k == l) {
                            strike++;

                        } else if (game.num[k] == checkNum[l] && k != l) {
                            ball++;
                        }
                    }
                }

                if (strike != game.strike || ball != game.ball) {
                    flag = false;
                    break;
                }

            }

            if (flag) answer++;
        }

        bw.write(answer +"");
        bw.flush();
        bw.close();
    }
}