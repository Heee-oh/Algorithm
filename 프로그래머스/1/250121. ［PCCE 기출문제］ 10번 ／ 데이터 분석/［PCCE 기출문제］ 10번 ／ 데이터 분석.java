import java.util.*;

class Solution {
    public  int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer;
        int filterIndex = indexTranslator(ext);
        List<int[]> filterList = new ArrayList<>();

        for (int[] datum : data) {
            if (datum[filterIndex] < val_ext)
                filterList.add(datum);
        }


        filterList.sort((o1, o2) -> {
            if (o1[indexTranslator(sort_by)] - o2[indexTranslator(sort_by)] < 0) return -1;
            else {
                return 1;
            }
        });

        answer = new int[filterList.size()][4];

        for (int i = 0; i < filterList.size(); i++) {
            answer[i] = filterList.get(i);
        }

        return  answer;
    }

    private  int indexTranslator(String value) {
        switch (value) {
            case  "code" : return 0;
            case "date" : return 1;
            case  "maximum" : return 2;
            default: return 3;
        }
    }
}