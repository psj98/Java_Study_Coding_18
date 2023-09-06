package study_230830.problemset;

import java.io.*;
import java.util.*;

public class boj_17140 {
    static int r, c, k;
    static int[][] map = new int[3][3]; // 초기 배열 3*3

    static class Number implements Comparable<Number> {
        int num, cnt; // 숫자, 등장 횟수

        Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number o) {
            if (this.cnt == o.cnt) // 등장 횟수가 같으면, 숫자로 오름차순 정렬
                return this.num - o.num;
            return this.cnt - o.cnt; // 등장 횟수가 적은 것부터 정렬
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        r = Integer.parseInt(stk.nextToken()) - 1;
        c = Integer.parseInt(stk.nextToken()) - 1;
        k = Integer.parseInt(stk.nextToken());

        // 맵 정보
        for (int i = 0; i < 3; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        int time = 0; // 초기 시간
        while (true) {
            // 값이 맞으면 종료
            if (r < map.length && c < map[0].length && map[r][c] == k) {
                sb.append(time);
                break;
            }

            // 100초를 넘어가면 종료
            if (time > 100) {
                sb.append(-1);
                break;
            }

            int n = map.length;
            int m = map[0].length;

            // 연산 수행
            if (n >= m) { // 행 길이 >= 열 길이 => R 연산
                rFunction();
            } else { // // 행 길이 < 열 길이 => C 연산
                cFunction();
            }

            time++; // 시간 증가
        }

        // 정답 출력
        System.out.println(sb);
    }

    // R 연산
    static void rFunction() {
        PriorityQueue<Number> pq = new PriorityQueue<>();
        int[][] checkMap = new int[201][201]; // 최대 200개까지 있음
        int len = 0;

        for (int i = 0; i < map.length; i++) {
            int[] numCnt = new int[101]; // 숫자 개수 체크
            for (int j = 0; j < map[0].length; j++) {
                numCnt[map[i][j]]++;
            }

            // pq에 저장
            for (int j = 1; j <= 100; j++) {
                if (numCnt[j] == 0) // 0개면 스킵
                    continue;

                pq.add(new Number(j, numCnt[j]));
            }

            len = Math.max(len, pq.size() * 2); // 배열 최대 길이 갱신

            // 배열에 [숫자, 등장횟수] 저장
            int idx = 0;
            while (!pq.isEmpty()) {
                Number cur = pq.poll();
                checkMap[i][idx++] = cur.num;
                checkMap[i][idx++] = cur.cnt;
            }
        }

        // map에 복사
        len = len > 100 ? 100 : len;
        map = new int[map.length][len];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.copyOf(checkMap[i], len);
        }
    }

    // C 연산
    static void cFunction() {
        PriorityQueue<Number> pq = new PriorityQueue<>();
        int[][] checkMap = new int[201][201];
        int len = 0;

        for (int i = 0; i < map[0].length; i++) {
            int[] numCnt = new int[101];
            for (int j = 0; j < map.length; j++) {
                numCnt[map[j][i]]++;
            }

            // pq에 저장
            for (int j = 1; j <= 100; j++) {
                if (numCnt[j] == 0)
                    continue;

                pq.add(new Number(j, numCnt[j]));
            }

            len = Math.max(len, pq.size() * 2);

            int idx = 0;
            while (!pq.isEmpty()) {
                Number cur = pq.poll();
                checkMap[idx++][i] = cur.num;
                checkMap[idx++][i] = cur.cnt;
            }
        }

        len = len > 100 ? 100 : len;
        map = new int[len][map[0].length];
        for (int i = 0; i < len; i++) {
            map[i] = Arrays.copyOf(checkMap[i], map[0].length);
        }
    }
}
