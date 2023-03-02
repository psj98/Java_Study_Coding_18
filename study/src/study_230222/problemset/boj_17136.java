package study_230222.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17136 {
    static int[][] map = new int[10][10];
    static int[] paper = new int[6];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 맵 정보 저장
        for (int i = 0; i < 10; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        find(0, 0, 0); // 색종이 찾기

        // 정답 출력 -> max 값이면 -1
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        sb.append(ans);
        System.out.println(ans);
    }

    // 색종이 찾기
    static void find(int x, int y, int cnt) {
        // 맨 마지막에 도착하면 최솟값 저장 후, return
        if (x == 9 && y == 10) {
            ans = Math.min(ans, cnt);
            return;
        }

        // y가 10이면 x값 증가, y=0
        if (y == 10) {
            x++;
            y = 0;
        }

        // cnt가 ans보다 크면 뒤에를 탐색할 필요 없음
        if (ans < cnt)
            return;

        if (map[x][y] == 1) { // 1인 경우
            // 색종이 붙일 크기 확인
            for (int i = 5; i > 0; i--) {
                if (can(x, y, i) && paper[i] < 5) { // 색종이 붙일 수 있으면
                    paste(x, y, i, 2); // 색종이 붙임
                    paper[i]++; // 색종이 개수 증가
                    find(x, y + 1, cnt + 1);
                    paste(x, y, i, 1); // 원래대로
                    paper[i]--; // 색종이 개수 감소
                }
            }
        } else { // 1이 아닌 경우 다음 좌표로 이동
            find(x, y + 1, cnt);
        }
    }

    // 색종이 붙일 수 있는지 여부
    static boolean can(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= 10 || j >= 10)
                    return false;
                if (map[i][j] == 0 || map[i][j] == 2)
                    return false;
            }
        }

        return true;
    }

    // 색종이 붙이기
    static void paste(int x, int y, int size, int num) {
        for (int i = x; i < x + size; i++)
            for (int j = y; j < y + size; j++)
                map[i][j] = num;
    }
}
