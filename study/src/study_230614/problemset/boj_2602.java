package study_230614.problemset;

import java.io.*;

public class boj_2602 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String word = br.readLine(); // 두루마리 문자열
        String devilRock = br.readLine(); // 악마의 돌다리
        String angelRock = br.readLine(); // 천사의 돌다리

        int wordSize = word.length(); // 두루마리 문자열 길이
        int rockSize = angelRock.length(); // 돌다리 길이

        int[][][] dp = new int[2][wordSize][rockSize]; // [시작 위치][두루마리 문자열 위치][현재 돌 위치];

        // 돌다리 시작값이 두루마리 시작값과 같은 경우, 1로 저장
        if (word.charAt(0) == angelRock.charAt(0))
            dp[0][0][0] = 1;
        if (word.charAt(0) == devilRock.charAt(0))
            dp[1][0][0] = 1;

        for (int i = 1; i < rockSize; i++) {
            // 두루마리 시작값 가져오기
            dp[0][0][i] += dp[0][0][i - 1];
            dp[1][0][i] += dp[1][0][i - 1];

            // 두루마리 시작값과 같은 경우
            if (angelRock.charAt(i) == word.charAt(0))
                dp[0][0][i]++;
            if (devilRock.charAt(i) == word.charAt(0))
                dp[1][0][i]++;

            // 두루마리 시작값 이후부터 반복
            for (int j = 1; j < wordSize; j++) {
                // 같은 경우, [현재 돌다리][두루마리 위치][이전 돌다리 위치] + [다른 돌다리][이전 두루마리 위치][이전 돌다리 위치]
                // 다른 경우, [현재 돌다리][두루마리 위치][이전 돌다리 위치]
                dp[0][j][i] = angelRock.charAt(i) == word.charAt(j) ? dp[0][j][i - 1] + dp[1][j - 1][i - 1]
                        : dp[0][j][i - 1];
                dp[1][j][i] = devilRock.charAt(i) == word.charAt(j) ? dp[1][j][i - 1] + dp[0][j - 1][i - 1]
                        : dp[1][j][i - 1];
            }
        }

        // 정답 출력 - 돌다리의 마지막 위치에서 두루마리의 마지막 값
        sb.append(dp[0][wordSize - 1][rockSize - 1] + dp[1][wordSize - 1][rockSize - 1]);
        System.out.println(sb);
    }
}
