package study_231108.problemset;

import java.util.*;

class Solution {
    int n;
    boolean[][][] visited;
    int[] dx = { 1, -1, 0, 0 };
    int[] dy = { 0, 0, 1, -1 };

    public class Node {
        int x, y, cost, dir; // 좌표, 비용, 방향

        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }

    public int solution(int[][] board) {
        n = board.length;
        visited = new boolean[n][n][4];

        return bfs(board);
    }

    public int bfs(int[][] board) {
        int ans = Integer.MAX_VALUE;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, -1));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 최솟값 갱신
            if (cur.x == n - 1 && cur.y == n - 1) {
                ans = Math.min(ans, cur.cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                // 벽 체크
                if (board[nx][ny] == 1) {
                    continue;
                }

                // 비용 계산
                int cost = cur.cost;
                if (cur.dir == -1 || cur.dir == i) {
                    cost += 100;
                } else {
                    cost += 600;
                }

                // 방문하지 않은 경우 or 비용이 더 적은 경우
                if (!visited[nx][ny][i] || board[nx][ny] >= cost) {
                    queue.add(new Node(nx, ny, cost, i));
                    board[nx][ny] = cost;
                    visited[nx][ny][i] = true;
                }
            }
        }

        return ans;
    }
}