import java.util.*;

class Solution {
    class Node {
        int num;
        Node prev;
        Node post;
        
        public Node(int num) {
            this.num = num;
            prev = null;
            post = null;
        }
    }
    
    
    // /"U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
    // "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
    // "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
    // "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
    
    // 직접 연결 리스트를 구현, 삭제는 스택으로 처리 
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
    
        String answer = "";
        Node head = new Node(0);
        
        boolean[] lived = new boolean[n];
        Node[] arr = new Node[n];
        Arrays.fill(lived, true);
        
        Node tmp = head;
        arr[0] = head;
        
        for (int i = 1; i < n; i++) {
            Node next = new Node(i);
            arr[i] = next;
            
            tmp.post = next;
            next.prev = tmp;
            tmp = next;
        }
        
        // 삭제된 노드들은 최근걸 복구 가능하게 스택으로 처리 
        Stack<Node> removed = new Stack<>();
        
        // k 위치로 이동
        Node cur = head;
        for (int i = 0; i < k; i++) {
            cur = cur.post;
        }
        
        // 명령 받기
        for (int i = 0; i < cmd.length; i++) {
            
            if (cmd[i].length() == 1) {
                
                // 삭제후 밑으로 이동, 마지막이면 위로 이동
                if (cmd[i].equals("C")) {
                    lived[cur.num] = false;
                    removed.push(cur);
                    
                    // 삭제 처리, 양옆을 다시 연결
                    // 마지막이라면 윗행을 선택
                    if (cur.post == null) {
                        Node tmp1 = cur.prev;
                        if (tmp1 != null) {
                            tmp1.post = null;
                        }
                        cur = tmp1;

                    } else {
                        Node tmp1 = cur.prev;
                        if (tmp1 != null) {
                            tmp1.post = cur.post;
                        }

                        if (cur.post != null) {
                            cur.post.prev = tmp1;
                            cur = cur.post;
                        }
                    }
                    
                    // 최근 삭제 복구 
                } else {
                    Node rollback = removed.pop();
                    Node prev = rollback.prev;
                    Node post = rollback.post;
                    
                    if (prev != null && post != null 
                        && lived[prev.num] && lived[post.num]) {
                        prev.post = rollback;
                        post.prev = rollback;
                        
                    } else if (prev != null 
                               && lived[prev.num]) {
                        Node newPost = prev.post;
                        prev.post = rollback;
                        rollback.post = newPost;
                        
                        if (newPost != null) {
                            newPost.prev = rollback;
                        }
                        
                    } else if (post != null 
                               && lived[post.num]) {
                        Node newPrev = post.prev;
                        if (newPrev != null) {
                            newPrev.post = rollback;
                        }
                        
                        rollback.prev = newPrev;
                        post.prev = rollback;
                        
                    } else {
                        
                    }
                    
                    lived[rollback.num] = true;
                }
                
            } else {
                String[] split = cmd[i].split(" ");
                String command = split[0];
                int dist = Integer.parseInt(split[1]);
                
                if (command.equals("D")) {
                    for (int j = 0; j < dist; j++) {
                        cur = cur.post;
                    }
                    
                } else {
                    for (int j = 0; j < dist; j++) {
                        cur = cur.prev;
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            sb.append(lived[i] ? "O" : "X");
        }
        return sb.toString();
    }

}