import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {        
        HashMap<Character, Integer> map = new HashMap<>();
        int answer = 0, rightSequence = 0;
        
        // 맵 초기화
        for (int i = 0; i < skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }
        
        
        for (int i = 0; i < skill_trees.length; i++) {
            boolean flag = true;
            rightSequence = 0;
            for (int j = 0; j < skill_trees[i].length(); j++) {
                char skillName = skill_trees[i].charAt(j);
                int currentSkill = map.getOrDefault(skillName, -1);
                
                if (currentSkill == -1) {
                  continue;
                    
                } else if (rightSequence != currentSkill) {
                    rightSequence = 0;
                    flag = false;
                    break;
                }
                
                rightSequence++;
            }
            
            if (flag) answer++;
        }
        
        
        return answer;
    }
}