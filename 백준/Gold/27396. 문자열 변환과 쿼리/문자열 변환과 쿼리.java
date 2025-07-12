import java.io.*;
import java.util.*;

public class Main {

    static char[] S;
    static int n;

    static char[] upperCaseChar;
    static char[] lowCaseChar;

    static Set<Integer>[] lowCase;
    static Set<Integer>[] upperCase;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        S = st.nextToken().toCharArray(); // max len 10만
        n = Integer.parseInt(st.nextToken()); // max 30만

        int len = 'Z' - 'A' + 1;
        lowCase = new Set[len];
        upperCase = new Set[len];

        upperCaseChar = new char[len];
        lowCaseChar = new char[len];

        // 대소문자색인 초기화
        for (int i = 0; i < len; i++) {
            upperCase[i] = new HashSet<>();
            lowCase[i] = new HashSet<>();

            upperCaseChar[i] = (char) ('A' + i);
            lowCaseChar[i] = (char) ('a' + i);
        }


        // 기존 문자열 S를 각 문자에 대한 인덱스 집합에 저장
        for (int i = 0; i < S.length; i++) {
            if ('A' <= S[i] && S[i] <= 'Z') {
                int idx = S[i] - 'A';
                upperCase[idx].add(i);
            } else {
                int idx = S[i] - 'a';
                lowCase[idx].add(i);
            }
        }


        //유형 1,2 처리
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            // 유형 2
            if (type == 2) {
                // 출력

                for (int j = 0; j < lowCase.length; j++) {
                    if (!lowCase[j].isEmpty()) {
                        for (int idx : lowCase[j]) {
                            S[idx] = lowCaseChar[j];
                        }
                    }

                    if (!upperCase[j].isEmpty()) {
                        for (int idx : upperCase[j]) {
                            S[idx] = upperCaseChar[j];
                        }
                    }

                }

                sb.append(S);
                sb.append("\n");

                // 1유형
            } else {
                char i1 = st.nextToken().charAt(0);
                char i2 = st.nextToken().charAt(0);

                if (i1 == i2) continue;

                if ('A' <= i1 && i1 <= 'Z') {
                    int idx = i1 - 'A';

                    if (upperCase[idx].isEmpty()) continue;

                    if ('a' <= i2 && i2 <= 'z') {
                        int idx2 = i2 - 'a';
                        lowCase[idx2].addAll(upperCase[idx]);

                    } else {
                        int idx2 = i2 - 'A';

                        upperCase[idx2].addAll(upperCase[idx]);
                    }
                    upperCase[idx] = new HashSet<>();

                } else {
                    int idx = i1 - 'a';

                    if (lowCase[idx].isEmpty()) continue;

                    if ('a' <= i2 && i2 <= 'z') {
                        int idx2 = i2 - 'a';
                        lowCase[idx2].addAll(lowCase[idx]);
                    } else {
                        int idx2 = i2 - 'A';
                        upperCase[idx2].addAll(lowCase[idx]);
                    }

                    lowCase[idx] = new HashSet<>();
                }



            }
        }

        System.out.print(sb.toString());
    }
}