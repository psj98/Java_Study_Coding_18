package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2573 {
    static int n, m; // 맵 크기
    static int[][] map; // 맵 정보
    static boolean[][] zeroCheck, visited; // 0인지 체크, dfs 방문 여부 체크
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];
        zeroCheck = new boolean[n][m];

        // 맵 정보 저장 | map의 값이 0이면 true, 0이 아니면 false로 저장
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                zeroCheck[i][j] = map[i][j] == 0 ? true : false;
            }
        }

        // 다 녹을 때까지 반복 -> 한 번 수행할 때마다 분리 여부를 확인하기 때문에 cnt++
        while (!melt()) {
            cnt++;
        }

        sb.append(cnt);
        System.out.println(sb);
    }

    // 빙산 녹이기
    static boolean melt() {
        // 영역이 나누어지면 종료
        if (checkArea()) {
            return true;
        } else {
            // 영역이 나누어지지 않으면 빙산 녹이기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // map이 0이 아닌 곳, 범위 체크, 맵 주변이 0인 곳 -> map이 음수가 되면 0으로 변경
                        if (!zeroCheck[i][j] && nx >= 0 && nx < n && ny >= 0 && ny < m && zeroCheck[nx][ny]) {
                            if (map[i][j] > 0)
                                map[i][j]--;
                        }
                    }
                }
            }

            // 빙산이 모두 녹아서 없어지는 경우
            if (checkSum()) {
                System.out.println(0);
                System.exit(0);
            }

            return false;
        }
    }

    // 빙산이 모두 녹아서 없어졌을 때 체크
    static boolean checkSum() {
        int zero = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    zeroCheck[i][j] = true;
                    zero++;
                }
            }
        }

        if (zero == n * m) {
            return true;
        } else {
            return false;
        }
    }

    // 분리 영역 체크
    static boolean checkArea() {
        int area = 0;
        visited = new boolean[n][m];

        // 0이 아닌 곳 + 방문하지 않은 곳 탐색 = 탐색 완료되면 area 증가
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!zeroCheck[i][j] && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                    area++;
                }
            }
        }

        // 영역이 2 이상으로 나누어지면 true / 아니면 false
        return area >= 2 ? true : false;
    }

    // 영역 탐색
    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !zeroCheck[nx][ny] && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }

        return;
    }
}
