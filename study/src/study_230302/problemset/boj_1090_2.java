package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1090_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> x = new ArrayList<>(); // x좌표
        ArrayList<Integer> y = new ArrayList<>(); // y좌표
        ArrayList<int[]> pos = new ArrayList<>(); // (x, y) 좌표

        // x, y, (x, y) 좌표 저장
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int numX = Integer.parseInt(stk.nextToken());
            int numY = Integer.parseInt(stk.nextToken());

            x.add(numX);
            y.add(numY);
            pos.add(new int[] { numX, numY });
        }

        // 정답 배열 초기화
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        // 모든 가능한 좌표에 대해 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> minVal = new ArrayList<>(); // 거리 리스트

                // 거리 계산
                for (int[] cur : pos)
                    minVal.add(Math.abs(cur[0] - x.get(i)) + Math.abs(cur[1] - y.get(j)));

                Collections.sort(minVal); // 오름차순 정렬

                // 앞에서부터 개수에 맞게 최솟값 갱신
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += minVal.get(k);
                    ans[k] = Math.min(ans[k], sum);
                }
            }
        }

        // 정답 출력
        for (int val : ans)
            sb.append(val).append(" ");
        System.out.println(sb);
    }
}
