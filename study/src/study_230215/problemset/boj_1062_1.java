package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1062_1 {
    static boolean[] visited = new boolean[26]; // 방문 배열
    static int n, k, ans = -1;
    static String[] word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        char[] acint = { 'a', 'c', 'i', 'n', 't' }; // 기본으로 가지고 있어야 하는 알파벳

        // 방문 배열 초기화
        for (int i = 0; i < 5; i++)
            visited[acint[i] - 97] = true;

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken()) - 5; // 기본 알파벳 빼줌

        // 단어
        word = new String[n];
        for (int i = 0; i < n; i++)
            word[i] = br.readLine();

        // 5개 (a, c, i, n, t)보다 작으면 단어를 만들 수 없음
        if (k < 0) {
            sb.append(0);
            System.out.println(sb);
            return;
        }

        // 백트래킹
        search(0, 0);

        sb.append(ans);
        System.out.println(sb);
    }

    // 조합 찾기
    public static void search(int cnt, int idx) {
        if (cnt == k) {
            int sum = 0;

            for (int i = 0; i < n; i++) {
                boolean check = false;
                for (int j = 0; j < word[i].length(); j++) {
                    // 방문되지 않은 값을 가지고 있는 경우
                    if (!visited[word[i].charAt(j) - 97]) {
                        check = true;
                        break;
                    }
                }

                if (check)
                    continue;
                sum++;
            }

            ans = Math.max(ans, sum);

            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                search(cnt + 1, i + 1);
                visited[i] = false;
            }
        }
    }

}
