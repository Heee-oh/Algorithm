import java.io.*;
import java.util.*;

public class Main {
    // 자주 나오는 거
    // 길이가 길수록
    // 알파벳 사전 순으로 앞에 있는 것

    // 길이가 M 이상인 단어만 외움

    static class Word {
        String name;
        int cnt;

        public Word(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() >= m) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        // 배열에 담기
        int idx = 0;
        Word[] words = new Word[map.size()];
        for (String s : map.keySet()) {
            words[idx++] = new Word(s, map.get(s));
        }


        Arrays.sort(words, (o1, o2) -> {
            // 자주 나오는 단어 순
            if (o2.cnt != o1.cnt) {
                return o2.cnt - o1.cnt;
            }

            // 단어 길이가 길수록 앞으로
            if (o2.name.length() != o1.name.length()) {
                return o2.name.length() - o1.name.length();
            }

            // 알파벳 사전 순
            return o1.name.compareTo(o2.name);
        });


        for (Word word : words) {
            sb.append(word.name).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
