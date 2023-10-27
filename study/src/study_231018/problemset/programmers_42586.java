package study_231018.problemset;

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>(); // 정답을 저장할 리스트

        // 진도율 계산 => 100에서 뺀 후, 속도로 나눔
        for (int i = 0; i < progresses.length; i++) {
            progresses[i] = 100 - progresses[i];

            if (progresses[i] % speeds[i] == 0) {
                progresses[i] /= speeds[i];
            } else {
                progresses[i] = progresses[i] / speeds[i] + 1;
            }
        }

        // 진도율 개수를 세면서 정답 리스트에 저장
        int cnt = 1, cur = progresses[0];
        for (int i = 1; i < progresses.length; i++) {
            if (cur < progresses[i]) {
                list.add(cnt);
                cur = progresses[i];
                cnt = 1;

                continue;
            }

            cnt++;
        }

        list.add(cnt);

        // 정답 배열에 저장
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}