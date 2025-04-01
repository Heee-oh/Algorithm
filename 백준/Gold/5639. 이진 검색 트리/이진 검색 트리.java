import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int value;
        Node leftNode;
        Node rightNode;

        public Node(int value, Node leftNode, Node rightNode) {
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static Node root = null; // 루트 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String inputNode = br.readLine();
            if (inputNode == null || inputNode.isBlank()) { // 입력이 끝났다면 멈춤
                break;
            }

            int node = Integer.parseInt(inputNode);
            root = shiftDown(node, root); // 아래로 내려가면서 자식 생성
        }

        postFix(root);

        System.out.println(sb.toString());

    }




    private static Node shiftDown(int nodeValue, Node nextNode) {

        // 노드가 비어있다면 생성해서 반환
        if (nextNode == null) {
            return new Node(nodeValue, null, null);
        }

        // 현재 노드보다 작다면 left로 이동
        if (nextNode.value > nodeValue) {
            nextNode.leftNode = shiftDown(nodeValue, nextNode.leftNode);

            // 현재 노드보다 크다면 right로 이동
        } else if (nextNode.value < nodeValue) {
            nextNode.rightNode = shiftDown(nodeValue, nextNode.rightNode);
        }
        return nextNode;
    }

    private static void postFix(Node node) {

        if (node == null) {
            return;
        }

        postFix(node.leftNode);
        postFix(node.rightNode);
        sb.append(node.value).append("\n");
    }

}