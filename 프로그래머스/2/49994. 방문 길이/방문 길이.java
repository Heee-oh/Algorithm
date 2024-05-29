import java.util.*;

class Solution {
    public int solution(String dirs) {
        int currentX = 0, currentY = 0;
        // 이전X , 이동후 X, 이전Y, 이동후 Y
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (char command : dirs.toCharArray()) {
            if (command == 'L' && currentX - 1 >= -5) {
                sb.append(currentX).append(currentX - 1).append(currentY).append(currentY);
                if (set.contains(sb.toString())) {
                    sb.delete(0, sb.length());
                    currentX--;
                    continue;
                } 
                set.add(sb.toString());
                sb.delete(0, sb.length());
                sb.append(currentX - 1).append(currentX).append(currentY).append(currentY);
                set.add(sb.toString());
                sb.delete(0, sb.length());
                currentX--;
                    
            }else if (command == 'D' && currentY - 1 >= -5) {
                sb.append(currentX).append(currentX).append(currentY).append(currentY - 1);
                if (set.contains(sb.toString())) {
                    sb.delete(0, sb.length());
                    currentY--;
                    continue;
                } 
                set.add(sb.toString());
                sb.delete(0, sb.length());
                sb.append(currentX).append(currentX).append(currentY - 1).append(currentY);
                set.add(sb.toString());
                sb.delete(0, sb.length());
                currentY--;
                
            }else if (command == 'R' && currentX + 1 <= 5) {
                sb.append(currentX).append(currentX + 1).append(currentY).append(currentY);
                if (set.contains(sb.toString())) {
                    sb.delete(0, sb.length());
                    currentX++;
                    continue;
                } 
                set.add(sb.toString());
                sb.delete(0, sb.length());
                sb.append(currentX + 1).append(currentX).append(currentY).append(currentY);
                set.add(sb.toString());
                sb.delete(0, sb.length());
                currentX++;
                    
            }else if (command == 'U' && currentY + 1 <= 5) {
                sb.append(currentX).append(currentX).append(currentY).append(currentY + 1);
                if (set.contains(sb.toString())) {
                    sb.delete(0, sb.length());
                    currentY++;
                    continue;
                } 
                set.add(sb.toString());
                sb.delete(0, sb.length());
                sb.append(currentX).append(currentX).append(currentY + 1).append(currentY);
                set.add(sb.toString());
                sb.delete(0, sb.length());
                currentY++;

            }
        }

        
        return  set.size()/2;
    }
}