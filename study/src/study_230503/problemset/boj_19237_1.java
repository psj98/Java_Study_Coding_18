package study_230503.problemset;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class boj_19237_1 {
    static int n, m, k, ans = 0, deadSharkCnt = 0;
    static int[][] map;
    static Shark[] sharks; // 상어 정보
    static ArrayList<Smell>[][] smells; // 냄새 정보
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Shark {
        int x, y; // 상어 현재 위치
        int curDir; // 상어가 바라보는 방향
        int[][] dir; // 상어 방향 우선 순위 (위, 아래, 왼, 오)
        boolean dead;

        Shark(int x, int y, int curDir, int[][] dir, boolean dead) {
            this.x = x;
            this.y = y;
            this.curDir = curDir;
            this.dir = dir;
            this.dead = dead;
        }
    }

    static class Smell {
        int num, time; // 상어 번호, 냄새가 사라지기까지 남은 초

        Smell(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        map = new int[n][n];
        sharks = new Shark[m];
        smells = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                smells[i][j] = new ArrayList<Smell>();
        }

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(stk.nextToken()) - 1;
                map[i][j] = num;

                if (num == -1)
                    continue;

                sharks[num] = new Shark(i, j, 0, null, false); // 상어 초기 정보 저장
                smells[i][j].add(new Smell(num, 0)); // 냄새 초기 정보 저장
            }
        }

        // 상어가 현재 바라보는 방향
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            sharks[i].curDir = Integer.parseInt(stk.nextToken()) - 1;

        // 상어 방향 우선순위 저장
        for (int i = 0; i < m; i++) {
            int[][] dir = new int[4][4];
            for (int j = 0; j < 4; j++) {
                stk = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++)
                    dir[j][k] = Integer.parseInt(stk.nextToken()) - 1;
            }

            sharks[i].dir = dir;
        }

        // 상어 움직이기 + 냄새 남기기
        while (deadSharkCnt < m - 1 && ans <= 1000) {
            move();
            makeSmell();
            ans++;
        }

        // 정답 출력
        if (ans > 1000) // 1000번 초과한 경우
            sb.append(-1);
        else
            sb.append(ans);
        System.out.println(sb);
    }

    // 상어 방향 체크 및 이동
    static void move() {
        for (int i = 0; i < m; i++) {
            if (sharks[i].dead) // 상어가 죽은 경우
                continue;

            Shark curShark = sharks[i]; // 현재 상태

            // 빈 칸 체크
            boolean check = false;
            for (int j = 0; j < 4; j++) {
                int nx = curShark.x + dx[curShark.dir[curShark.curDir][j]]; // 상어가 가야할 곳
                int ny = curShark.y + dy[curShark.dir[curShark.curDir][j]];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) // 범위 체크
                    continue;

                if (smells[nx][ny].size() != 0) // 냄새가 있는 경우, 스킵
                    continue;

                // 빈 칸이 있으면 이동하기
                curShark.x = nx;
                curShark.y = ny;

                curShark.curDir = curShark.dir[curShark.curDir][j]; // 방향 갱신
                check = true; // 이동함

                break;
            }

            if (check)
                continue;

            // 자신의 냄새 칸 체크
            check = false;
            for (int j = 0; j < 4; j++) {
                int nx = curShark.x + dx[curShark.dir[curShark.curDir][j]]; // 상어가 가야할 곳
                int ny = curShark.y + dy[curShark.dir[curShark.curDir][j]];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) // 범위 체크
                    continue;

                if (smells[nx][ny].size() == 0) // 냄새가 없는 경우, 스킵
                    continue;

                for (Smell curSmell : smells[nx][ny]) {
                    if (curSmell.num != i) // 자신의 냄새와 다른 경우, 스킵
                        continue;

                    // 자신의 냄새와 같은 경우, 이동
                    curShark.x = nx;
                    curShark.y = ny;

                    curShark.curDir = curShark.dir[curShark.curDir][j]; // 방향 갱
                    check = true; // 이동함
                    break;
                }

                if (check)
                    break;
            }
        }

        // 맵 초기화
        for (int i = 0; i < n; i++)
            Arrays.fill(map[i], -1);

        // 겹치는 상어 죽이기
        for (int i = 0; i < m; i++) {
            Shark cur = sharks[i];

            if (sharks[i].dead)
                continue;

            if (map[cur.x][cur.y] == -1) { // 상어가 없는 경우, 추가
                map[cur.x][cur.y] = i;
            } else { // 상어가 이미 있는 경우, 현재 상어 죽음
                sharks[i].dead = true;
                deadSharkCnt++;
            }
        }
    }

    // 냄새 남기기
    static void makeSmell() {
        // 원래 냄새 없애기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (smells[i][j].size() == 0) // 냄새가 없는 경우, 스킵
                    continue;

                for (int t = smells[i][j].size() - 1; t >= 0; t--) {
                    Smell cur = smells[i][j].get(t);
                    if (cur.time + 1 == k) { // 냄새가 k 시간 지난 경우
                        smells[i][j].remove(t); // 냄새 제거
                    } else {
                        smells[i][j].set(t, new Smell(cur.num, cur.time + 1)); // 냄새 갱신
                    }
                }
            }
        }

        // 상어가 옮긴 자리에 냄새 남기기
        for (int i = 0; i < m; i++) {
            Shark cur = sharks[i];

            if (sharks[i].dead) // 죽은 상어 제외
                continue;

            smells[cur.x][cur.y].add(new Smell(i, 0));
        }
    }
}
