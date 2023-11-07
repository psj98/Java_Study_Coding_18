package study_231101.problemset;

import java.util.*;

class Solution {
    String[] userId;
    String[] bannedId;
    HashSet<HashSet<String>> ans = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;

        backtracking(new HashSet<>(), 0); // 조합 찾기

        return ans.size(); // 크기 반환
    }

    // 백트래킹
    public void backtracking(HashSet<String> set, int cnt) {
        // 제재 아이디만큼 찾은 경우
        if (cnt == bannedId.length) {
            ans.add(set); // 정답 set에 저장
            return;
        }

        for (int i = 0; i < userId.length; i++) {
            // 이미 set에 포함된 경우 제외
            if (set.contains(userId[i])) {
                continue;
            }

            // 사용자 아이디 & 제재 아이디 비교
            if (!check(userId[i], bannedId[cnt])) {
                continue;
            }

            set.add(userId[i]); // set에 저장
            backtracking(new HashSet<>(set), cnt + 1);
            set.remove(userId[i]); // 저장된 값 제거
        }
    }

    // 사용자 아이디 & 제재 아이디 비교
    public boolean check(String user, String ban) {
        // 길이가 다르면 불가능
        if (user.length() != ban.length()) {
            return false;
        }

        for (int i = 0; i < user.length(); i++) {
            // * 인 경우 스킵
            if (ban.charAt(i) == '*') {
                continue;
            }

            // 같은 값인 경우 스킵
            if (ban.charAt(i) == user.charAt(i)) {
                continue;
            }

            return false; // 제재 아이디 불가능
        }

        // 제재 아이디 가능
        return true;
    }
}