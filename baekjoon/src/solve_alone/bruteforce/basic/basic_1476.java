package bruteforce.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class basic_1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int cnt = 1;
        int E = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        while (!((cnt - E) % 15 == 0 && (cnt - S) % 28 == 0 && (cnt - M) % 19 == 0)) {
            cnt++;
        }

        System.out.println(cnt);
    }
}
