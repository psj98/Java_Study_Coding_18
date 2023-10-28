package study_231018.problemset;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[computers.length]; // 방문 배열 체크

        for (int i = 0; i < computers.length; i++) {
            // 방문 여부 체크
            if (visited[i]) {
                continue;
            }

            dfs(i, visited, computers); // 방문을 안한 노드에 대해 DFS 수행
            answer++; // 정답 증가
        }

        return answer;
    }

    // DFS
    public void dfs(int cur, boolean[] visited, int[][] computers) {
        visited[cur] = true; // 방문 체크

        for (int i = 0; i < computers.length; i++) {
            if (visited[i]) { // 방문 여부 체크
                continue;
            }

            // 연결 여부 체크
            if (computers[cur][i] == 0) {
                continue;
            }

            dfs(i, visited, computers); // DFS
        }
    }
}