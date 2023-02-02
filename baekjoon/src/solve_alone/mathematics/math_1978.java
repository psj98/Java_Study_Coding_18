package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class math_1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int cnt = 0;

        stk = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(stk.nextToken());
            boolean check = true;

            if (m == 1)
                continue;

            for (int j = 2; j <= Math.sqrt(m); j++) {
                if (m % j == 0){
                    check=false;
                    break;
                }
            }

            if (check)
                cnt++;
        }

        sb.append(cnt);

        System.out.println(sb);
    }
}
