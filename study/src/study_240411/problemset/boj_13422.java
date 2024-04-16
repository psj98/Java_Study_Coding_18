package study_240411.problemset;

import java.io.*;
import java.util.*;

public class boj_13422 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine()); // testcase

        for (int t = 0; t < tc; t++) {
            stk = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(stk.nextToken()); // 마을에 있는 집 개수
            int m = Integer.parseInt(stk.nextToken()); // 도둑이 돈을 훔칠 연속된 집 개수
            int k = Integer.parseInt(stk.nextToken()); // 최소 돈의 양

            int[] house = new int[n + m - 1]; // 집 배열

            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                house[i] = Integer.parseInt(stk.nextToken());
            }

            // 한 바퀴 순환할 수 있기 때문에 뒤에 집 추가
            for (int i = n; i < n + m - 1; i++) {
                house[i] = house[i - n];
            }

            int sum = 0; // 현재까지의 합
            for (int i = 0; i < m - 1; i++) {
                sum += house[i];
            }

            int cnt = 0; // 가능한 개수
            for (int i = m - 1; i < n + m - 1; i++) {
                sum += house[i];

                // k보다 작은 경우, 가능
                if (sum < k) {
                    cnt++;
                }

                sum -= house[i - m + 1];
            }

            // n == m 인 경우, 같은 집을 선택하는 경우가 생김
            if (n == m) {
                cnt /= m;
            }

            // 정답 저장
            sb.append(cnt).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
