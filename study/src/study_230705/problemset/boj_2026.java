package study_230705.problemset;

import java.io.*;
import java.util.*;

public class boj_2026 {
    static int n, k;
    static boolean[][] map; // 친구 정보
    static int[] friends; // 본인의 친구 수
    static boolean[] visited; // 방문 여부

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력 및 초기화
        k = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        int f = Integer.parseInt(stk.nextToken());

        map = new boolean[n + 1][n + 1];
        friends = new int[n + 1];
        visited = new boolean[n + 1];

        // 친구 정보 및 친구 수 갱신
        for (int i = 0; i < f; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            map[from][to] = map[to][from] = true; // 친구 정보
            friends[from]++; // 친구 수 갱신
            friends[to]++;
        }

        // 백트래킹
        for (int i = 1; i <= n; i++) {
            // 본인 제외하고 k-1 보다 작으면 k명만큼 친구를 만들 수 없음
            if (friends[i] < k - 1)
                continue;

            // 백트래킹으로 친구 찾기
            visited[i] = true;
            dfs(1, i);
            visited[i] = false;
        }

        // k명 친구를 못 만드는 경우, -1 출력
        sb.append(-1);
        System.out.println(sb);
    }

    // 백트래킹
    static void dfs(int cnt, int cur) {
        // k명 친구를 만든 경우
        if (cnt == k) {
            // 정답 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (visited[i]) // true인 경우만 출력
                    sb.append(i).append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        for (int i = cur + 1; i <= n; i++) {
            // 현재 친구인지 체크 || 이전에 true인 사람과 친구인지 체크
            if (!map[cur][i] || !checkFriends(i))
                continue;

            visited[i] = true;
            dfs(cnt + 1, i);
            visited[i] = false;
        }
    }

    // 이전에 true인 사람과 친구인지 체크
    static boolean checkFriends(int cur) {
        for (int i = 1; i <= n; i++) {
            if (visited[i] && !map[cur][i]) {
                return false;
            }
        }

        return true;
    }
}
