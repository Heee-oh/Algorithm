import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static FireBall[] fireBalls;
    static class FireBall {
        int r,c,m,s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireBalls = new FireBall[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls[i] = new FireBall(r, c, m, s, d);

        }

        // 명령 K번
        for (int i = 0; i < K; i++) {
            List<Integer>[][] map = new List[N][N];

            // 이동
            for (int j = 0; j < fireBalls.length; j++) {
                FireBall cur = fireBalls[j];

                int nextR = (cur.r + N + (dr[cur.d] * cur.s) % N) % N;
                int nextC = (cur.c + N + (dc[cur.d] * cur.s) % N) % N;

                if (map[nextR][nextC] == null) {
                    map[nextR][nextC] = new ArrayList<>();
                }

                // 해당 위치에 파이어볼 이동
                map[nextR][nextC].add(j);
                cur.r = nextR;
                cur.c = nextC;
            }

            // 이동후 파이어볼 처리
            List<FireBall> newFireball = new ArrayList<>();
            for (int j = 0; j < fireBalls.length; j++) {
                FireBall cur = fireBalls[j];

                if (cur == null
                        || map[cur.r][cur.c] == null) continue;

                List<Integer> list = map[cur.r][cur.c];

                if (list.size() >= 2) {
                    int sumM = 0, sumS = 0, check = 0;

                    for (int k = 0; k < list.size(); k++) {
                        FireBall ball = fireBalls[list.get(k)];
                        sumM += ball.m;
                        sumS += ball.s;
                        check += ball.d % 2;
                    }

                    int newM = sumM / 5;
                    int newS = sumS / list.size();

                    // 0이라면 모두 소멸
                    if (newM == 0) {
                        for (int k = 0; k < list.size(); k++) {
                            fireBalls[list.get(k)] = null;
                        }
                        continue;
                    }


                    int dist = 1;
                    // 모두 짝수 혹은 홀수
                    if (check == 0 || check == list.size()) {
                        dist = 0;
                    }

                    // 4개 새로운 애들 추가
                    for (int k = 0; k < 4; k++) {
                        newFireball.add(new FireBall(cur.r, cur.c, newM, newS, dist + 2 * k));
                    }

                    fireBalls[j] = null;
                    map[cur.r][cur.c] = null;

                } else if (list.size() == 1){
                    newFireball.add(cur);
                }
            }

            int size = newFireball.size();
            fireBalls = new FireBall[size];

            for (int j = 0; j < size; j++) {
                fireBalls[j] = newFireball.get(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < fireBalls.length; i++) {
            if (fireBalls[i] != null) {
                answer += fireBalls[i].m;
            }

        }

        System.out.println(answer);
    }


}

