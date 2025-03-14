import java.io.*;
import java.util.*;

public class Main {

    static class Word {
        String name;
        int idx;

        public Word(String name, int idx) {
            this.name = name;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];
        List<Word> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            list.add(new Word(words[i], i));
        }

        // 사전순 정렬
        Collections.sort(list, (o1, o2) -> o1.name.compareTo(o2.name));


        // 접두사 길이에 따른 단어들의 인덱스 저장
        TreeSet<Integer> ts = new TreeSet<>();
        int max = 0;

        // 접두사 탐색
        for (int i = 0; i < n - 1; i++) {
            String word1 = list.get(i).name;
            String word2 = list.get(i + 1).name;
            int len = Math.min(word1.length(), word2.length()); // 짧은 단어 기준으로 순회
            int cnt = 0;

            for (int j = 0; j < len; j++) {
                if (word1.charAt(j) == word2.charAt(j)) {
                    cnt++;
                } else {
                    break;
                }
            }

            // 접두사 길이가 max보다 크다면 갱신
            if (cnt > max) {
                ts.clear(); // 짧은건 필요없어졌으므로 초기화

                ts.add(list.get(i).idx);
                ts.add(list.get(i + 1).idx);
                max = cnt;

            } else if (cnt == max) { // 길이가 같을때 앞쪽 있는 단어인지를 위해 우선 저장
                ts.add(list.get(i).idx);
                ts.add(list.get(i + 1).idx);
            }
        }


        if (!ts.isEmpty()) {
            int S = ts.pollFirst(); // 맨 처음것이 max길이이면서 가장 앞에있는 S임
            String prefix = words[S].substring(0, max); // 접두사를 추출

            while (!ts.isEmpty()) { // max길이인 단어들을 순서대로뽑아내서 접두사가 같은 것을 탐색
                int idx = ts.pollFirst();
                if (words[idx].startsWith(prefix)) {
                    sb.append(words[S]).append("\n").append(words[idx]);
                    break;
                }
            }
            // 비어있다면 맨 앞 2개를 출력
        } else {
            sb.append(words[0]).append("\n").append(words[1]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }



}
