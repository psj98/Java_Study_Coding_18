package study_231101.problemset;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];

        // 웅덩이 제외
        for (int[] puddle : puddles) {
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        map[0][0] = 1; // 초기값

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 웅덩이인 경우, 0으로 변경
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }

                // 세로 값 => 위의 값을 더함
                if (i != 0) {
                    map[i][j] += map[i - 1][j] % 1000000007;
                }

                // 가로 값 => 왼쪽의 값을 더함
                if (j != 0) {
                    map[i][j] += map[i][j - 1] % 1000000007;
                }
            }
        }

        // 정답 출력
        return map[n - 1][m - 1] % 1000000007;
    }
}