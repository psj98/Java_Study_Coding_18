package study_230614.problemset;

import java.io.*;
import java.util.*;

public class boj_17182 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 모든 행성을 탐사하는데 걸리는 최소 시간을 계산
        // 다시 시작 행성으로 돌아올 필요 X
        // 방문한 행성 중복해서 가능

        n = Integer.parseInt(stk.nextToken());
        int start = Integer.parseInt(stk.nextToken());

        // 맵 정보
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // 플로이드-워셜 => 각 정점 간 최단거리 찾기
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        dfs(1, 0, start, 1 << start); // 백트래킹

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 백트래킹 (비트마스킹)
    static void dfs(int cnt, int sum, int cur, int flag) {
        if (cnt == n) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) != 0)
                continue;

            dfs(cnt + 1, sum + map[cur][i], i, flag | (1 << i));
        }
    }
}
