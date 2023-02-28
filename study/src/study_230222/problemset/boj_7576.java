package study_230222.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7576 {
    static int n, m, cnt = -1;
    static int[][] map; // 토마토 저장 맵
    static int[] dx = { 1, -1, 0, 0 }; // bfs 탐색하기 위한 배열
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited; // 방문 체크
    static ArrayList<int[]> pos = new ArrayList<>(); // 값이 1인 (x, y)를 저장할 리스트

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 값 입력 및 초기화
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == 1) // 1인 값의 위치를 따로 저장
                    pos.add(new int[] { i, j });
            }
        }

        bfs(); // bfs 탐색

        // 0이 있으면 -1 출력 / 아니면 cnt 출력
        if (check())
            sb.append(-1);
        else
            sb.append(cnt);
        System.out.println(sb);
    }

    // bfs 탐색
    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        int size = 0;

        // 1인 값을 queue에 저장
        for (int i = 0; i < pos.size(); i++) {
            int x = pos.get(i)[0];
            int y = pos.get(i)[1];
            queue.add(pos.get(i));
            visited[x][y] = true;
        }

        // queue가 빌 때까지 실행
        while (!queue.isEmpty()) {
            size = queue.size(); // 날짜를 세기 위해 size에 queue.size() 저장
            cnt++; // cnt 증가

            for (int s = 0; s < size; s++) {
                int curX = queue.peek()[0];
                int curY = queue.poll()[1];

                for (int i = 0; i < 4; i++) {
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        if (!visited[nx][ny] && map[nx][ny] == 0) { // 0인 경우에만 값 1로 변경
                            visited[nx][ny] = true;
                            map[nx][ny] = 1;
                            queue.add(new int[] { nx, ny });
                        }
                    }
                }
            }
        }

        return;
    }

    // 0이 있는지 체크
    static boolean check() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (map[i][j] == 0)
                    return true;

        return false;
    }
}
