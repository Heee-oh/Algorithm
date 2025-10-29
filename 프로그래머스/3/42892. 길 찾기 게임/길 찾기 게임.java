import java.util.*;


class Solution {
    
    class Node {
        int n, x, y;
        
        Node left;
        Node right;
        
        public Node (int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
            left = null;
            right = null;
        }
    }
    
    static List<Node> list = new ArrayList<>();
    static boolean[] visited;
    static int[][] answer;
    
    static int preOrderIdx = 0;
    static int postOrderIdx = 0;
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            list.add(new Node(i+1,x,y));
        }
        
        visited = new boolean[nodeinfo.length + 1];
        Collections.sort(list, (a, b) -> b.y == a.y ? a.x - b.x : b.y - a.y);
        
        Node head = list.get(0);
        visited[head.n] = true;
        buildBinaryTree(head, 1, 0, 100000);
        
        preOrder(head);
        postOrder(head);
        
        return answer;
    }
    
    private void preOrder(Node cur) {
        if (cur == null) {
            return;
        }
        answer[0][preOrderIdx++] = cur.n;
        preOrder(cur.left);
        preOrder(cur.right);
    }
    
    
    private void postOrder(Node cur) {
        if (cur == null) {
            return;
        }
        
        postOrder(cur.left);
        postOrder(cur.right);
        answer[1][postOrderIdx++] = cur.n;
    }
    private void buildBinaryTree(Node cur, int idx, int l, int r) {
        
        for (int i = idx; i < list.size(); i++) {
            Node tmp = list.get(i);
            if (visited[tmp.n] || cur.y == tmp.y) continue;
            if (tmp.x < l || tmp.x > r) continue;
            
            if (cur.left == null && tmp.x < cur.x) {
                cur.left = tmp;
                visited[tmp.n] = true;
                buildBinaryTree(tmp, i + 1, l, cur.x);
                
            } else if (cur.right == null && tmp.x > cur.x) {
                cur.right = tmp;
                visited[tmp.n] = true;
                buildBinaryTree(tmp, i + 1, cur.x, r);
            }
            
            
            if (cur.left != null && cur.right != null) {
                break;
            }
            
        }
        
        
        
    }
}