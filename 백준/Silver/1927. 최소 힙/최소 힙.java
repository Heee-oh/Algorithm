import java.io.*;
import java.util.*;

public class Main {


    static class Heap {

        int[] arr;
        int size;

        public Heap(int size) {
            this.arr = new int[size];
        }


        public void add(int e) {
            if (!isFull()) {
                arr[size++] = e;
                heapify();
            }
        }
        public int remove() {
            if (!isEmpty()) {
                int min = arr[0];
                arr[0] = arr[size - 1];
                size--;

                int cur = 0;
                while (cur < size) {

                    if (cur * 2 < size && getLeftChild(cur) < getRightChild(cur)) {
                        if (arr[cur] > getLeftChild(cur) ) {
                            int tmp = getLeftChild(cur);
                            arr[cur * 2] = arr[cur];
                            arr[cur] = tmp;
                        }
                        cur *= 2;

                    } else if (cur * 2 + 1 < size && getLeftChild(cur) > getRightChild(cur)){
                        if (arr[cur] > getRightChild(cur) ) {
                            int tmp = getRightChild(cur);
                            arr[cur * 2 + 1] = arr[cur];
                            arr[cur] = tmp;
                        }
                        cur = cur * 2 + 1;
                    }else {
                        break;
                    }
                }

                return min;

                // 비어있으면 0 출력
            } else {
                return 0;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == arr.length;
        }

        public void heapify() {
            if (size - 1 < 1) {
                return;
            }

            int cur = size - 1;
            while (cur >= 1) {
                if (getParent(cur) <= arr[cur]) {
                    break;
                }

                int tmp = getParent(cur);
                arr[cur / 2] = arr[cur];
                arr[cur] = tmp;

                cur /= 2;
            }
        }

        private int getParent(int idx) {
            return arr[idx / 2];
        }

        private int getLeftChild(int idx) {
            return arr[idx * 2];
        }

        private int getRightChild(int idx) {
            return arr[idx * 2 + 1];
        }
        
        public void print() {
            for (int i = 0; i < size; i++) {

                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Heap minHeap = new Heap(n);

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                int remove = minHeap.remove();
                sb.append(remove).append("\n");
            } else {
                minHeap.add(x);
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


}
