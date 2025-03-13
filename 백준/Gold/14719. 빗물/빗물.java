import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    //왼쪽 최댓값과 오른쪽 최댓값 중 최솟값?
    // 양 옆의 자신과 같거나 큰 곳 탐색
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] blocks = new int[w];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }


        int left = leftSearch(blocks, stack);

        // 남은 것은 다시 위 알고리즘과 동일하게 진행한다.
        int[] restBlocks = new int[stack.size() + 1];
        int idx = 0;

        while (!stack.isEmpty()) {
            restBlocks[idx++] = stack.pop();
        }

        restBlocks[idx] = blocks[left];
        leftSearch(restBlocks, stack);

        bw.write(answer+ "");
        bw.flush();
        bw.close();

    }

    private static int leftSearch(int[] blocks, Stack<Integer> stack) {
        int left = 0, right = 1;

        while (right < blocks.length) {
            if (blocks[left] <= blocks[right]) {

                int min = Math.min(blocks[left], blocks[right]);
                while (!stack.isEmpty()) {
                    answer += min - stack.pop();
                }

                left = right;
                right++;

            } else {
                stack.push(blocks[right++]);

            }
        }

        return left;
    }


}