package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_16964 {
    static ArrayList<Integer>[] list;
    static int[] ans, sortArr;
    static boolean[] visited;
    static int cnt = 1;
    static StringBuilder sb = new StringBuilder();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int V = Integer.parseInt(br.readLine()); // 정점 개수

        // 초기화
        ans = new int[V + 1];
        sortArr = new int[V + 1];
        visited = new boolean[V + 1];
        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++)
            list[i] = new ArrayList<>();

        // 방향 정보 저장 (양방향)
        for (int i = 0; i < V - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            list[from].add(to);
            list[to].add(from);
        }

        // 결과값 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            ans[i] = Integer.parseInt(stk.nextToken());
            sortArr[ans[i]] = i; // 탐색 순서를 찾기 위한 배열
        }

        // sort - sortArr 순서에 따라 정렬 (같은 레벨에 대해 순서 정렬)
        for (int i = 1; i <= V; i++) {
            Collections.sort(list[i], new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return sortArr[o1] - sortArr[o2];
                }
            });
        }

        dfs(1); // 시작 노드 1

        sb.append(1);
        System.out.println(sb);
    }

    static void dfs(int node) {
        if (visited[node]) { // 방문한 경우 return
            return;
        }

        if (ans[cnt] != node) { // 정답 dfs와 다른 경우
            sb.append(0);
            System.out.println(sb);
            System.exit(0);
        }

        cnt++; // 정답 dfs와 맞은 경우, 다음 정답 node를 탐색하기 위해 cnt++
        visited[node] = true; // 방문 체크

        // 현재 노드에서 갈 수 있는 곳 탐색
        for (int next : list[node]) {
            dfs(next);
        }
    }
}
