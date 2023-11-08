package study_231101.problemset;

import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        // Stack에 각각 값 저장
        Stack<Integer> pickup = new Stack<>();
        Stack<Integer> delivery = new Stack<>();

        for (int cur : pickups) {
            pickup.add(cur);
        }

        for (int cur : deliveries) {
            delivery.add(cur);
        }

        // 배달 / 수거 진행
        while (!(pickup.isEmpty() && delivery.isEmpty())) {
            // 0 스킵
            while (!delivery.isEmpty() && delivery.peek() == 0) {
                delivery.pop();
            }

            // 0 스킵
            while (!pickup.isEmpty() && pickup.peek() == 0) {
                pickup.pop();
            }

            answer += Math.max(pickup.size(), delivery.size()) * 2; // 거리 저장

            // 배달 진행
            int num = 0;
            while (!delivery.isEmpty() && num <= cap) {
                // 배달할 물건 개수 + 현재 배달할 수 있는 물건과 용량 비교
                if (delivery.peek() + num > cap) {
                    int cur = delivery.pop() - cap + num; // 일부분만 배달
                    delivery.add(cur);
                    break;
                }

                num += delivery.pop(); // 모두 배달 가능
            }

            // 수거 진행
            num = 0;
            while (!pickup.isEmpty() && num <= cap) {
                // 수거할 물건 개수 + 현재 수거할 수 있는 물건과 용량 비교
                if (pickup.peek() + num > cap) {
                    int cur = pickup.pop() - cap + num; // 일부분만 수거
                    pickup.add(cur);
                    break;
                }

                num += pickup.pop(); // 모두 수거 가능
            }
        }

        return answer;
    }
}