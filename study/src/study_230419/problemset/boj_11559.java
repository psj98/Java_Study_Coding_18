package study_230419.problemset;

import java.io.*;
import java.util.*;

public class boj_11559 {
    static int n = 12, m = 6;
    static char[][] map = new char[n][m];
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static Queue<int[]> pos = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기화 및 저장
        int combo = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++)
                map[i][j] = str.charAt(j);
        }

        while (true) {
            boolean check = false; // 콤보가 있는지 체크
            visited = new boolean[n][m]; // 방문 체크 배열
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') // 색깔 Puyo가 아니면 (빈 공간)
                        continue;

                    bfs(i, j, map[i][j]); // BFS 탐색

                    if (pos.size() >= 4) { // 같은 색의 인접한 Puyo가 4개 이상이면
                        while (!pos.isEmpty()) {
                            map[pos.peek()[0]][pos.poll()[1]] = '.'; // 빈 공간으로 변경
                        }

                        check = true; // 콤보가 생겼음
                    } else {
                        pos.clear(); // 아니면 clear()
                    }

                }
            }

            if (!check)
                break;

            gravity();
            combo++;
        }

        // 정답 출력
        sb.append(combo);
        System.out.println(sb);
    }

    // BFS 탐색 -> 같은 색의 인접한 Puyo를 탐색
    static void bfs(int x, int y, char color) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });
        pos.add(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                // 방문 체크
                if (visited[nx][ny])
                    continue;

                // 같은 색 체크
                if (map[nx][ny] != color)
                    continue;

                queue.add(new int[] { nx, ny });
                pos.add(new int[] { nx, ny });
                visited[nx][ny] = true;
            }
        }
    }

    // 아래로 쌓기
    static void gravity() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[j][i] != '.') { // '.' 이 아닌 것 stack에 저장
                    stack.add(map[j][i]);
                    map[j][i] = '.'; // '.' 으로 변경
                }
            }

            // 거꾸로 가면서 스택에서 뺀 값을 저장
            int height = n - 1;
            while (!stack.isEmpty()) {
                map[height--][i] = stack.pop();
            }
        }
    }
}
