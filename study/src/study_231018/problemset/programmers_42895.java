package study_231018.problemset;

import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // 1개만 사용했을 경우, 체크
        if (number == N) {
            return 1;
        }

        List<Set<Integer>> numList = new ArrayList<>(); // 사용 개수 별로 나올 수 있는 값 체크

        // 초기화
        for (int i = 0; i <= 8; i++) {
            numList.add(new HashSet<>());
        }

        numList.get(1).add(N); // 1개만 사용했을 때 값 저장

        for (int i = 2; i <= 8; i++) {
            // 모든 경우 조합
            for (int j = 1; j <= i / 2; j++) {
                combine(numList.get(i), numList.get(i - j), numList.get(j));
                combine(numList.get(i), numList.get(j), numList.get(i - j));
            }

            // 연속된 숫자 생성
            String num = String.valueOf(N);
            String newNum = "";
            for (int j = 0; j < i; j++) {
                newNum += num;
            }

            numList.get(i).add(Integer.parseInt(newNum)); // 연속된 숫자 저장

            // 값이 있는 경우, i 반환
            for (int cur : numList.get(i)) {
                if (cur != number) {
                    continue;
                }

                return i;
            }
        }

        return -1; // 8개를 넘어가는 경우
    }

    // 모든 경우의 수 조합
    public void combine(Set<Integer> cur, Set<Integer> x, Set<Integer> y) {
        for (int i : x) {
            for (int j : y) {
                cur.add(i + j);
                cur.add(i - j);
                cur.add(i * j);

                if (j == 0) {
                    continue;
                }

                cur.add(i / j);
            }
        }
    }
}