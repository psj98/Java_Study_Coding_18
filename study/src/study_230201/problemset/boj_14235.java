package study_230201.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_14235 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 큐로 선물 가치가 큰 순서대로 저장

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");

            // 선물 주기
            if (str[0].equals("0")) {
                if (pq.size() == 0) // 선물 없는 경우
                    sb.append("-1\n");
                else { // 선물 있는 경우, 가장 앞에서 줌
                    sb.append(pq.peek() + "\n");
                    pq.remove();
                }
            }

            // 거점지
            else {
                for (int j = 1; j <= Integer.parseInt(str[0]); j++) {
                    pq.add(Integer.parseInt(str[j])); // 선물 추가
                }
            }
        }

        System.out.println(sb);
    }
}
