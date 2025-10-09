import java.util.*;

class Solution {

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static Map<Integer, String> idxMap = new HashMap<>();
    static int[][] map;
    static int id = 1;

    public String[] solution(String[] commands) {
        List<String> print = new ArrayList<>();
        map = new int[51][51];

        for (String command : commands) {
            String[] cmd = command.split(" ");
            String op = cmd[0];

            if (op.equals("UPDATE")) {
                // 위치 값 변경
                if (cmd.length == 4) {
                    int r = Integer.parseInt(cmd[1]);
                    int c = Integer.parseInt(cmd[2]);
                    String value = cmd[3];
                    int v = map[r][c];

                    // 이미 존재하는 집합에 대한 값을 수정
                    if (v != 0) {
                        idxMap.put(v, value);
                    } 
                    // 아니라면 새로운 집합 대푯값 생성
                    else {
                        idxMap.put(id, value);
                        map[r][c] = id;
                        id++;
                    }

                } else {
                    // 값 일괄 변경
                    String oldValue = cmd[1];
                    String newValue = cmd[2];
                    for (Map.Entry<Integer, String> entry : idxMap.entrySet()) {
                        if (entry.getValue().equals(oldValue)) {
                            entry.setValue(newValue);
                        }
                    }
                }

            } else if (op.equals("MERGE")) {
                int r1 = Integer.parseInt(cmd[1]);
                int c1 = Integer.parseInt(cmd[2]);
                int r2 = Integer.parseInt(cmd[3]);
                int c2 = Integer.parseInt(cmd[4]);

                // 두 셀이 같을 경우 무시
                if ((r1 == r2 && c1 == c2) || 
                    (map[r1][c1] == map[r2][c2] && map[r1][c1] != 0)) {
                    continue;
                }

                merge(r1, c1, r2, c2);

            } else if (op.equals("UNMERGE")) {
                int ur = Integer.parseInt(cmd[1]);
                int uc = Integer.parseInt(cmd[2]);
                int target = map[ur][uc];

                for (int r = 1; r < 51; r++) {
                    for (int c = 1; c < 51; c++) {
                        if (map[r][c] == target) {
                            map[r][c] = 0;
                        }
                    }
                }
                map[ur][uc] = target;

            } else if (op.equals("PRINT")) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                print.add(map[r][c] == 0 ? "EMPTY" : idxMap.get(map[r][c]));
            }
        }

        return print.toArray(String[]::new);
    }

    // r2,c2가 기준
    private void merge(int r1, int c1, int r2, int c2) {
        int id1 = map[r1][c1];
        int id2 = map[r2][c2];
        

        // 둘다 집합이 없다면 새로 생성하여 빈 집합 생성
        if (id1 == 0 && id2 == 0) {
            map[r1][c1] = map[r2][c2] = id;
            idxMap.put(id++, "EMPTY");
            return;
        }
        int baseId;
        int target;
        
        // 값의 경우 최대한 id1의 값을 선택하되 없다면 존재하는 값을 선택
        String val1 = (id1 == 0) ? "EMPTY" : idxMap.get(id1);
        String val2 = (id2 == 0) ? "EMPTY" : idxMap.get(id2);
        String mergedVal = !val1.equals("EMPTY") ? val1 : val2;
        
        // id1을 우선으로 
        if (id1 == 0 && id2 != 0) baseId = id2;
        else if (id1 != 0 && id2 == 0) baseId = id1;
        else baseId = id1; 
        
        target = (id1 != 0 && id2 != 0) ? id2 : (id1 == 0 ? id2 : id1);

        // 병합
        for (int r = 1; r < 51; r++) {
            for (int c = 1; c < 51; c++) {
                if (map[r][c] == target) {
                    map[r][c] = baseId;
                }
            }
        }
        
        // 한쪽이 id값이 0일 경우 (반대쪽은 0 아님)  baseId가 반대로 설정되고
        // target도 반대쪽으로 설정되면 결과적으로 id값이 0 인 곳은 baseId가 갱신되지 않음 (if가 target으로 걸림)
        // 때문에 따로 이렇게 세팅해줘야함
        map[r1][c1] = baseId;
        map[r2][c2] = baseId;
        idxMap.put(baseId, mergedVal);
    }
}
