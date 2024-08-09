import java.io.*;
import java.util.*;

public class Main {

    static class SegmentTree {
        long tree[];
        int treeSize;

        public SegmentTree(int arrSize) {
            // 트리는 1부터 시작
            // h = log2(N)
            // 자바에서 log는 자연로그, 따라서 2로 나누는 효과를 위해 log(2)로 나눔
            int h = (int) Math.ceil(Math.log(arrSize)/ Math.log(2)) + 1;
            this.treeSize = (int) Math.pow(2,h);
            tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end) {
            // 배열의 시작과 끝이 같다면 leaf 노드이므로 원소 배열 값 그대로 담기
            if (start == end) {
                return tree[node] = arr[start];
            }

            // leaf 노드가 아니면 자식노드 합 담기 (왼쪽 자식, 오른쪽 자식)
            return tree[node] =
                    init(arr, node * 2, start, (start + end) / 2)
                            + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        // arr 도 데이터를 변경 해놔야함
        // node : 현재 노드 idx, start : 배열의 시작, end : 배열의 끝
        // idx : 변경된 데이터의 idx, diff : 원래 데이터 값과 변경 데이터 값의 차이
        public void update(int node, int start, int end, int idx, long diff) {
            // 변경할 index 값이 범위 바깥이면 확인 불필요
            if (idx < start || end < idx) return;

            // 차를 저장
            tree[node] += diff;

            // 리프 노드가 아니면 아래 자식들도 확인
            if (start != end) {
                update(node * 2, start, (start + end) / 2, idx, diff);
                update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
            }
        }

        // node : 현재 노드, start : 배열의 시작, end : 배열의 끝
        // left : 원하는 누적합의 시작, right : 원하는 누적합의 끝
        public long sum(int node, int start, int end, int left, int right) {
            // 범위를 벗어나게 되는 경우 더할 필요 X
            // 즉 right < [1, 2, 3, 4] < left 이런 경우
            // 범위가 start나 end와 같을 경우는 범위를 벗아나는 것이 아니므로 넘어감
            // 왜 한부분만 체크하고 ||로 처리하냐면
            // left > end && right >= start 인 경우는 없다. 반대도 마찬가지

            if (left > end || right < start) return 0;

            // 범위 내 완전히 포함 시 바로 리턴
            // left <= [ 1, 2, 3, 4] <= right 이렇게 범위안에 완전 포함시 바로 리턴하겠다는 의미
            if (left <= start && end <= right) return tree[node];

            // 자식 탐색
            return sum(node * 2, start, (start + end) / 2, left, right) +
                    sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간 합 구하는 횟수

        long[] arr = new long[n + 1];
        SegmentTree tree = new SegmentTree(arr.length - 1);

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree.init(arr, 1, 1, arr.length - 1);


        // 구간 합을 구하는 횟수가 출력 횟수이다.

        for (int i = 0; i < k; i++) {
            boolean flag = true;

            while (flag) {
                st = new StringTokenizer(br.readLine());
                int select = Integer.parseInt(st.nextToken());

                if (select == 1) {
                    int idx = Integer.parseInt(st.nextToken());
                    long changeValue = Long.parseLong(st.nextToken());
                    tree.update(1, 1, arr.length - 1, idx, changeValue - arr[idx]);
                    arr[idx] = changeValue;

                } else {
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    bw.write(tree.sum(1, 1, arr.length - 1, left, right) + "\n");
                    flag = false;
                }
            }
        }

        bw.flush();
        bw.close();
    }


}