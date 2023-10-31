package study_231025.problemset;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) { // 맨 왼쪽 체크
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == i) { // 맨 오른쪽 체크
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else { // 중간 체크
                    triangle[i][j] += Math.max(triangle[i - 1][j], triangle[i - 1][j - 1]);
                }

                answer = Math.max(answer, triangle[i][j]); // 최댓값 찾기
            }
        }

        return answer;
    }
}