package study_240104.problemset;

import java.io.*;
import java.util.*;

public class boj_15685 {
    static boolean[][] map = new boolean[101][101];
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 드래곤 커브 개수

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int g = Integer.parseInt(stk.nextToken());

            dragon(x, y, d, g); // 좌표 체크하기
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 네 꼭지점이 모두 true가 아닌 경우
                if (!map[i][j] || !map[i][j + 1] || !map[i + 1][j] || !map[i + 1][j + 1]) {
                    continue;
                }

                answer++;
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 좌표 체크하기
    static void dragon(int y, int x, int d, int g) {
        ArrayList<Integer> degree = new ArrayList<>(); // 방향 저장 리스트
        degree.add(d);

        for (int i = 0; i < g; i++) {
            for (int j = degree.size() - 1; j >= 0; j--) {
                int next = (degree.get(j) + 1) % 4; // 다음에 갈 방향
                degree.add(next);
            }
        }

        map[x][y] = true; // 현재 위치

        // 다음에 갈 방향 true로 변경
        for (int cur : degree) {
            x += dx[cur];
            y += dy[cur];

            map[x][y] = true;
        }
    }
}
