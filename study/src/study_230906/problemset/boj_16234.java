import java.io.*;
import java.util.*;

public class boj_16234 {
    static int N, L, R;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited;
    static ArrayList<int[]> move;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(stk.nextToken()); // 행 
        L = Integer.parseInt(stk.nextToken()); // L 이상 조건
        R = Integer.parseInt(stk.nextToken()); // R 이하 조건

        // 초기화
        map = new int[N][N];

        // 맵 정보
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        int time = 0; // 시간
        while (true) {
            boolean check = false; // 인구 이동 여부 체크
            visited = new boolean[N][N]; // 방문 배열

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) { // 방문 체크
                        continue;
                    }

                    move = new ArrayList<>(); // 인구 이동할 국가 좌표 리스트
                    int sum = bfs(i, j); // BFS 탐색

                    // 이동할 국가가 없는 경우
                    if (move.size() <= 1) {
                        continue;
                    }

                    // 인구 갱신
                    sum /= move.size();
                    for (int[] cur : move) {
                        map[cur[0]][cur[1]] = sum;
                    }

                    check = true; // 인구 이동 여부 체크
                }
            }

            // 인구 이동하지 않았다면 break
            if (!check) {
                break;
            }

            time++; // 시간 증가
        }

        // 정답 출력
        sb.append(time);
        System.out.println(sb);
    }

    // BFS 탐색
    static int bfs(int x, int y) {
        int sum = map[x][y];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] { x, y });
        move.add(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                // 방문 체크
                if (visited[nx][ny]) {
                    continue;
                }

                // L 이상 R 이하 체크
                int diff = Math.abs(map[nx][ny] - map[cur[0]][cur[1]]);
                if (diff < L || diff > R) {
                    continue;
                }

                // 저장
                queue.add(new int[] { nx, ny });
                move.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                sum += map[nx][ny]; /// 합 갱신
            }
        }

        return sum;
    }
}
