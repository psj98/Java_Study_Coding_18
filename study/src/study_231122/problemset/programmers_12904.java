package study_231122.problemset;

class Solution {
    boolean[] visited;
    int answer = 0;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length]; // 방문 체크 배열 초기화

        dfs(0, begin, target, words); // DFS

        return answer; // 정답 반환
    }

    public void dfs(int cnt, String begin, String target, String[] words) {
        // target과 같은 경우, 값 갱신
        if (begin.equals(target)) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            // 방문 체크
            if (visited[i]) {
                continue;
            }

            // 같은 알파벳 체크
            int same = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) != words[i].charAt(j)) {
                    continue;
                }

                same++;
            }

            // 2개 이상 다른 경우, 스킵
            if (same != begin.length() - 1) {
                continue;
            }

            // DFS
            visited[i] = true;
            dfs(cnt + 1, words[i], target, words);
            visited[i] = false;
        }
    }
}