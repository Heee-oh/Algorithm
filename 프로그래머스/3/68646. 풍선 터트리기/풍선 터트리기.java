import java.util.*;

class Solution {
    
    static int[] trees;
    
    public int solution(int[] a) {
        int answer = 0;
        int h = (int)Math.ceil(Math.log(a.length) / Math.log(2)) + 1;
        trees = new int[(int)Math.pow(2,h)];
        
        Arrays.fill(trees, Integer.MAX_VALUE);
        
        
        init(a, 1, 0, a.length - 1);
        
        
        for (int i = 0; i < a.length; i++) {
            int leftMin = find(1, 0, a.length - 1, 0, i-1);
            int rightMin = find(1, 0, a.length - 1, i+1, a.length - 1);
            
            if (leftMin > a[i] || rightMin > a[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int init(int[] a, int node, int s, int e) {
        if (s >= trees.length || e < 0) {
            return Integer.MAX_VALUE;
        }
        
        if (s == e) {
            return trees[node] = a[s];
        }
        
        int mid = (s + e) >>> 1;
        
        
        return trees[node] = Math.min(init(a, node * 2, s, mid),
                                    init(a, node * 2 + 1, mid + 1, e));
        
    }
    
    private int find(int node, int s, int e, int l, int r) {
        if (l > e || r < s) return Integer.MAX_VALUE;
    
        if (l <= s && e <= r) {
            return trees[node];
        }
        
        
        int mid = (s + e) >>> 1;
        
        return Math.min(find(node * 2, s, mid, l, r),
                            find(node * 2 + 1, mid + 1, e, l, r));
        
    }
}