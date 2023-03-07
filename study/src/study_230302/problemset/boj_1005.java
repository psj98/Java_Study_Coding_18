package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1005 {
    static int[] value, indegree; // 정점에서의 건설 시간, 해당 정점이 화살표를 받는 개수
    static ArrayList<Integer>[] map; // 간선 정보

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int k = Integer.parseInt(stk.nextToken());

            // 초기화
            indegree = new int[n + 1];
            value = new int[n + 1];
            map = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++)
                map[i] = new ArrayList<>();

            // 정점에서의 건설 시간 저장
            stk = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                value[i] = Integer.parseInt(stk.nextToken());

            // 간선 정보 저장 및 화살표 받는 개수 저장
            for (int i = 0; i < k; i++) {
                stk = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stk.nextToken());
                int end = Integer.parseInt(stk.nextToken());
                map[start].add(end);
                indegree[end]++;
            }

            int w = Integer.parseInt(br.readLine()); // 출력해야하는 정점

            sb.append(topology(n, w)).append("\n"); // 정답 출력
        }

        System.out.println(sb);
    }

    // 위상 정렬
    static int topology(int n, int end) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[n + 1]; // 정답 배열

        // 화살표를 받는 개수가 0인 정점을 queue에 저장
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                ans[i] = value[i]; // 해당 정점의 값을 value로 초기화
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : map[cur]) {
                if (--indegree[next] == 0) // 해당 정점이 화살표를 모두 받았으면 그 정점에서 다른 정점으로 출발 가능
                    queue.offer(next);
                ans[next] = Math.max(ans[next], value[next] + ans[cur]); // 최댓값 갱신
            }
        }

        return ans[end]; // 정답
    }
}
