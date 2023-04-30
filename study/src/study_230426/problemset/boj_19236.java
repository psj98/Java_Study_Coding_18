package study_230426.problemset;

import java.io.*;
import java.util.*;

public class boj_19236 {
    static int ans;
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // x 방향 정보
    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 }; // y 방향 정보

    static class Fish {
        int x, y, dir; // 위치, 방향
        boolean isAlive; // 살아있는지 체크

        Fish(int x, int y, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // 초기화
        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];

        // 맵, 물고기 정보
        for (int i = 0; i < 4; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(stk.nextToken());
                int dir = Integer.parseInt(stk.nextToken()) - 1;
                map[i][j] = num;
                fishes[num] = new Fish(i, j, dir, true);
            }
        }

        eat(map, fishes, 0, 0, 0); // 물고기 먹기 & 물고기 이동

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 물고기 이동
    static void moveFish(int[][] map, Fish[] fishes, int sharkX, int sharkY) {
        for (int i = 1; i <= 16; i++) {
            Fish cur = fishes[i];
            if (!cur.isAlive) // 이동할 물고기가 죽은 경우, 이동 불가
                continue;

            for (int j = 0; j < 8; j++) {
                int nx = cur.x + dx[(cur.dir + j) % 8];
                int ny = cur.y + dy[(cur.dir + j) % 8];

                // 범위 체크
                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                    continue;

                // 상어 위치로 이동 불가
                if (nx == sharkX && ny == sharkY)
                    continue;

                // 바꿀 물고기
                Fish next = fishes[map[nx][ny]];
                next.x = cur.x;
                next.y = cur.y;

                // 현재 물고기
                cur.x = nx;
                cur.y = ny;
                cur.dir = (cur.dir + j) % 8;

                // 물고기 자리 변경
                int tempNum = map[next.x][next.y];
                map[next.x][next.y] = map[cur.x][cur.y];
                map[cur.x][cur.y] = tempNum;

                break;
            }
        }
    }

    // 상어가 물고기 먹기 -> 백트래킹
    static void eat(int[][] map, Fish[] fishes, int sum, int sharkX, int sharkY) {
        // 맵 Copy
        int[][] copyMap = new int[4][4];
        for (int i = 0; i < 4; i++)
            copyMap[i] = Arrays.copyOf(map[i], 4);

        // 물고기 Copy
        Fish[] copyFishes = new Fish[17];
        for (int i = 1; i <= 16; i++)
            copyFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].dir, fishes[i].isAlive);

        // 상어 초기 정보
        int sharkDir = copyFishes[copyMap[sharkX][sharkY]].dir; // 상어 방향
        copyFishes[copyMap[sharkX][sharkY]].isAlive = false; // 물고기 죽음
        sum += copyMap[sharkX][sharkY]; // 상어가 물고기 먹음

        ans = Math.max(ans, sum); // 최댓값 갱신

        moveFish(copyMap, copyFishes, sharkX, sharkY); // 물고기 이동

        // 상어 이동
        int idx = 1;
        while (true) {
            int nx = sharkX + dx[sharkDir] * idx;
            int ny = sharkY + dy[sharkDir] * idx++;

            // 범위 체크
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                return;

            // 죽은 물고기면 스킵
            if (!copyFishes[copyMap[nx][ny]].isAlive)
                continue;

            eat(copyMap, copyFishes, sum, nx, ny); // 백트래킹
        }
    }
}
