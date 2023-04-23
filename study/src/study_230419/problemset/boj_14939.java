package study_230419.problemset;

import java.io.*;
import java.util.Arrays;

public class boj_14939 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 전구 방향 정보
        int[] dx = { 1, -1, 0, 0, 0 };
        int[] dy = { 0, 0, 1, -1, 0 };

        int ans = Integer.MAX_VALUE;
        boolean[][] bulb = new boolean[10][10];
        boolean[][] copy = new boolean[10][10];

        // 값 저장
        for (int i = 0; i < 10; i++) {
            String str = br.readLine();
            for (int j = 0; j < 10; j++)
                bulb[i][j] = str.charAt(j) == '#' ? false : true;
        }

        // 비트마스킹으로 첫 번째 줄 전구를 누를지 말지 체크 0 ~ 1023
        for (int curBit = 0; curBit < (1 << 10); curBit++) {
            int cnt = 0;
            for (int i = 0; i < 10; i++)
                copy[i] = Arrays.copyOf(bulb[i], 10);

            // i값에 대해 첫 번째 줄 전구 누르기
            for (int j = 0; j < 10; j++) {
                if ((curBit & (1 << j)) == 0) // 0인 경우 스킵
                    continue;

                for (int k = 0; k < 5; k++) {
                    int nx = dx[k];
                    int ny = j + dy[k];

                    // 범위 체크
                    if (nx < 0 || nx >= 10 || ny < 0 || ny >= 10)
                        continue;

                    copy[nx][ny] = !copy[nx][ny]; // 전구 반전
                }

                cnt++; // 누른 횟수 증가
            }

            // 두 번째줄 부터 마지막 줄까지 실행 (위의 전구가 켜진 경우만 누름)
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!copy[i - 1][j]) // 위의 전구가 꺼져있는 경우 스킵
                        continue;

                    for (int k = 0; k < 5; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // 범위 체크
                        if (nx < 0 || nx >= 10 || ny < 0 || ny >= 10)
                            continue;

                        copy[nx][ny] = !copy[nx][ny]; // 전구 반전
                    }

                    cnt++; // 누른 횟수 증가
                }
            }

            // 마지막 줄에 스위치가 켜져있으면 X
            boolean check = false;
            for (int i = 0; i < 10; i++) {
                if (copy[9][i])
                    check = true;
            }

            if (check) // 마지막 줄에 스위치가 켜져있는 경우, 정답 갱신 X
                continue;

            ans = Integer.min(ans, cnt); // 정답 갱신
        }

        // 정답 출력
        if (ans == Integer.MAX_VALUE)
            ans = -1;

        sb.append(ans);
        System.out.println(sb);
    }
}
