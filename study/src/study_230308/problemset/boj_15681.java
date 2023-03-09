package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15681 {
    static int n;
    static ArrayList<Integer>[] node;
    static int[] dp;
    static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        int r = Integer.parseInt(stk.nextToken());
        int q = Integer.parseInt(stk.nextToken());

        // 초기화
        node = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            node[i] = new ArrayList<>();

        visited = new boolean[n + 1];
        dp = new int[n + 1];
        Arrays.fill(dp, 1); // 1로 초기화 (말단 노드 -> 1)

        // 정점 연결 정보 저장
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            node[start].add(end);
            node[end].add(start);
        }

        dfs(r); // dfs

        // 출력
        for (int i = 0; i < q; i++)
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");

        System.out.println(sb);
    }

    // 말단 노드까지 dfs
    static int dfs(int cur) {
        visited[cur] = true; // 현재 노드 방문

        // 다음에 갈 노드가 있을 경우
        for (int next : node[cur])
            if (!visited[next]) // 방문하지 않은 경우, 현재 dp에 dfs(next) 값을 더함
                dp[cur] += dfs(next);

        return dp[cur]; // 없을 경우, 현재 dp 값 return
    }
}
