import java.beans.Customizer;
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static Student[] students;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};


    static class Student {
        int num;
        Set<Integer> like;

        public Student(int num) {
            this.num = num;
            like = new HashSet<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        students = new Student[N * N];
        int[] numbers = new int[N * N + 1]; // 인덱싱용
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            students[i] = new Student(num);
            numbers[num] = i;

            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                students[i].like.add(a);
            }
        }

        // 자리 탐색
        for (int i = 0; i < N * N; i++) {
            Student student = students[i];
            int studentNum = student.num;
            Set<Integer> likeStudent = student.like;

            int newR = 0, newC = 0;
            int curEmpty = -1, curLike = -1;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] != 0) continue;
                    int adjacentCell = 0;
                    int emptyCell = 0;

                    for (int j = 0; j < 4; j++) {
                        int nr = r + dr[j];
                        int nc = c + dc[j];

                        if (nr < 0 || nr >= N
                                || nc < 0 || nc >= N) continue;

                        // 비어있다면
                        if (map[nr][nc] == 0) {
                            emptyCell++;
                        } else {
                            // 좋아하는 학생이라면
                            if (likeStudent.contains(map[nr][nc])) {
                                adjacentCell++;
                            }

                        }
                    }

                    // 좋아하는 학생이 1명도 없다면 빈칸이 많은 곳을 선정
                    if (adjacentCell == 0 && curLike == 0) {
                        if (curEmpty < emptyCell) {
                            curEmpty = emptyCell;
                            newR = r;
                            newC = c;
                        }

                        // 좋아하는 학생이 더 많은 칸을 찾았다면
                    }else {
                        if (adjacentCell > curLike ) {
                            newR = r;
                            newC = c;

                            curLike = adjacentCell;
                            curEmpty = emptyCell;

                            // 좋아하는 학생이 인접한  칸의 개수가 같은 게 여러개일때
                        } else if (adjacentCell == curLike) {
                            if (curEmpty < emptyCell) { // 빈칸이 더 많은 곳으로
                                newR = r;
                                newC = c;

                                curEmpty = emptyCell;
                            }
                        }
                    }
                }
            }
            map[newR][newC] = studentNum;

        }




        // 합 구하기
        int ans = 0;
        int[] score = {0, 1, 10, 100, 1000};
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int num = map[r][c];
                int idx = numbers[num];

                Set<Integer> like = students[idx].like;
                int cnt = 0;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    if (like.contains(map[nr][nc])) {
                        cnt++;
                    }
                }

                ans += score[cnt];
            }
        }

        System.out.println(ans);

    }


}

