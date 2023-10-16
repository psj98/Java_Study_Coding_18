package study_231011.problemset;

import java.io.*;
import java.util.*;

public class boj_1091 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 초기화
        int[] p = new int[n];
        int[] s = new int[n];
        int[] card = new int[n]; // 초기 배열
        int[] temp = new int[n];
        int[] arr = new int[n]; // 배열이 초기값과 같은지 확인할 배열

        // 되어야 할 배열
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(stk.nextToken());
        }

        // 카드 이동 위치
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(stk.nextToken());
        }

        // 초기 배열 값 저장
        for (int i = 0; i < n; i++) {
            arr[i] = card[i] = i % 3;
        }

        int ans = 0; // 섞은 횟수
        while (true) {
            // 초기값과 배열이 같은 경우, -1
            if (ans != 0 && Arrays.equals(card, arr)) {
                ans = -1;
                break;
            }

            // p와 같아진 경우
            if (Arrays.equals(card, p)) {
                break;
            }

            // 카드 섞기
            for (int i = 0; i < n; i++) {
                temp[i] = card[s[i]];
            }

            for (int i = 0; i < n; i++) {
                card[i] = temp[i];
            }

            ans++;
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }
}