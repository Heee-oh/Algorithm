import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static ArrayList<Character>[] tree;
    static StringBuilder sb = new StringBuilder();
    static int nullCheck = '.' - 'A';
    static char[] alpha;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        // 인덱스는   -'A' 값은 알파벳
        tree = new ArrayList[n];


        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            int root = split[0].charAt(0) - 'A';
            tree[root].add(split[1].charAt(0));
            tree[root].add(split[2].charAt(0));
        }

        alpha = new char[26];
        for (int i = 0; i < 26; i++) {
            alpha[i] = (char) (i + 'A');
        }

        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);



        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


    // 전위
    private static void preorder(int idx) {
        if (nullCheck == idx) {
            return;
        }

        // 중앙 - 왼쪽 - 오른쪽
        sb.append(alpha[idx]);
        preorder(tree[idx].get(0) - 'A');
        preorder(tree[idx].get(1) - 'A');

    }

    // 중위
    private static void inorder(int idx) {
        if (nullCheck == idx) {
            return;
        }

        // 왼쪽 - 중앙 - 오른쪽
        inorder(tree[idx].get(0) - 'A');
        sb.append(alpha[idx]);
        inorder(tree[idx].get(1) - 'A');
    }

    // 후위
    private static void postorder(int idx) {
        if (nullCheck == idx) {
            return;
        }

        // 왼쪽 - 오른쪽 - 중앙
        postorder(tree[idx].get(0) - 'A');
        postorder(tree[idx].get(1) - 'A');
        sb.append(alpha[idx]);
    }


}
