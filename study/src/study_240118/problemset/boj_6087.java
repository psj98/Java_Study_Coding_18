package study_240118.problemset;

import java.io.*;
import java.util.*;

public class boj_6087 {
    static int n, m; // 맵 크기
    static char[][] map; // 맵 정보
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };

    static class Node implements Comparable<Node> {
        int x, y, dir, mirror; // 위치, 방향, 거울 개수

        public Node(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }

        @Override
        public int compareTo(Node o) {
            return this.mirror - o.mirror;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());

        map = new char[n][m];

        // 맵 정보
        int startX = 0, startY = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                // 시작 위치 저장
                if (map[i][j] == 'C') {
                    startX = i;
                    startY = j;
                }
            }
        }

        map[startX][startY] = '.'; // 초기화

        // 정답 출력
        sb.append(bfs(startX, startY)); // BFS
        System.out.println(sb);
    }

    // BFS
    static int bfs(int startX, int startY) {
        int ans = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][][] visited = new int[n][m][4]; // 방문 체크

        // 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        pq.add(new Node(startX, startY, 10, -1)); // 시작 위치

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 연결한 경우, 최솟값 갱신
            if (map[cur.x][cur.y] == 'C') {
                ans = Math.min(ans, cur.mirror);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 장애물 체크
                if (map[nx][ny] == '*') {
                    continue;
                }

                // 이전에 왔던 방향 체크
                if (Math.abs(cur.dir - i) == 2) {
                    continue;
                }

                // 거울을 배치해야 하는 경우
                int nextMirror = cur.mirror;
                if (cur.dir != i) {
                    nextMirror++;
                }

                // 최솟값이 아닌 경우
                if (visited[nx][ny][i] <= nextMirror) {
                    continue;
                }

                pq.offer(new Node(nx, ny, i, nextMirror));
                visited[nx][ny][i] = nextMirror;
            }
        }

        return ans;
    }
}
