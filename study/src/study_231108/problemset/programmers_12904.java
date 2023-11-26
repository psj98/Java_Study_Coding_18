package study_231108.problemset;

class Solution {
    public int solution(String s) {
        // 길이가 긴 것부터 체크
        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; j + i <= s.length(); j++) {
                // 팰린드롬 체크
                if (check(s, j, j + i - 1)) {
                    return i;
                }
            }
        }

        return 0;
    }

    // 팰린드롬 체크
    boolean check(String s, int start, int end) {
        // 양쪽에서부터 가운데로 오면서 체크
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }

        return true;
    }
}