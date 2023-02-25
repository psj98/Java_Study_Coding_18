package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1167 {
    static int V, maxNode, maxDist = Integer.MIN_VALUE;
    static ArrayList<Node>[] tree;
    static boolean[] visited;

    // 정점, 비용 클래스
    static class Node {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 초기화
        V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++)
            tree[i] = new ArrayList<>();

        // 각 정점 별로 연결된 정점 + 비용 저장
        for (int i = 1; i <= V; i++) {
            stk = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(stk.nextToken());

            while (true) {
                int node = Integer.parseInt(stk.nextToken());
                if (node == -1)
                    break;
                int cost = Integer.parseInt(stk.nextToken());

                tree[vertex].add(new Node(node, cost));
            }
        }

        dfs(1, 0); // 1번 정점에서 가장 비용이 많이 나오는 정점 탐색
        visited = new boolean[V + 1];
        dfs(maxNode, 0); // 가장 비용이 많이 나오는 정점에서 끝까지 비용 탐색

        sb.append(maxDist);
        System.out.println(sb);
    }

    static void dfs(int node, int cost) {
        if (maxDist < cost) {
            maxDist = cost;
            maxNode = node;
        }

        visited[node] = true;

        for (int i = 0; i < tree[node].size(); i++) {
            int nextNode = tree[node].get(i).vertex;
            int curCost = tree[node].get(i).cost;

            if (!visited[nextNode]) {
                dfs(nextNode, cost + curCost);
            }
        }
    }
}
