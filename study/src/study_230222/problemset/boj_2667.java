package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class boj_2667 {
    static int n;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기화
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        // 맵에 값 저장
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++)
                map[i][j] = str.charAt(j) - '0';
        }

        // 각 좌표에 대해 bfs 탐색 -> 1이고 방문하지 않은 곳
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (map[i][j] == 1 && !visited[i][j])
                    bfs(i, j);

        // 오름차순 정렬
        Collections.sort(list);
        sb.append(list.size()).append("\n");
        for (int i = 0; i < list.size(); i++)
            sb.append(list.get(i)).append("\n");
        System.out.println(sb);
    }

    // BFS로 넓이 탐색
    static void bfs(int x, int y) {
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int curX = queue.peek()[0];
            int curY = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && map[nx][ny] == 1) {
                    queue.add(new int[] { nx, ny });
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }

        list.add(cnt); // 넓이 저장
    }
}
