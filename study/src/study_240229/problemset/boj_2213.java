package study_240229.problemset;

import java.io.*;
import java.util.*;

public class boj_2213 {
    static int n; // 정점 개수
    static int[] cost; // 비용 배열
    static int[][] dp; // DP 배열
    static ArrayList<Integer>[] list, tree; // 간선 정보, 1번 정점을 root로 하고 내려가면서 연결된 트리 정보
    static PriorityQueue<Integer> pq; // 최대 독립집합 크기에 해당하는 정점

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 정점 개수

        // 초기화
        cost = new int[n + 1];
        dp = new int[n + 1][2];

        list = new ArrayList[n + 1];
        tree = new ArrayList[n + 1];
        pq = new PriorityQueue<>();

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        stk = new StringTokenizer(br.readLine());

        // 정점 별 비용
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(stk.nextToken());
        }

        // 간선 연결
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            list[from].add(to);
            list[to].add(from);
        }

        makeTree(1, -1); // 1번 정점을 root로 하는 트리 생성

        // 1번 정점을 선택 O / X 에 대해 DP
        int rootO = treeDP(1, 1); // 선택 O
        int rootX = treeDP(1, 0); // 선택 X

        // 큰 값에 대해 정점을 찾음
        if (rootO > rootX) {
            sb.append(rootO).append("\n");
            findNode(1, 1);
        } else {
            sb.append(rootX).append("\n");
            findNode(1, 0);
        }

        // 정점 출력
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 1번 정점을 root로 하는 트리 생성
    static void makeTree(int cur, int next) {
        for (int child : list[cur]) {
            if (child == next) {
                continue;
            }

            tree[cur].add(child);
            makeTree(child, cur);
        }
    }

    // tree에서 DP 수행
    static int treeDP(int cur, int check) {
        int sum = 0;

        if (dp[cur][check] != 0) {
            return dp[cur][check];
        }

        if (check == 1) { // 현재 정점을 포함하는 경우
            sum += cost[cur];

            for (int next : tree[cur]) {
                sum += treeDP(next, 0);
            }
        } else { // 현재 정점을 포함하지 않은 경우
            for (int next : tree[cur]) {
                int nextO = treeDP(next, 1); // 인접 정점 포함
                int nextX = treeDP(next, 0); // 인접 정점 미포함

                sum += Math.max(nextO, nextX);
            }
        }

        return dp[cur][check] = sum;
    }

    // 최댓값에 포함되는 정점 찾기
    static void findNode(int cur, int check) {
        if (check == 1) { // 선택된 경우
            pq.add(cur);

            for (int next : tree[cur]) {
                findNode(next, 0); // 다음 정점은 선택하면 안됨
            }
        } else { // 선택되지 않은 경우
            for (int next : tree[cur]) {
                // 큰 값에 대해 수행
                if (dp[next][1] > dp[next][0]) {
                    findNode(next, 1);
                } else {
                    findNode(next, 0);
                }
            }
        }
    }
}
