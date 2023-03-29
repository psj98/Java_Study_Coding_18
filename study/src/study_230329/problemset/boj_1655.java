package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        PriorityQueue<Integer> right = new PriorityQueue<>(); // 오름차순

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (i % 2 == 0) // 짝수일 때는 left에 저장
                left.add(num);
            else // 홀수일 때는 right에 저장
                right.add(num);

            // 양쪽이 비어있지 않고 left의 최댓값이 right의 최솟값보다 큰 경우 -> 교환
            if (!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
                int temp = left.poll();
                left.add(right.poll());
                right.add(temp);
            }

            sb.append(left.peek()).append("\n"); // 정답 출력
        }

        System.out.println(sb);
    }
}
