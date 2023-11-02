package study_231025.problemset;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] sum = new int[n + 1][m + 1]; // 누적합 배열

        // 각 점에 대해 값 저장
        for (int[] curSkill : skill) {
            // type에 대해 degree 계산
            int degree = curSkill[0] == 1 ? curSkill[5] * -1 : curSkill[5];

            sum[curSkill[1]][curSkill[2]] += degree;
            sum[curSkill[1]][curSkill[4] + 1] -= degree;
            sum[curSkill[3] + 1][curSkill[2]] -= degree;
            sum[curSkill[3] + 1][curSkill[4] + 1] += degree;
        }

        // 좌우 누적합
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        // 상하 누적합
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        // 정답 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + sum[i][j] <= 0) {
                    continue;
                }

                answer++;
            }
        }

        return answer;
    }
}