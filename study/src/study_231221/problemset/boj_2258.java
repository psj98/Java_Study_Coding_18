package study_231221.problemset;

import java.io.*;
import java.util.*;

public class boj_2258 {

    // 고기 클래스
    static class Meat implements Comparable<Meat> {
        int weight, cost; // 무게, 비용

        Meat(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        // 정렬
        @Override
        public int compareTo(Meat o) {
            if (this.cost == o.cost) // 무게 내림차순
                return o.weight - this.weight;
            return this.cost - o.cost; // 비용 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Meat> pq = new PriorityQueue<>();
        int n = Integer.parseInt(stk.nextToken()); // 덩어리 개수
        int m = Integer.parseInt(stk.nextToken()); // 필요한 고기의 양

        int sum = 0; // 가능한지 여부를 판단하기 위한 무게 합
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            pq.add(new Meat(weight, cost));

            sum += weight;
        }

        // 불가능한 경우, -1 출력
        if (sum < m) {
            sb.append(-1);
            System.out.println(sb);
            return;
        }

        int answer = Integer.MAX_VALUE; // 정답
        int totalWeight = 0, totalCost = 0, beforeCost = 0;

        while (!pq.isEmpty()) {
            Meat meat = pq.poll();

            totalWeight += meat.weight; // 무게 추가

            if (beforeCost == meat.cost) { // 이전 비용 == 현재 비용
                totalCost += meat.cost; // 구매해야 하기 때문에 가격 더함
            } else { // 이전 비용 != 현재 비용
                totalCost = beforeCost = meat.cost; // 이전 비용을 현재 비용으로 갱신
            }

            // 무게 합이 m을 넘어가는지 체크
            if (totalWeight < m) {
                continue;
            }

            answer = Math.min(answer, totalCost); // 최솟값 갱신
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
