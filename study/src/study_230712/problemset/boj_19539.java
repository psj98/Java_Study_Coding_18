package study_230712.problemset;

import java.io.*;
import java.util.*;

public class boj_19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        /**
         * 합 구하기 => 3의 배수
         * 홀수 개수 => 홀수는 1로 더해서 만들 수 있음 => 
         */

        int n = Integer.parseInt(br.readLine());

        int num, sum = 0, odd = 0; // 합, 홀수 개수

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num = Integer.parseInt(stk.nextToken());
            sum += num;

            if (num % 2 == 1) {
                odd++;
            }
        }

        // 정답 출력
        if (sum % 3 == 0 && odd <= sum / 3) {
            sb.append("YES");
        } else {
            sb.append("NO");
        }

        System.out.println(sb);
    }
}