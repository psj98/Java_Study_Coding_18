package study_231011.problemset;

import java.io.*;
import java.util.*;

public class boj_13549 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 시작 지점
        int k = Integer.parseInt(stk.nextToken()); // 종료 지점

        boolean[] visited = new boolean[100001];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // 비용 오름차순 정렬
            }
        });

        pq.add(new int[] { n, 0 }); // 시작 지점, 비용

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 정답인 경우, 종료
            if (cur[0] == k) {
                sb.append(cur[1]);
                break;
            }

            visited[cur[0]] = true; // 방문 체크

            // 순간 이동
            if (cur[0] * 2 <= 100000 && !visited[cur[0] * 2]) {
                pq.add(new int[] { cur[0] * 2, cur[1] });
            }

            // 걷기
            if (cur[0] + 1 <= 100000 && !visited[cur[0] + 1]) {
                pq.add(new int[] { cur[0] + 1, cur[1] + 1 });
            }

            if (cur[0] - 1 >= 0 && !visited[cur[0] - 1]) {
                pq.add(new int[] { cur[0] - 1, cur[1] + 1 });
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}