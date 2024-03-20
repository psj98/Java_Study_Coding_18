package study_240314.problemset;

import java.io.*;
import java.util.*;

public class boj_23843 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken()); // 전자기기 개수
        int m = Integer.parseInt(stk.nextToken()); // 콘센트 개수

        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder()); // 전자기기 내림차순 정렬
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 시간 오름차순 정렬

        // 전자기기 정보
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq1.add(Integer.parseInt(stk.nextToken()));
        }

        // 전자기기가 콘센트보다 많은 경우
        if (n > m) {
            for (int i = 0; i < m; i++) {
                pq2.add(pq1.poll());
            }

            // 충전이 끝난 전자기기에 그 다음 전자기기를 더함
            while (!pq1.isEmpty()) {
                int cur = pq2.poll() + pq1.poll();
                pq2.add(cur);
            }

            // 최댓값이 최소 충전 시간
            int ans = 0;
            while (!pq2.isEmpty()) {
                ans = pq2.poll();
            }

            sb.append(ans);
        } else { // 전자기기가 콘센트보다 적은 경우
            sb.append(pq1.peek());
        }

        // 정답 출력
        System.out.println(sb);
    }
}
