package study_231025.problemset;

import java.util.*;

class Solution {
    int maxArea = 0, number = -1;
    boolean[][] visited;
    int[] dx = { 1, -1, 0, 0 };
    int[] dy = { 0, 0, 1, -1 };

    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        visited = new boolean[m][n]; // 초기화

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 빈 공간 체크
                if (picture[i][j] == 0) {
                    continue;
                }

                // 방문 체크
                if (visited[i][j]) {
                    continue;
                }

                bfs(picture[i][j], i, j, picture, n, m); // BFS
            }
        }

        // 정답 리턴
        answer[0] = maxArea;
        answer[1] = number;

        return answer;
    }

    // BFS
    public void bfs(int num, int x, int y, int[][] picture, int n, int m) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });
        visited[x][y] = true;

        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                // 빈 공간 체크
                if (picture[nx][ny] == 0) {
                    continue;
                }

                // 같은 영역이 아닌 경우 체크
                if (picture[nx][ny] != num) {
                    continue;
                }

                // 방문 여부 체크
                if (visited[nx][ny]) {
                    continue;
                }

                queue.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                cnt++; // 개수 증가
            }
        }

        // 최댓값 갱신
        if (number < cnt) {
            number = cnt;
        }

        maxArea++; // 영역 증가
    }
}