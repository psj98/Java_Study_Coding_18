package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2112 {
    static int d, w, k, ans;
    static int[][] map;
    static int[][] copy;

    public static boolean check() {
        int pass = 0;

        for (int i = 0; i < w; i++) {
            int idx = 0;

            while (idx <= d - k) {
                int sum = 0;
                for (int j = idx; j < idx + k; j++)
                    sum += map[j][i];

                if (sum == 0 || sum == k) {
                    pass++;
                    break;
                }

                idx++;
            }
        }

        if (pass == w)
            return true;
        else
            return false;
    }

    public static void drug(int line, int change) {
        if (change >= ans)
            return;

        if (line == d) {
            if (check())
                ans = Math.min(ans, change);
            return;
        }

        drug(line + 1, change);

        // A
        for (int i = 0; i < w; i++)
            map[line][i] = 0;
        drug(line + 1, change + 1);

        // B
        for (int i = 0; i < w; i++)
            map[line][i] = 1;
        drug(line + 1, change + 1);

        for (int i = 0; i < w; i++)
            map[line][i] = copy[line][i];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            stk = new StringTokenizer(br.readLine());

            d = Integer.parseInt(stk.nextToken());
            w = Integer.parseInt(stk.nextToken());
            k = Integer.parseInt(stk.nextToken());

            // 초기화
            ans = Integer.MAX_VALUE;
            map = new int[d][w];
            copy = new int[d][w];

            // 값 저장
            for (int i = 0; i < d; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++)
                    map[i][j] = copy[i][j] = Integer.parseInt(stk.nextToken());
            }

            drug(0, 0);

            System.out.println("#" + tc + " " + ans);
        }
    }

}
