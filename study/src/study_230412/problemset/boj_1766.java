package study_230412.problemset;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class boj_1766 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // 초기화
        int[] indegree = new int[n + 1]; // 순위 (화살표를 받는 개수)
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            list[from].add(to); // from -> to 체크

            indegree[to]++; // 화살표 받는 쪽 개수 증가
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++)
            if (indegree[i] == 0) // 화살표를 받지 않는 노드 저장
                pq.add(i);

        while (!pq.isEmpty()) {
            int cur = pq.poll(); // 현재 노드

            sb.append(cur).append(" "); // 정답 출력

            // 현재 노드에서 갈 수 있는 곳 -> 개수 감소
            for (int next : list[cur]) {
                if (--indegree[next] == 0) // 0인 경우, pq에 저장
                    pq.add(next);
            }
        }

        System.out.println(sb);
    }
}
