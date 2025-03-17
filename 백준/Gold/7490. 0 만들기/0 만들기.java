import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        List<String>[] answer = new List[10];
        List<Integer> testcase = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            answer[i] = new ArrayList<>();
        }

        int[] arr = new int[n];
        for (int i = 0; i < T; i++) {
            PriorityQueue<String> pq = new PriorityQueue<>();
            n = Integer.parseInt(br.readLine());
            testcase.add(n);
            if (!answer[n].isEmpty()) continue;

            dfs(1);
            for (String s : list) {
                String[] digit = s.replaceAll(" ", "")
                        .replaceAll("[+-]", " ")
                        .split(" ");
                String[] plusMinus = s.replaceAll(" ", "")
                        .replaceAll("[1-9]", "")
                        .split("");

//                Arrays.stream(digit).forEach(x -> System.out.print(x + " "));
//                System.out.println();
//                Arrays.stream(plusMinus).forEach(x -> System.out.print(x + " "));
//                System.out.println();

                int sum = Integer.parseInt(digit[0]);
                if (plusMinus.length <= 1) continue;

                for (int j = 0; j < plusMinus.length; j++) {
                    if (plusMinus[j].equals("+")) {
                        sum += Integer.parseInt(digit[j + 1]);
                    } else {
                        sum -= Integer.parseInt(digit[j + 1]);
                    }
                }

                if (sum == 0) {
                    answer[n].add(s);
                }
            }
            list.clear();
        }

        StringBuilder answerSb = new StringBuilder();
        for (int i = 0; i < testcase.size(); i++) {
            answer[testcase.get(i)].forEach(x -> answerSb.append(x).append("\n"));
            answerSb.append("\n");
        }

        bw.write(answerSb.toString());
        bw.flush();
        bw.close();
    }

    static List<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static String[] op = {" ", "+", "-"};
    private static void dfs(int nextN) {

        if (nextN == n) {
            sb.append(nextN);
            list.add(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }


        for (int j = 0; j < 3; j++) {
            sb.append(nextN).append(op[j]);
            dfs(nextN + 1);
            sb.delete(sb.length() - 2, sb.length());
        }



    }
}
