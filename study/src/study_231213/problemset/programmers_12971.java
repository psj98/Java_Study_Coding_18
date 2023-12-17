package study_231213.problemset;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length; // 스티커 개수

        // 길이가 1인 경우, 해당 스티커가 정답
        if (len == 1) {
            return sticker[0];
        }

        int[] dp = new int[len]; // 배열 초기화

        // 첫 번째 스티커 뗐을 경우 => 마지막 스티커 제외
        dp[0] = dp[1] = sticker[0];
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }

        answer = dp[len - 2];

        // 첫 번째 스티커 안뗐을 경우 => 마지막 스티커 포함
        dp[0] = 0;
        dp[1] = sticker[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }

        answer = Math.max(answer, dp[len - 1]); // 최댓값 갱신

        return answer;
    }
}