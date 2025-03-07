import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] switches = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            switches[i] = st.nextToken().equals("1");
        }

        int numStudents = Integer.parseInt(br.readLine());
        for (int i = 0; i < numStudents; i++) {
            String[] student = br.readLine().split(" ");

            if (student[0].equals("1")) {
                switchOnlyMale(switches, Integer.parseInt(student[1]));
            } else {
                switchOnlyFemale(switches, Integer.parseInt(student[1]));
            }
        }


        for (int i = 1; i <= n; i++) {
            sb.append(switches[i] ? 1 : 0).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());


    }


    // 남학생은 받은 숫자의 배수 스위치 전환
    private static void switchOnlyMale(boolean[] switches, int num) {
        for (int i = num; i < switches.length; i+= num) {
            switches[i] = !switches[i];
        }
    }

    // 여학생은 받은 숫자 위치 양옆 대칭 구간 스위치 전환
    private static void switchOnlyFemale(boolean[] switches,int num) {

        // 구간 구하기
        int front = num, back = num;
        while (front > 0 && back < switches.length) {
            if (switches[front] == switches[back]) {
                front--;
                back++;
            } else {
                break;
            }
        }


        // 구간 전부 뒤집기
        for (int i = front + 1; i < back; i++) {
            switches[i] = !switches[i];
        }

    }
}
