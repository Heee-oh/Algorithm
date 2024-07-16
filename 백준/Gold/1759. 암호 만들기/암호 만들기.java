import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static int C;
//    static String[] vowel = {"a", "e", "i", "o", "u"};
    static String vowel2 = "aeiou";
    static String[] keyword;
    static String[] password;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken()); // 암호 구성 자릿수
        C = Integer.parseInt(st.nextToken()); // 문자 개수
        st = new StringTokenizer(br.readLine());
        keyword = new String[C];
        password = new String[L];
        visited = new boolean[C];
        for (int i = 0; i < C; i++) {
            keyword[i] = st.nextToken();
        }

        Arrays.sort(keyword);

        dfs(0, -1);
        System.out.println(sb.toString());
    }




    private static void dfs(int depth, int pre) {
        if (depth == L) {
            if (validVowel()) return;

            // 이상없다면 정상 진행
            for (String ps : password) {
                sb.append(ps);
            }

            sb.append("\n");
            return;
        }


        for (int i = 0; i < C; i++) {
            if (!visited[i] && pre < i) {
                visited[i] = true;
                password[depth] = keyword[i];
                dfs(depth + 1, i);
                visited[i] = false;
            }
        }


    }

    private static boolean validVowel() {
        int vowCount = 0;
        for (String s : password) {
            // 검증
            vowCount += vowel2.contains(s) ? 1 : 0;

        }
        // 전체길이 - 모음의 개수 = 자음의 개수
        if(L - vowCount < 2 || vowCount == 0) {
            return true;
        }
        return false;
    }


}