package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_14400 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 초기화
        int n = Integer.parseInt(br.readLine().trim());
        int[] x = new int[n];
        int[] y = new int[n];

        // 값 저장
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(stk.nextToken());
            y[i] = Integer.parseInt(stk.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(x); 
        Arrays.sort(y);

        // 중간값 찾기
        int midX = x[n / 2];
        int midY = y[n / 2];

        // 중간값을 기준으로 거리 더하기
        long sum = 0;
        for (int i = 0; i < n; i++)
            sum += Math.abs(midX - x[i]) + Math.abs(midY - y[i]);

        sb.append(sum);
        System.out.println(sb);
    }
}