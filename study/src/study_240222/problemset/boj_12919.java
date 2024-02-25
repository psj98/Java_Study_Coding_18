package study_240222.problemset;

import java.io.*;

public class boj_12919 {
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();
        String T = br.readLine();

        dfs(S, T);

        // 정답 출력
        sb.append(check ? 1 : 0);
        System.out.println(sb);
    }

    static void dfs(String S, String T) {
        if (check) {
            return;
        }

        if (S.length() == T.length()) {
            // 같은 경우, true
            if (S.equals(T)) {
                check = true;
                return;
            }

            return;
        }

        // A를 추가했을 경우, A를 빼고 DFS
        if (T.charAt(T.length() - 1) == 'A') {
            dfs(S, T.substring(0, T.length() - 1));
        }

        // B를 추가하고 뒤집었을 경우, B를 빼고 뒤집어서 DFS
        if (T.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(T.substring(1));
            dfs(S, sb.reverse().toString());
        }
    }
}
