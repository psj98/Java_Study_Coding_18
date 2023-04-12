package study_230412.problemset;

import java.util.*;
import java.io.*;

public class boj_21608 {
    static int n;
    static int[][] map;
    static int[][] emptySeat;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static HashMap<Integer, Student> list;

    static class Student {
        int x, y; // 좌표
        int[] friends; // 좋아하는 친구 배열

        Student(int x, int y, int[] friends) {
            this.x = x;
            this.y = y;
            this.friends = friends;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int ans = 0;
        int[] score = { 0, 1, 10, 100, 1000 };

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        emptySeat = new int[n][n];
        list = new HashMap<>();

        findEmptySeat(); // 빈자리 찾기 -> 배치될 때마다 주변 인접한 곳 --

        for (int i = 0; i < n * n; i++) {
            stk = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stk.nextToken());

            // 친구 번호 저장
            int[] friends = new int[4];
            for (int j = 0; j < 4; j++)
                friends[j] = Integer.parseInt(stk.nextToken());

            if (i == 0) {
                map[1][1] = num;
                list.put(num, new Student(1, 1, friends));

                for (int j = 0; j < 4; j++)
                    emptySeat[1 + dx[j]][1 + dy[j]]--;

                continue;
            }

            findSeat(num, friends);
        }

        for (int i = 1; i <= n * n; i++) {
            int cnt = 0;
            Student student = list.get(i);

            for (int friend : student.friends) {
                int dist = Math.abs(student.x - list.get(friend).x)
                        + Math.abs(student.y - list.get(friend).y);
                if (dist == 1)
                    cnt++;
            }

            ans += score[cnt];
        }

        sb.append(ans);
        System.out.println(sb);
    }

    // 빈 자리 찾기
    static void findEmptySeat() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 4;

                if (i == 0 || i == n - 1)
                    cnt--;
                if (j == 0 || j == n - 1)
                    cnt--;

                emptySeat[i][j] = cnt;
            }
        }
    }

    // 배치할 자리 찾기
    static void findSeat(int num, int[] friends) {
        int[][] nextToFriend = new int[n][n];

        // 배치된 학생들에서 인접한 곳 개수 세기
        for (int friend : friends) {
            if (!list.containsKey(friend))
                continue;

            Student student = list.get(friend); // 좋아하는 학생 정보
            for (int i = 0; i < 4; i++) {
                int nx = student.x + dx[i];
                int ny = student.y + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                // 비어 있는 곳 체크
                if (map[nx][ny] != 0)
                    continue;

                nextToFriend[nx][ny]++; // 인접한 곳 증가
            }
        }

        int x = -1, y = -1, maxCnt = -1, maxEmptySeat = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) // 좋아하는 학생이 배치가 안되어있을 수 있음 -> 배치된 곳 제외
                    continue;

                // 1. 비어있는 칸 -> 좋아하는 학생이 가장 많이 인접한 칸 구하기
                if (maxCnt < nextToFriend[i][j]) {
                    x = i;
                    y = j;
                    maxCnt = nextToFriend[i][j];
                    maxEmptySeat = emptySeat[i][j];
                } else if (maxCnt == nextToFriend[i][j]) { // 2. 1번에서 비어있는 칸이 많은 칸
                    if (maxEmptySeat < emptySeat[i][j]) {
                        x = i;
                        y = j;
                        maxEmptySeat = emptySeat[i][j];
                    }

                    // 3. 2번에서 행 번호 작은 칸 -> 열 번호 작은 칸 -> 행, 열 작은 순서부터 탐색하므로 체크 X
                }
            }
        }

        map[x][y] = num;
        list.put(num, new Student(x, y, friends));

        // 배치 했을 때, 인접한 곳 개수 줄이기
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            // 비어 있는 곳 체크
            if (map[nx][ny] != 0)
                continue;

            emptySeat[nx][ny]--;
        }
    }
}
