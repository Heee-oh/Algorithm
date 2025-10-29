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
        
        // 노드 객체로 생성후 리스트에 담기
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            list.add(new Node(i+1,x,y));
        }
        
        visited = new boolean[nodeinfo.length + 1];
        Collections.sort(list, (a, b) -> b.y == a.y ? a.x - b.x : b.y - a.y); // y가 큰순으로, 같다면 x가 작은 순으로 
        
        Node head = list.get(0); // 이진 트리이므로 항상 루트가 있을 것이다. 
        visited[head.n] = true;
        
        buildBinaryTree(head, 1, 0, 100000); // 이진트리의 자식 노드를 설정할 수 있는 범위를 정해 올바른 이진트리생성
        
        // 전위, 후위 노드 탐색 
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
            if (visited[tmp.n] // 이미 사용한 노드
                || cur.y == tmp.y  // 같은 level의 노드 
                ) continue; // 자식노드 추가 가능한 범위를 벗어남
         
            if(tmp.x < l || tmp.x > r) break;
            
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