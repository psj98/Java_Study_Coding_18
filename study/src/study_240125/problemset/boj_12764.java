package study_240125.problemset;

import java.io.*;
import java.util.*;

public class boj_12764 {
    static class Time implements Comparable<Time> {
        int start, end; // 시작 시간, 종료 시간

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            return this.start - o.start;
        }
    }

    static class EndTime implements Comparable<EndTime> {
        int end, num; // 종료 시간, 사용중인 컴퓨터 번호

        EndTime(int end, int num) {
            this.end = end;
            this.num = num;
        }

        @Override
        public int compareTo(EndTime o) {
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Time> time = new PriorityQueue<>();
        PriorityQueue<EndTime> endTime = new PriorityQueue<>();
        PriorityQueue<Integer> computer = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        int comCnt = 0;
        int[] comArr = new int[100001];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());

            time.add(new Time(start, end)); // 시간 저 (시작, 종료)
        }

        for (int i = 0; i < n; i++) {
            // 남은 사람이 있는 경우 & 시작 시간이 더 느린 경우
            while (!endTime.isEmpty() && time.peek().start > endTime.peek().end) {
                computer.add(endTime.poll().num); // 컴퓨터 추가
            }

            if (computer.isEmpty()) { // 컴퓨터가 없는 경우
                endTime.add(new EndTime(time.poll().end, comCnt));
                comArr[comCnt++]++;
            } else { // 컴퓨터가 있는 경우
                int curComputer = computer.poll();

                endTime.add(new EndTime(time.poll().end, curComputer)); // 가장 앞에 있는 컴퓨터에 배정
                comArr[curComputer]++;
            }
        }

        // 정답 출력
        sb.append(comCnt).append("\n");
        for (int i = 0; i < comCnt; i++) {
            sb.append(comArr[i]).append(" ");
        }
        System.out.println(sb);
    }
}
