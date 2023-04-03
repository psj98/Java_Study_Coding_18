package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2098 {
    static int n;
    static final int INF = 16000000;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], INF * 2);
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        sb.append(dfs(0, 1)); // 0번 노드, Bit(0...01)
        System.out.println(sb);
    }

    static int dfs(int node, int visited) {
        if (visited == (1 << n) - 1) { // 모든 노드 방문
            if (map[node][0] == 0) // 0이면 못감
                return INF; // Cycle이 안됨
            return map[node][0];
        }

        if (dp[node][visited] != INF * 2) // 이미 계산되어 있는 경우
            return dp[node][visited];

        for (int i = 0; i < n; i++) {
            if (map[node][i] == 0 || (visited & (1 << i)) != 0) // 못가는 경우, 방문한 경우
                continue;

            // 방문한 적이 없는 곳
            int next = visited | (1 << i);
            dp[node][visited] = Math.min(dp[node][visited], dfs(i, next) + map[node][i]);
        }

        return dp[node][visited];
    }
}
