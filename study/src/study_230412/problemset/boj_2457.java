package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_2457 {
    static class Flower implements Comparable<Flower> {
        int start, end; // 시작일, 종료일

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) // 종료일 내림차순
                return o.end - this.end;
            return this.start - o.start; // 시작일 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Flower[] flower = new Flower[n];

        // 꽃 저장
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(stk.nextToken()) * 100;
            int startD = Integer.parseInt(stk.nextToken());
            int endM = Integer.parseInt(stk.nextToken()) * 100;
            int endD = Integer.parseInt(stk.nextToken());

            flower[i] = new Flower(startM + startD, endM + endD);
        }

        Arrays.sort(flower); // 정렬

        int idx = 0, cnt = 0;
        int startDate = 301, endDate = 0; // 꽃이 피는, 지는 날짜
        while (startDate < 1201) {
            boolean check = false;
            int cur = idx;

            for (int i = cur; i < n; i++) {
                if (flower[i].start > startDate)
                    break;

                // 피는 날짜가 종료일에 가까워야 하고, 종료일이 최대한 오래걸리는 것 찾음
                if (flower[i].start <= startDate && flower[i].end > endDate) {
                    endDate = flower[i].end;
                    idx++;
                    check = true;
                }
            }

            if (check) { // 겹치는 것이 있으면 갱신
                startDate = endDate;
                cnt++;
            } else // 없으면 종료
                break;
        }

        // 정답 출력
        if (endDate >= 1201) // 1130에 지면 1130은 포함 X -> 1201 이상이어야 함
            sb.append(cnt);
        else
            sb.append(0);
        System.out.println(sb);
    }
}
