package study_230614.problemset;

import java.io.*;
import java.util.*;

public class boj_19238 {
    static int n, m, fuel, taxiX, taxiY;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static Customer[] customers;

    // 손님 정보
    static class Customer implements Comparable<Customer> {
        int startX, startY, endX, endY, dist;

        Customer(int startX, int startY, int endX, int endY, int dist) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.dist = dist;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.dist == o.dist) { // 거리 같은 경우, 행 비교
                if (this.startX == o.startX) { // 행 같은 경우, 열 비교
                    return this.startY - o.startY; // 열 오름차순
                } else {
                    return this.startX - o.startX; // 행 오름차순
                }
            }

            return this.dist - o.dist; // 거리 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        fuel = Integer.parseInt(stk.nextToken()); // 연료

        // 0은 빈칸, -1은 벽
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == 1) // 벽인 경우, -1로 변경
                    map[i][j] = -1;
            }
        }

        // 택시 위
        stk = new StringTokenizer(br.readLine());
        taxiX = Integer.parseInt(stk.nextToken()) - 1;
        taxiY = Integer.parseInt(stk.nextToken()) - 1;

        boolean check = false;
        customers = new Customer[m];
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(stk.nextToken()) - 1;
            int startY = Integer.parseInt(stk.nextToken()) - 1;
            int endX = Integer.parseInt(stk.nextToken()) - 1;
            int endY = Integer.parseInt(stk.nextToken()) - 1;

            int dist = bfs(startX, startY, endX, endY); // 손님 ~ 목적지 간 거리 구하기
            if (dist == -1) { // 목적지로 못가는 경우, check = true
                check = true;
            }

            customers[i] = new Customer(startX, startY, endX, endY, dist); // 손님 정보 추가
            map[startX][startY] = i + 1; // 손님 번호를 맵에 추가
        }

        // 목적지로 못 가는 경우, -1 출력
        if (check) {
            sb.append(-1);
            System.out.println(sb);
            return;
        }

        // 손님 수만큼 진행
        while (m-- != 0) {
            int[] next = bfs(taxiX, taxiY); // 택시에서 가장 가까운 손님 찾기
            if (next[0] == -1) { // 손님을 못찾은 경우, -1 출력
                sb.append(-1);
                System.out.println(sb);
                return;
            }

            Customer customerInfo = customers[next[0] - 1]; // 현재 손님 정보
            fuel = fuel - next[1] - customerInfo.dist; // 목적지까지 이동 후, 남은 연료

            // 연료가 0보다 작으면, 더 이상 진행 불가 -> -1 출력
            if (fuel < 0) {
                sb.append(-1);
                System.out.println(sb);
                return;
            }

            fuel += customerInfo.dist * 2; // 연료 계산 -> + 손님 ~ 목적지 * 2
            map[customerInfo.startX][customerInfo.startY] = 0; // 빈 공간으로 변경

            // 택시 좌표 변경
            taxiX = customerInfo.endX;
            taxiY = customerInfo.endY;
        }

        // 정답 출력
        sb.append(fuel);
        System.out.println(sb);
    }

    // 택시에서 가장 가까운 손님 찾기
    static int[] bfs(int x, int y) {
        PriorityQueue<Customer> pq = new PriorityQueue<>();
        pq.add(new Customer(x, y, 0, 0, 0));

        boolean[][] visited = new boolean[n][n];
        visited[x][y] = true;

        while (!pq.isEmpty()) {
            Customer cur = pq.poll();

            // 손님인 경우, 손님 번호와 거리 반환
            if (map[cur.startX][cur.startY] >= 1) {
                return new int[] { map[cur.startX][cur.startY], cur.dist };
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.startX + dx[i];
                int ny = cur.startY + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                // 벽 체크
                if (map[nx][ny] == -1)
                    continue;

                // 방문 체크
                if (visited[nx][ny])
                    continue;

                pq.add(new Customer(nx, ny, 0, 0, cur.dist + 1));
                visited[nx][ny] = true;
            }
        }

        return new int[] { -1, -1 }; // 손님을 못찾은 경우, {-1, -1} 반환
    }

    // 손님 ~ 목적지 거리 구하기
    static int bfs(int startX, int startY, int endX, int endY) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { startX, startY, 0 });

        boolean[][] visited = new boolean[n][n];
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 목적지에 도착한 경우, 거리 반환
            if (cur[0] == endX && cur[1] == endY)
                return cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                // 벽 체크
                if (map[nx][ny] == -1)
                    continue;

                // 방문 체크
                if (visited[nx][ny])
                    continue;

                queue.add(new int[] { nx, ny, cur[2] + 1 });
                visited[nx][ny] = true;
            }
        }

        return -1; // 목적지로 못가는 경우, -1 반환
    }
}
