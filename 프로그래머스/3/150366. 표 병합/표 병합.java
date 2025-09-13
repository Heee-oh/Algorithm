import java.util.*;

class Solution {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) return false;
            Point other = (Point) obj;
            return this.r == other.r && this.c == other.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        Point[][] parent = new Point[51][51];
        Map<Point, String> valueMap = new HashMap<>();

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                parent[i][j] = new Point(i, j);
            }
        }

        for (String cmd : commands) {
            String[] parts = cmd.split(" ");

            if (parts[0].equals("UPDATE")) {
                if (parts.length == 4) {
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    String val = parts[3];
                    Point root = find(parent, r, c);
                    valueMap.put(root, val);
                } else {
                    String val1 = parts[1];
                    String val2 = parts[2];
                    for (Point key : valueMap.keySet()) {
                        if (valueMap.get(key).equals(val1)) {
                            valueMap.put(key, val2);
                        }
                    }
                }

            } else if (parts[0].equals("MERGE")) {
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);

                Point p1 = find(parent, r1, c1);
                Point p2 = find(parent, r2, c2);

                if (p1.equals(p2)) continue;

                String val1 = valueMap.getOrDefault(p1, null);
                String val2 = valueMap.getOrDefault(p2, null);

                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        if (find(parent, i, j).equals(p2)) {
                            parent[i][j] = p1;
                        }
                    }
                }

                if (val1 == null && val2 != null) {
                    valueMap.put(p1, val2);
                } else if (val1 != null) {
                    valueMap.put(p1, val1);
                }

                valueMap.remove(p2);

            } else if (parts[0].equals("UNMERGE")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                Point root = find(parent, r, c);
                String val = valueMap.get(root);

                List<Point> group = new ArrayList<>();
                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        if (find(parent, i, j).equals(root)) {
                            group.add(new Point(i, j));
                        }
                    }
                }

                for (Point p : group) {
                    parent[p.r][p.c] = new Point(p.r, p.c);
                }

                valueMap.remove(root);
                if (val != null) {
                    valueMap.put(new Point(r, c), val);
                }

            } else if (parts[0].equals("PRINT")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                Point root = find(parent, r, c);
                answer.add(valueMap.getOrDefault(root, "EMPTY"));
            }
        }

        return answer.toArray(new String[0]);
    }

    private Point find(Point[][] parent, int r, int c) {
        Point p = parent[r][c];
        if (p.r == r && p.c == c) return p;
        Point root = find(parent, p.r, p.c);
        parent[r][c] = root; // path compression
        return root;
    }
}
