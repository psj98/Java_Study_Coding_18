package study_240215.problemset;

import java.io.*;
import java.util.*;

public class boj_17779 {
    static int n, total = 0; // 맵 크기, 총 사람 수
    static int[][] map; // 맵 정보
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        map = new int[n][n]; // 맵 초기화

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                total += map[i][j]; // 전체 합
            }
        }

        // 가능한 x, y, d1, d2 값
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (i + d1 + d2 >= n) {
                            continue;
                        }

                        if (j - d1 < 0 || j + d2 >= n) {
                            continue;
                        }

                        find(i, j, d1, d2); // 최솟값 찾기
                    }
                }
            }
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 최솟값 찾기
    static void find(int x, int y, int d1, int d2) {
        int[] people = new int[5]; // 구간 별 사람 수
        boolean[][] visited = new boolean[n][n]; // 경계 체크

        // 경계 정보
        for (int i = 0; i <= d1; i++) {
            visited[x + i][y - i] = true;
            visited[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            visited[x + i][y + i] = true;
            visited[x + d1 + i][y - d1 + i] = true;
        }

        // 1번 선거구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (visited[i][j]) {
                    break;
                }

                people[0] += map[i][j];
            }
        }

        // 2번 선거구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (visited[i][j]) {
                    break;
                }

                people[1] += map[i][j];
            }
        }

        // 3번 선거구
        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (visited[i][j]) {
                    break;
                }

                people[2] += map[i][j];
            }
        }

        // 4번 선거구
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (visited[i][j]) {
                    break;
                }

                people[3] += map[i][j];
            }
        }

        // 5번 선거구
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += people[i];
        }

        people[4] = total - sum;

        Arrays.sort(people); // 오름차순 정렬

        // 최솟값 갱신
        ans = Math.min(ans, people[4] - people[0]);
    }
}
