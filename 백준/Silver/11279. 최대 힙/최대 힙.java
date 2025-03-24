import java.io.*;
import java.util.*;

public class Main {

    static class MaxHeap {
        int[] heap;
        int size;

        public MaxHeap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        public void add(int val) {
            if (size >= heap.length) {
                resize();
            }
            heap[size] = val;
            siftUp(size);
            size++;
        }

        public int remove() {
            if (size == 0) return 0;

            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            siftDown(0);
            return max;
        }

        private void siftUp(int i) {
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap[parent] >= heap[i]) break;
                swap(i, parent);
                i = parent;
            }
        }

        private void siftDown(int i) {
            while (true) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int largest = i;

                if (left < size && heap[left] > heap[largest]) {
                    largest = left;
                }
                if (right < size && heap[right] > heap[largest]) {
                    largest = right;
                }
                if (largest == i) break;

                swap(i, largest);
                i = largest;
            }
        }

        private void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        private void resize() {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        MaxHeap maxHeap = new MaxHeap(n);

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                sb.append(maxHeap.remove()).append("\n");
            } else {
                maxHeap.add(x);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
