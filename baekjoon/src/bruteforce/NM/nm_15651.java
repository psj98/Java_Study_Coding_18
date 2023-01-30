package bruteforce.NM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class nm_15651 {
    private static int n, m;
    private static Integer[] value;
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int cnt) {
        if (cnt == m) {
            for (int i : value)
                sb.append(i).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            value[cnt] = i + 1;
            dfs(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        value = new Integer[m];

        dfs(0);

        System.out.println(sb);
    }
}
