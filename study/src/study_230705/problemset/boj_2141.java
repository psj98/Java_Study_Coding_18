package study_230705.problemset;

import java.io.*;
import java.util.*;

public class boj_2141 {
    static class Town implements Comparable<Town> {
        long pos, num; // 위치, 사람 수

        Town(long pos, long num) {
            this.pos = pos;
            this.num = num;
        }

        @Override
        public int compareTo(Town o) {
            return (int) (this.pos - o.pos); // 위치에 따른 오름차순 정렬
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        ArrayList<Town> towns = new ArrayList<>();
        long sum = 0, val = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            long pos = Long.parseLong(stk.nextToken());
            long num = Long.parseLong(stk.nextToken());

            towns.add(new Town(pos, num)); // 마을 추가
            sum += num; // 사람 수 합
        }

        Collections.sort(towns); // 오름차순 정렬

        // 중간값이 작아질 때가 최적의 위치
        for (int i = 0; i < n; i++) {
            val += towns.get(i).num;

            long mid = (sum + 1) / 2; // 중간값
            if (mid <= val) {
                sb.append(towns.get(i).pos); // 우체국 최적 위치
                break;
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}
