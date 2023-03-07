package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1194 {
    static int n, m, ans = Integer.MAX_VALUE;
    static char[][] map;
    static boolean[][][] visited; // 비트마스킹 방문 배열
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static class Node {
        int x, y, move, bit;

        // 좌표, 이동 횟수, 현재 먹은 알파벳 (비트마스킹)
        Node(int x, int y, int move, int bit) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.bit = bit;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 초기화
        map = new char[n][m];
        visited = new boolean[n][m][64];

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') { // 시작 위치 정점
                    map[i][j] = '.';
                    x = i;
                    y = j;
                }
            }
        }

        bfs(x, y);

        if (ans == Integer.MAX_VALUE)
            sb.append(-1);
        else
            sb.append(ans);
        System.out.println(sb);
    }

    static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y, 0, 0));

        visited[x][y][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (map[cur.x][cur.y] == '1') {
                ans = cur.move;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (visited[nx][ny][cur.bit])
                        continue;

                    char c = map[nx][ny];
                    if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                        visited[nx][ny][cur.bit | (1 << (c - 'a'))] = true;
                        queue.add(new Node(nx, ny, cur.move + 1, cur.bit | (1 << (c - 'a'))));
                    } else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                        if ((cur.bit & (1 << (c - 'A'))) != 0) {
                            visited[nx][ny][cur.bit] = true;
                            queue.add(new Node(nx, ny, cur.move + 1, cur.bit));
                        }
                    } else if (map[nx][ny] == '.' || map[nx][ny] == '1') {
                        visited[nx][ny][cur.bit] = true;
                        queue.add(new Node(nx, ny, cur.move + 1, cur.bit));
                    }
                }
            }
        }
    }
}
