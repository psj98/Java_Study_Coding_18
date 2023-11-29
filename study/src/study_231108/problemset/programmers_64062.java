package study_231108.problemset;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0; // 정답
        int left = 1, right = 200_000_000; // 사람 수 체크

        // 이분 탐색
        while (left <= right) {
            int mid = (left + right) / 2; // 중간값

            // 건널 수 있는지 체크
            if (check(stones, k, mid)) { // 건널 수 있는 경우
                left = mid + 1; // mid + 1
                answer = Math.max(answer, mid); // 최댓값 갱신
            } else {
                right = mid - 1; // mid - 1
            }
        }

        return answer;
    }

    // 건널 수 있는지 체크
    public boolean check(int[] stones, int k, int mid) {
        int skip = 0; // 한 번에 스킵할 수 있는 개수

        for (int stone : stones) {
            if (stone < mid) { // 못 건너는 경우
                skip++;
            } else { // 건널 수 있는 경우
                skip = 0;
            }

            // 한 번에 스킵할 수 있는 개수와 같아지면 false
            if (skip == k) {
                return false;
            }
        }

        return true;
    }
}