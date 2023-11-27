package study_231108.problemset;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int left = 0, right = 10000 * n, time = 0, work = 0;

        // 이분 탐색 => 시간 기준
        while (left <= right) {
            int mid = (left + right) / 2; // 중간값 찾기
            int cnt = check(cores, mid); // 시간 체크

            if (cnt >= n) {
                right = mid - 1;
                time = mid;
                work = cnt;
            } else {
                left = mid + 1;
            }
        }

        work -= n; // 작업량까지 줄여야 하는 개수

        for (int i = cores.length - 1; i >= 0; i--) {
            // core로 나눠지지 않은 경우 => 작업 완료 불가
            if (time % cores[i] != 0) {
                continue;
            }

            // 모두 작업한 경우, 종료
            if (work == 0) {
                answer = i + 1;
                break;
            }

            work--; // 작업량 감소
        }

        return answer;
    }

    // 해당 시간동안(mid)에 작업 가능한 core 개수 세기
    public int check(int[] cores, int mid) {
        int cnt = cores.length;

        for (int i = 0; i < cores.length; i++) {
            cnt += (mid / cores[i]);
        }

        return cnt;
    }
}