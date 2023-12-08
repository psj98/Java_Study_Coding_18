package study_231129.problemset;

import java.util.*;

class Solution {
    public Queue<String> pq = new PriorityQueue<>();
    public boolean[] visited;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        dfs(tickets, "ICN", 0, "ICN"); // DFS

        String[] answer = pq.peek().split(" "); // 정답

        return answer;
    }

    // DFS
    public void dfs(String[][] tickets, String cur, int cnt, String next) {
        // 기저 조 => 모두 탐색
        if (cnt == tickets.length) {
            pq.add(next);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            // 이미 방문한 경우
            if (visited[i]) {
                continue;
            }

            // 현재 있는 곳이 다른 경우
            if (!cur.equals(tickets[i][0])) {
                continue;
            }

            visited[i] = true;
            dfs(tickets, tickets[i][1], cnt + 1, next + " " + tickets[i][1]);
            visited[i] = false;
        }
    }
}