package study_240111.problemset;

import java.io.*;
import java.util.*;

public class boj_1744 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 개수

        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder()); // 양수
        PriorityQueue<Integer> minus = new PriorityQueue<>(); // 0 & 음수

        // PriorityQueue에 저장
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 0) {
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        // 정답 출력
        sb.append(getSum(minus) + getSum(plus));
        System.out.println(sb);
    }

    // 합 구하기
    public static int getSum(PriorityQueue<Integer> pq) {
        int sum = 0; // 합

        while (!pq.isEmpty()) {
            int cur = pq.poll(); // 첫 번째 값

            // 값이 1개인 경우, 더함
            if (pq.isEmpty()) {
                sum += cur;
                break;
            }

            int next = pq.poll(); // 두 번째 값

            if (cur == 1 || next == 1) { // 1인 경우, 더하는게 더 큼
                sum += cur + next;
            } else { // 1이 아닌 경우, 곱해서 더함
                sum += cur * next;
            }
        }

        return sum;
    }
}
