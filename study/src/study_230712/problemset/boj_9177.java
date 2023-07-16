package study_230712.problemset;

import java.io.*;
import java.util.*;

public class boj_9177 {
    static char[] word1, word2, word3; // 단어, 단어2, 단어 모음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            word1 = stk.nextToken().toCharArray();
            word2 = stk.nextToken().toCharArray();
            word3 = stk.nextToken().toCharArray();

            // 정답 출력
            sb.append("Data set ").append(i).append(": ").append(bfs() ? "yes" : "no").append("\n");
        }

        System.out.println(sb);
    }

    // 단어 탐색
    static boolean bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[word1.length + 1][word2.length + 1];

        queue.add(new int[] { 0, 0 }); // 시작 위치 (단어 1 위치, 단어 2 위치)
        visited[0][0] = true; // 방문 체크

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 단어 3과 길이가 같은 경우, 단어 섞기 가능
            if (cur[0] + cur[1] == word3.length) {
                return true;
            }

            // 단어 1 체크
            if (cur[0] < word1.length && word3[cur[0] + cur[1]] == word1[cur[0]] && !visited[cur[0] + 1][cur[1]]) {
                visited[cur[0] + 1][cur[1]] = true;
                queue.add(new int[] { cur[0] + 1, cur[1] });
            }

            // 단어 2 체크
            if (cur[1] < word2.length && word3[cur[0] + cur[1]] == word2[cur[1]] && !visited[cur[0]][cur[1] + 1]) {
                visited[cur[0]][cur[1] + 1] = true;
                queue.add(new int[] { cur[0], cur[1] + 1 });
            }
        }

        return false;
    }
}