package study_231213.problemset;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int start = 0, len = Integer.MAX_VALUE; // 시작 위치, 길이

        HashSet<String> gemKind = new HashSet<>(Arrays.asList(gems)); // 보석 종류
        HashMap<String, Integer> map = new HashMap<>(); // 보석 체크

        for (int end = 0; end < gems.length; end++) {
            // 있는 경우 + 1, 없는 경우 1
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

            // 맨 앞의 보석과 같은 보석이 들어올 경우
            while (map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }

            // 보석 종류 개수와 같은 경우, 최솟값 갱신
            if (map.size() == gemKind.size() && len > (end - start)) {
                len = end - start; // 길이 갱신
                answer[0] = start + 1; // 시작 위치
                answer[1] = end + 1; // 종료 위치
            }
        }

        return answer;
    }
}