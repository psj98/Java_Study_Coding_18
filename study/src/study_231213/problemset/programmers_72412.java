package study_231213.problemset;

import java.util.*;

class Solution {
    HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 개발 언어 : cpp, java, python
        // 지원 직군 : backend, frontend
        // 경력 구분 : junior, senior
        // 소울 푸드 : chicken, pizza
        // 코딩테스트 점수 : 1 ~ 100000

        // 모든 조합 저장
        for (String i : info) {
            dfs(0, "", i.split(" "));
        }

        // 점수 오름차순 정렬
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        // 쿼리 판단
        for (int i = 0; i < query.length; i++) {
            // and, 공백 제외 => StringTokenizer는 char 단위이므로 불가능
            String[] token = query[i].replaceAll(" and", "").split(" ");
            StringBuilder sb = new StringBuilder();

            // StringBuilder에 추가
            for (int j = 0; j < 4; j++) {
                sb.append(token[j]);
            }

            int score = Integer.parseInt(token[4]); // 점수

            answer[i] = checkQuery(sb.toString(), score); // 개수 찾기
        }

        return answer;
    }

    // 모든 조합 찾기
    public void dfs(int cnt, String str, String[] infos) {
        // 점수 제외 나머지 조합
        if (cnt == 4) {
            int score = Integer.parseInt(infos[cnt]); // 점수

            if (map.containsKey(str)) { // 이미 있는 경우, 점수만 추가
                map.get(str).add(score);
            } else { // 없는 경우, map에 저장
                ArrayList<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(str, list);
            }

            return;
        }

        dfs(cnt + 1, new StringBuilder(str).append(infos[cnt]).toString(), infos); // 포함 O
        dfs(cnt + 1, new StringBuilder(str).append("-").toString(), infos); // 포함 X
    }

    // 쿼리 체크하기
    public int checkQuery(String query, int score) {
        // key가 없는 경우, 0 반환
        if (!map.containsKey(query)) {
            return 0;
        }

        ArrayList<Integer> list = map.get(query); // 점수 리스트
        int left = 0, right = list.size() - 1; // 이분 탐색을 위한 변수

        // 이분 탐색
        while (left <= right) {
            int mid = (left + right) / 2; // 중간 위치

            if (list.get(mid) < score) { // 점수보다 작은 경우
                left = mid + 1;
            } else { // 점수보다 큰 경우
                right = mid - 1;
            }
        }

        return list.size() - left; // 점수보다 낮은 개수를 빼고 반환
    }
}
