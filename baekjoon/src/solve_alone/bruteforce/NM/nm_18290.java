package bruteforce.NM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class nm_18290 {
    private static int n, m, k, maxNum = Integer.MIN_VALUE, sum = 0;
    private static Integer[][] arr;
    private static boolean[][] visit;
    private static int[] dx = { 1, -1, 0, 0 };
    private static int[] dy = { 0, 0, 1, -1 };

    public static void dfs(int cnt) {
        if (cnt == k) {
            maxNum = Math.max(maxNum, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    boolean check = false;
                    for (int a = 0; a < 4; a++) {
                        int nx = dx[a] + i;
                        int ny = dy[a] + j;

                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && visit[nx][ny]) {
                            check = true;
                            break;
                        }
                    }

                    if (!check) {
                        sum += arr[i][j];
                        visit[i][j] = true;
                        dfs(cnt + 1);
                        visit[i][j] = false;
                        sum -= arr[i][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        arr = new Integer[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                arr[i][j] = Integer.parseInt(stk.nextToken());
        }

        dfs(0);

        System.out.println(maxNum);
    }
}
