import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> trueSet = new HashSet<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        boolean[] partyCheck = new boolean[m];

        int theTrueOne = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람이 없다면 모든 파티에서 거짓말 가능
        if (theTrueOne == 0) {
            System.out.println(m);
            return;
        }

        // 진실을 아는 사람들 저장
        for (int i = 0; i < theTrueOne; i++) {
            trueSet.add(Integer.parseInt(st.nextToken()));
        }



        // 파티 인원 저장
        int[][] party = new int[m][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                int person = Integer.parseInt(st.nextToken());
                party[i][j] = person;

                // 진실된 사람이 있다면 그 파티는 진실만 말해야함
                if (trueSet.contains(person)) {
                    partyCheck[i] = true;
                }
            }

            // 진실을 모르는사람과 아는 사람이 같이 있는 파티이면 모르는 사람들앞에서도 진실을 얘기해야함
            if (partyCheck[i]) {
                for (int j = 0; j <= n; j++) {
                    if (party[i][j] == 0) {
                        break;
                    }

                    trueSet.add(party[i][j]);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (party[j][k] == 0) break;
                    if (trueSet.contains(party[j][k])) {
                        partyCheck[j] = true;
                    }
                }

                if (partyCheck[j]) {
                    for (int k = 0; k <= n; k++) {
                        if (party[j][k] == 0) break;
                        trueSet.add(party[j][k]);
                    }
                }

            }
        }
        int answer = 0;
        for (int i = 0; i < m; i++) {
            if (!partyCheck[i]) {
                boolean flag = false;
                for (int j = 0; j < n; j++) {
                    if (party[i][j] == 0) break;

                    if (trueSet.contains(party[i][j])) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    answer++;
                }
            }
        }


        System.out.println(answer);





    }

}
