package study_230621.problemset;

import java.io.*;
import java.util.*;

public class boj_21315 {
    static int n;
    static int[] ans;
    static int[] card;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        ans = new int[n]; // 정답 카드 배열
        card = new int[n]; // 초기 카드 배열

        // 정답 카드 배열 초기화
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            ans[i] = Integer.parseInt(stk.nextToken());

        // k값의 최댓값 찾기
        int two = 2, idx = 1;
        while (two * 2 <= n) {
            two *= 2;
            idx++;
        }

        for (int i = 1; i <= idx; i++) {
            for (int j = 1; j <= idx; j++) {
                for (int k = 0; k < n; k++) {
                    card[k] = k + 1;
                }

                // 카드 섞기
                setRange(i);
                setRange(j);

                // 같은지 판별
                boolean check = false;
                for (int k = 0; k < n; k++) {
                    if (card[k] == ans[k])
                        continue;

                    check = true;
                    break;
                }

                if (check)
                    continue;

                // 정답 출력
                sb.append(i).append(" ").append(j);
                System.out.println(sb);
                return;
            }
        }

    }

    // 범위 설정 - 이전에 올린 개수 안에서 2^(k-i+1)개 만큼 올림
    static void setRange(int k) {
        int preCnt = n; // 이전에 올린 개수
        for (int i = 1; i <= k + 1; i++) {
            int cardCnt = (int) Math.pow(2, k - i + 1); // 올릴 카드 개수
            mix(cardCnt, preCnt); // 카드 섞기
            preCnt = cardCnt; // 이전에 올린 개수 갱신
        }
    }

    // 카드 섞기
    static void mix(int cardCnt, int preCnt) {
        int idx = 0;
        int[] mixCard = new int[n];

        // 해당 범위의 값을 mixCard에 저장
        for (int i = preCnt - cardCnt; i < preCnt; i++) {
            mixCard[idx++] = card[i];
            card[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            if (card[i] != -1) // mixCard에 저장된 값이 아닌 경우
                mixCard[idx++] = card[i]; // mixCard 뒤에 저장
            card[i] = mixCard[i];
        }
    }
}
