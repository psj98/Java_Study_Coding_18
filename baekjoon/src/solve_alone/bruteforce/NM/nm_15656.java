package bruteforce.NM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nm_15656 {
    private static int n, m;
    private static Integer[] value;
    private static Integer[] num;
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int cnt) {
        if (cnt == m) {
            for (int i : value)
                sb.append(i).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            value[cnt] = num[i];
            dfs(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());

        value = new Integer[m];
        num = new Integer[n];

        for (int i = 0; i < n; i++)
            num[i] = Integer.parseInt(stk.nextToken());

        Arrays.sort(num);

        dfs(0);

        System.out.println(sb);
    }
}
