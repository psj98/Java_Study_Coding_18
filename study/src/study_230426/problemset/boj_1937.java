package study_230426.problemset;

import java.io.*;
import java.util.*;

public class boj_1937 {
    static int n;
    static int[][] map, dp;
    static int[] dx = { 1, -1, 0, 0 }; // x 방향 정보
    static int[] dy = { 0, 0, 1, -1 }; // y 방향 정보

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 대나무 숲의 크기

        // 초기화
        map = new int[n][n];
        dp = new int[n][n];

        // 대나무 양 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ans = Math.max(ans, dfs(i, j));

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != 0) // 값이 있는 경우, 해당 값을 return
            return dp[x][y];

        dp[x][y] = 1; // 현재 위치는 1 (하나의 대나무를 먹음)

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            // 이전 값보다 작은 경우 갈 수 없음
            if (map[x][y] >= map[nx][ny])
                continue;

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1); // 최댓값 갱신
        }

        return dp[x][y];
    }
}
