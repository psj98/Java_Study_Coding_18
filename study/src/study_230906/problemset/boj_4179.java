import java.io.*;
import java.util.*;

public class boj_4179 {
    static int n, m;
    static char[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static Queue<Node> fire = new ArrayDeque<>();
    static Queue<Node> jihun = new ArrayDeque<>();

    static class Node {
        int x, y, time; // 좌표, 시간

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken()); // 행 수
        m = Integer.parseInt(stk.nextToken()); // 열 수

        // 초기화
        map = new char[n][m];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'J') { // 지훈 Queue에 추가 (좌표, 시간)
                    jihun.add(new Node(i, j, 0));
                } else if (map[i][j] == 'F') { // 불 Queue에 추가 (좌표, 시간)
                    fire.add(new Node(i, j, 0));
                }
            }
        }

        int ans = bfs(); // BFS 탐색
        if (ans == -1) { // -1 이면 탈출 불가능
            sb.append("IMPOSSIBLE");
        } else { // -1이 아니면 탈출 가능
            sb.append(ans);
        }

        // 정답 출력
        System.out.println(sb);
    }

    // BFS 탐색
    static int bfs() {
        // 지훈이 계속 갈 수 있을 때까지 반복
        while (!jihun.isEmpty()) {
            // 불 Queue 실행
            int fireSize = fire.size(); // 시간으로 나타내야 하기 때문에 size로 반복

            for (int f = 0; f < fireSize; f++) {
                Node curFire = fire.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = curFire.x + dx[i];
                    int ny = curFire.y + dy[i];

                    // 범위 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    // 벽, 이전 불 위치 체크 => 중복 제거
                    if (map[nx][ny] == '#' || map[nx][ny] == 'F') {
                        continue;
                    }

                    // Queue에 추가
                    fire.add(new Node(nx, ny, curFire.time + 1));
                    map[nx][ny] = 'F';
                }
            }

            // 지훈 Queue 실행
            int jihunSize = jihun.size();
            for (int j = 0; j < jihunSize; j++) {
                Node curJihun = jihun.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = curJihun.x + dx[i];
                    int ny = curJihun.y + dy[i];

                    // 범위 벗어나면 탈출!!
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        return curJihun.time + 1;
                    }

                    // 벽, 이전 불, 이전 지훈 위치 체크 => 중복 제거
                    if (map[nx][ny] == '#' || map[nx][ny] == 'F' || map[nx][ny] == 'J') {
                        continue;
                    }

                    // Queue에 추가
                    jihun.add(new Node(nx, ny, curJihun.time + 1));
                    map[nx][ny] = 'J';
                }
            }
        }

        return -1; // 탈출 불가능!!
    }
}
