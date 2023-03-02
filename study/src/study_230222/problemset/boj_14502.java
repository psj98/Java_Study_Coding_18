package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14502 {
    static int n, m, ans = Integer.MIN_VALUE;
    static int[][] map;
    static int[] posIdx = new int[3];
    static ArrayList<int[]> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                // 바이러스 좌표 저장
                if (map[i][j] == 2)
                    virus.add(new int[] { i, j });
            }
        }

        comb(0, 0); // 조합 찾기

        sb.append(ans);
        System.out.println(sb);
    }

    // 조합 찾기
    static void comb(int cnt, int idx) {
        // 벽 3개를 찾으면
        if (cnt == 3) {
            int[][] copyMap = new int[n][m]; // 기존 맵 복사할 배열
            for (int i = 0; i < n; i++)
                copyMap[i] = Arrays.copyOf(map[i], m);

            // 벽 세우기
            for (int i = 0; i < 3; i++) {
                int x = posIdx[i] / m;
                int y = posIdx[i] % m;
                copyMap[x][y] = 1;
            }

            // 바이러스 퍼뜨리기
            for (int i = 0; i < virus.size(); i++)
                bfs(virus.get(i)[0], virus.get(i)[1], copyMap);

            ans = Math.max(ans, cntzero(copyMap)); // 안전 영역 개수 최댓값 갱신

            return;
        }

        // 조합 찾기
        for (int i = idx; i < n * m; i++) {
            // 값이 0인 경우
            if (map[i / m][i % m] == 0) {
                posIdx[cnt] = i; // index 저장
                comb(cnt + 1, i + 1); // 조합 찾기
            }
        }
    }

    // bfs 탐색 (바이러스 퍼뜨리기)
    static void bfs(int x, int y, int[][] copyMap) {
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };
        boolean[][] visited = new boolean[n][m]; // 방문 체크 배열
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] { x, y });
        visited[x][y] = true; // 방문 체크

        while (!queue.isEmpty()) {
            int curX = queue.peek()[0];
            int curY = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                // 범위 체크 + 방문 안한 곳 + 0(빈 곳)일 경우
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && copyMap[nx][ny] == 0) {
                        queue.add(new int[] { nx, ny }); // 좌표 추가
                        copyMap[nx][ny] = 2; // 바이러스로 변경
                        visited[nx][ny] = true; // 방문 체크
                    }
                }
            }
        }
    }

    // 빈 곳 영역 개수 구하기
    static int cntzero(int[][] copyMap) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (copyMap[i][j] == 0)
                    sum++;

        return sum;
    }
}
