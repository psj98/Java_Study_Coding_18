package study_240215.problemset;

import java.io.*;
import java.util.*;

public class boj_19644 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int L = Integer.parseInt(br.readLine()); // 좀비 개수

        stk = new StringTokenizer(br.readLine());

        int ml = Integer.parseInt(stk.nextToken()); // 유효 사거리
        int mk = Integer.parseInt(stk.nextToken()); // 살상력

        int mine = Integer.parseInt(br.readLine()); // 지뢰 개수

        long[] zombie = new long[L + 1]; // 좀비 체력
        long[] sum = new long[L + 1]; // 누적합 배열

        // 좀비 체 정보
        for (int i = 1; i <= L; i++) {
            zombie[i] = Integer.parseInt(br.readLine());
        }

        boolean ans = true; // 가능 여부
        for (int i = 1; i <= L; i++) {
            long cur = sum[i - 1] - sum[Math.max(0, i - ml)] + mk; // 현재 데미지

            if (zombie[i] <= cur) { // 살상 가능한 경우
                sum[i] = sum[i - 1] + mk; // 누적합 갱신
            } else { // 폭탄 사용해야 하는 경우
                // 폭탄이 없는 경우, 불가능
                if (mine <= 0) {
                    ans = false;
                    break;
                }

                mine--; // 폭탄 개수 감소
                sum[i] = sum[i - 1]; // 이전 누적합 그대로 가져옴
            }
        }

        // 정답 출력
        sb.append(ans ? "YES" : "NO");
        System.out.println(sb);
    }
}
