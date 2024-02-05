package study_240201.problemset;

import java.io.*;
import java.util.*;

public class boj_13975 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // 테스트케이스

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            long sum = 0; // 총합

            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                pq.add(Long.parseLong(stk.nextToken()));
            }

            // 2개 이상인 경우에 최솟값 2개를 poll 하여 더함 => 해당 값을 sum에 추가
            while (pq.size() > 1) {
                long next = pq.poll() + pq.poll(); // 최솟값 2개 추출 후, 더함

                sum += next; // sum에 더함

                pq.add(next);
            }

            sb.append(sum).append("\n"); // 정답 저장
        }

        // 정답 출력
        System.out.println(sb);
    }
}
