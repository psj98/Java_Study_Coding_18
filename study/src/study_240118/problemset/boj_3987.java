package study_240118.problemset;

import java.io.*;
import java.util.*;

public class boj_3987 {
    static int n, m; // 맵 크기
    static char[][] map; // 맵 정보
    static boolean visited[][][]; // 방문 체크 배열 - 위치, 방향
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    static int[] left = { 1, 0, 3, 2 }; // '/' 일 때 변경해야 할 방향
    static int[] right = { 3, 2, 1, 0 }; // '\' 일 때 변경해야 할 방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        char[] dir = { 'U', 'R', 'D', 'L' }; // 가장 값이 큰 방 배열

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new char[n][m];

        // 맵 정보
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        stk = new StringTokenizer(br.readLine());

        // 시작 위치
        int pr = Integer.parseInt(stk.nextToken()) - 1;
        int pc = Integer.parseInt(stk.nextToken()) - 1;

        int ans = 0, d = -1; // 정답, 방향

        for (int i = 0; i < 4; i++) {
            int val = find(pr, pc, i); // 이동 횟수 세기

            if (val <= ans) {
                continue;
            }

            // 최댓값, 방향 갱신
            ans = val;
            d = i;
        }

        // 정답 출력
        sb.append(dir[d]).append("\n");

        if (ans == Integer.MAX_VALUE) {
            sb.append("Voyager");
        } else {
            sb.append(ans);
        }

        System.out.println(sb);
    }

    // 이동 횟수 세기
    static int find(int x, int y, int dir) {
        int cnt = 1; // 이동 횟수
        visited = new boolean[n][m][4]; // 방문 체크 배열 초기화

        visited[x][y][dir] = true; // 시작 위치 방문 체크

        while (true) {
            // 다음에 갈 방향
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                break;
            }

            // 방문 체크
            if (visited[nx][ny][dir]) {
                cnt = Integer.MAX_VALUE;
                break;
            }

            // 블랙홀
            if (map[nx][ny] == 'C') {
                break;
            }

            // 방향 변경
            if (map[nx][ny] == '/') {
                dir = left[dir];
            } else if (map[nx][ny] == '\\') {
                dir = right[dir];
            }

            x = nx;
            y = ny;
            cnt++; // 이동 횟수 증가
        }

        return cnt;
    }
}
