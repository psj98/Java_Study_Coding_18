package study_230419.problemset;

import java.io.*;

public class boj_2138 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int onCnt = 1, offCnt = 0, ansCnt = -1;
        int n = Integer.parseInt(br.readLine());
        boolean[] firstOn = new boolean[n];
        boolean[] firstOff = new boolean[n];
        boolean[] ans = new boolean[n];

        String curBulb = br.readLine(); // 현재 전구
        String ansBulb = br.readLine(); // 정답 전구

        // 값 저장
        for (int i = 0; i < n; i++) {
            firstOn[i] = curBulb.charAt(i) == '0' ? false : true;
            firstOff[i] = curBulb.charAt(i) == '0' ? false : true;
            ans[i] = ansBulb.charAt(i) == '0' ? false : true;
        }

        // 첫 번째 스위치 누르기
        firstOn[0] = !firstOn[0];
        firstOn[1] = !firstOn[1];

        for (int i = 1; i < n; i++) {
            // 이전 값이 다른 경우만 스위치 누르기
            if (firstOn[i - 1] != ans[i - 1]) {
                firstOn[i - 1] = !firstOn[i - 1]; // 변경
                firstOn[i] = !firstOn[i];

                if (i != n - 1)
                    firstOn[i + 1] = !firstOn[i + 1];

                onCnt++;
            }

            if (firstOff[i - 1] != ans[i - 1]) {
                firstOff[i - 1] = !firstOff[i - 1]; // 변경
                firstOff[i] = !firstOff[i];

                if (i != n - 1)
                    firstOff[i + 1] = !firstOff[i + 1];

                offCnt++;
            }

        }

        // 마지막이 같으면 정답
        if (firstOn[n - 1] == ans[n - 1])
            ansCnt = onCnt;

        if (firstOff[n - 1] == ans[n - 1])
            ansCnt = offCnt;

        // 정답 출력
        sb.append(ansCnt);
        System.out.println(sb);
    }
}
