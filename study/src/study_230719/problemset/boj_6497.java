package study_230719.problemset;

import java.io.*;
import java.util.*;

public class boj_6497 {
    static int[] parents;
    static PriorityQueue<Node> nodes;

    static class Node implements Comparable<Node> {
        int x, y, weight;

        Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        while (true) {
            stk = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(stk.nextToken());
            int n = Integer.parseInt(stk.nextToken());

            // 기저 조건
            if (m == 0 && n == 0) {
                break;
            }

            makeSet(m); // 배열, PriorityQueue 초기화

            // 집 정보 입력 (from, to, weight)
            int cost = 0;
            for (int i = 0; i < n; i++) {
                stk = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(stk.nextToken());
                int to = Integer.parseInt(stk.nextToken());
                int weight = Integer.parseInt(stk.nextToken());

                cost += weight;
                nodes.add(new Node(from, to, weight));
            }

            // 크루스칼 알고리즘
            while (!nodes.isEmpty()) {
                Node cur = nodes.poll();

                if (findSet(cur.x) != findSet(cur.y)) {
                    cost -= cur.weight;
                    union(cur.x, cur.y);
                }
            }

            sb.append(cost).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 초기화
    static void makeSet(int num) {
        nodes = new PriorityQueue<>();
        parents = new int[num];

        for (int i = 0; i < num; i++) {
            parents[i] = i;
        }
    }

    // Union
    static void union(int x, int y) {
        x = findSet(x);
        y = findSet(y);

        if (x == y) {
            return;
        }

        parents[y] = x;
    }

    // Find
    static int findSet(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = findSet(parents[x]);
    }
}
