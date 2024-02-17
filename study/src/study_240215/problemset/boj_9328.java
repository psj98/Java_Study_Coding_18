package study_240215.problemset;

import java.io.*;
import java.util.*;

public class boj_9328 {
    static int n, m;
    static char[][] map;
    static HashSet<Character> keys; // 소유한 키
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            stk = new StringTokenizer(br.readLine());

            // 맵 크기
            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());

            // 초기화
            map = new char[n + 2][m + 2];
            keys = new HashSet<>();

            // 빈 공간으로 채움
            for (int i = 0; i < n + 2; i++) {
                Arrays.fill(map[i], '.');
            }

            // 맵 정보
            for (int i = 1; i <= n; i++) {
                String str = br.readLine();

                for (int j = 1; j <= m; j++) {
                    map[i][j] = str.charAt(j - 1);
                }
            }

            // 소유한 열쇠
            String keyStr = br.readLine();

            // 열쇠가 있는 경우
            if (keyStr.charAt(0) != '0') {
                for (int i = 0; i < keyStr.length(); i++) {
                    keys.add(keyStr.charAt(i));
                }
            }

            sb.append(bfs()).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // BFS
    @SuppressWarnings("unchecked")
    static int bfs() {
        int cnt = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        ArrayList<int[]>[] gates = new ArrayList[26];
        boolean[][] visited = new boolean[n + 2][m + 2];

        queue.add(new int[] { 0, 0 });
        visited[0][0] = true;

        // 초기화
        for (int i = 0; i < 26; i++) {
            gates[i] = new ArrayList<>();
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2) {
                    continue;
                }

                // 벽 체크
                if (map[nx][ny] == '*') {
                    continue;
                }

                // 방문 체크
                if (visited[nx][ny]) {
                    continue;
                }

                // 문서인 경우
                if (map[nx][ny] == '$') {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny });
                    cnt++;
                    continue;
                }

                // 빈 공간인 경우
                if (map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny });
                    continue;
                }

                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') { // 열쇠인 경우
                    keys.add(map[nx][ny]); // 열쇠 획득
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny });

                    // 열지 못한 문 열기 => 해당 위치 Queue에 추가
                    for (int j = 0; j < 26; j++) {
                        // 열지 못한 문 개수 체크
                        if (gates[j].size() == 0) {
                            continue;
                        }

                        // 보유 키 체크
                        if (!keys.contains((char) (j + 'a'))) {
                            continue;
                        }

                        // 해당 위치를 Queue에 추가
                        for (int[] gate : gates[j]) {
                            map[gate[0]][gate[1]] = '.';
                            visited[gate[0]][gate[1]] = true;
                            queue.add(new int[] { gate[0], gate[1] });
                        }
                    }
                } else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') { // 문인 경우
                    if (keys.contains((char) (map[nx][ny] - 'A' + 'a'))) { // 열쇠가 있는 경우
                        map[nx][ny] = '.';
                        visited[nx][ny] = true;
                        queue.add(new int[] { nx, ny });
                    } else { // 열쇠가 없는 경우
                        gates[map[nx][ny] - 'A'].add(new int[] { nx, ny }); // 문을 List에 추가
                    }
                }
            }
        }

        return cnt;
    }
}
