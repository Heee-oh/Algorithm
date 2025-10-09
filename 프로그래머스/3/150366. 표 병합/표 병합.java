import java.util.*;

class Solution {
    static int[] parent = new int[51 * 51];
    static String[] value = new String[51 * 51];
    
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        
        for (int i = 0; i < 51 * 51; i++) {
            parent[i] = i;
            value[i] = "EMPTY";
        }
        
        for (String command : commands) {
            String[] cmd = command.split(" ");
            
            switch (cmd[0]) {
                case "UPDATE":
                    if (cmd.length == 4) {
                        int r = Integer.parseInt(cmd[1]);
                        int c = Integer.parseInt(cmd[2]);
                        String val = cmd[3];
                        int idx = find(getIndex(r, c));
                        value[idx] = val;
                    } else {
                        String val1 = cmd[1];
                        String val2 = cmd[2];
                        for (int i = 0; i < 51 * 51; i++) {
                            if (value[find(i)].equals(val1)) {
                                value[find(i)] = val2;
                            }
                        }
                    }
                    break;
                    
                case "MERGE":
                    int r1 = Integer.parseInt(cmd[1]);
                    int c1 = Integer.parseInt(cmd[2]);
                    int r2 = Integer.parseInt(cmd[3]);
                    int c2 = Integer.parseInt(cmd[4]);
                    merge(r1, c1, r2, c2);
                    break;
                    
                case "UNMERGE":
                    int r = Integer.parseInt(cmd[1]);
                    int c = Integer.parseInt(cmd[2]);
                    unmerge(r, c);
                    break;
                    
                case "PRINT":
                    int pr = Integer.parseInt(cmd[1]);
                    int pc = Integer.parseInt(cmd[2]);
                    String v = value[find(getIndex(pr, pc))];
                    answer.add(v.equals("EMPTY") ? "EMPTY" : v);
                    break;
            }
        }
        
        return answer.toArray(new String[0]);
    }
    
    private int getIndex(int r, int c) {
        return (r - 1) * 50 + c;
    }
    
    private int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parent[b] = a;
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        int idx1 = getIndex(r1, c1);
        int idx2 = getIndex(r2, c2);
        int root1 = find(idx1);
        int root2 = find(idx2);
        if (root1 == root2) return;
        
        // 값 선택 규칙
        String val = "EMPTY";
        if (!value[root1].equals("EMPTY")) val = value[root1];
        else if (!value[root2].equals("EMPTY")) val = value[root2];
        
        union(root1, root2);
        value[find(root1)] = val;
    }
    
    private void unmerge(int r, int c) {
        int idx = getIndex(r, c);
        int root = find(idx);
        String val = value[root];
        
        // 같은 root인 모든 셀 분리
        List<Integer> group = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            if (find(i) == root) group.add(i);
        }
        
        for (int i : group) {
            parent[i] = i;
            value[i] = "EMPTY";
        }
        value[idx] = val;
    }
}
