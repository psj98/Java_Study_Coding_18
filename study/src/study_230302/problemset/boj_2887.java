package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2887 {
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static ArrayList<int[]> node = new ArrayList<>();
    static int[] parents;
    static int n;

    // 간선 클래스
    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost; // 비용 오름차순 정렬
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        int cost;

        n = Integer.parseInt(br.readLine()); // 정점 개수

        // 정점 저장
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            node.add(new int[] { x, y, z, i });
        }

        // x좌표 기준으로 정렬
        node.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // PriorityQueue에 간선 정보 저장
        for (int i = 1; i < n; i++) {
            cost = Math.abs(node.get(i - 1)[0] - node.get(i)[0]);
            pq.add(new Edge(node.get(i - 1)[3], node.get(i)[3], cost));
        }

        // y좌표 기준으로 정렬
        node.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // PriorityQueue에 간선 정보 저장
        for (int i = 1; i < n; i++) {
            cost = Math.abs(node.get(i - 1)[1] - node.get(i)[1]);
            pq.add(new Edge(node.get(i - 1)[3], node.get(i)[3], cost));
        }

        // z좌표 기준으로 정렬
        node.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // PriorityQueue에 간선 정보 저장
        for (int i = 1; i < n; i++) {
            cost = Math.abs(node.get(i - 1)[2] - node.get(i)[2]);
            pq.add(new Edge(node.get(i - 1)[3], node.get(i)[3], cost));
        }

        makeSet(); // parents 배열 초기화

        int cnt = 0, sum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            // union이 가능하면
            if (union(edge.start, edge.end)) {
                sum += edge.cost; // 비용 더하기
                cnt++; // 간선 개수 증가
            }

            // 간선 개수가 정점-1 이면 break
            if (cnt == n - 1)
                break;
        }

        // 정답 출력
        sb.append(sum);
        System.out.println(sb);
    }

    // parents 배열 초기화
    static void makeSet() {
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = i;
    }

    // 정점 찾기
    static int findSet(int num) {
        if (num == parents[num])
            return num;
        return parents[num] = findSet(parents[num]);
    }

    // 정점 합치기
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
