package study_240307.problemset;

import java.io.*;
import java.util.*;

public class boj_1911 {

    // 웅덩이 클래스
    static class Pool implements Comparable<Pool> {
        int start, end;

        Pool(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pool o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stk.nextToken()); // 물웅덩이 개수
        int L = Integer.parseInt(stk.nextToken()); // 널빤지 크기

        Pool[] pools = new Pool[N]; // 웅덩이 배열

        // 웅덩이 정보
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken()) - 1;

            pools[i] = new Pool(start, end);
        }

        Arrays.sort(pools); // 오름차순 정렬

        // 널빤지 배치 및 개수 세기
        int idx = 0, cnt = 0;
        for (Pool pool : pools) {
            int start = pool.start;
            int end = pool.end;

            if (start > idx) {
                idx = start;
            }

            while (end >= idx) {
                idx += L;
                cnt++;
            }
        }

        // 정답 출력
        sb.append(cnt);
        System.out.println(sb);
    }
}
