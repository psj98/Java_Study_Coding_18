package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_1090_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        Set<Integer> x = new HashSet<>(); // x좌표
        Set<Integer> y = new HashSet<>(); // y좌표
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

        Iterator<Integer> itX = x.iterator();
        Iterator<Integer> itY = y.iterator();

        // 정답 배열 초기화
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        // 모든 가능한 좌표에 대해 탐색
        while (itX.hasNext()) {
            int curX = itX.next();
            itY = y.iterator();
            while (itY.hasNext()) {
                ArrayList<Integer> minVal = new ArrayList<>(); // 거리 리스트
                int curY = itY.next();

                // 거리 계산
                for (int[] cur : pos)
                    minVal.add(Math.abs(cur[0] - curX) + Math.abs(cur[1] - curY));

                Collections.sort(minVal);

                // 앞에서부터 개수에 맞게 최솟값 갱신
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += minVal.get(i);
                    ans[i] = Math.min(ans[i], sum);
                }
            }
        }

        // 정답 출력
        for (int val : ans)
            sb.append(val).append(" ");
        System.out.println(sb);
    }
}
