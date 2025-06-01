import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // test case

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] students = new int[n + 1];
            boolean[] visited = new boolean[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            int assignedStudentCnt = 0;

            Stack<Integer> stack = new Stack<>();
            Set<Integer> set = new HashSet<>();

            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;

                int s = i;
                int wantedStudent = 0;
                while (s != wantedStudent) {
                    if (visited[s]) break; // 팀구성 실패하고 싸이클을 돌 경우 팀구성 취소

                    stack.push(s); // 임시 팀에 합류
                    set.add(s);
                    visited[s] = true; // 방문 처리
                    wantedStudent = students[s]; // s학생이 원하는 학생 대입


                    // 팀구성 조건이 만족하다면 (싸이클)
                    if (!set.contains(wantedStudent)) {
                        // 아직 싸이클 존재 X
                        s = wantedStudent;
                        wantedStudent = 0;
                        continue;
                    }

                    //탐색하면서 현재 학생이 원하는 학생이 임시 팀에 있다면 그 학생까지를 싸이클로 묶어 팀으로 선출
                    while (!stack.isEmpty()) {
                        assignedStudentCnt++; // 팀이 있는 학생 카운트
                        if (stack.pop() == wantedStudent) {
                            break;
                        }
                    }

                    break;
                }

                // 임시 팀 구성 초기화
                stack.clear();
                set.clear();
            }

            bw.write((n - assignedStudentCnt) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
