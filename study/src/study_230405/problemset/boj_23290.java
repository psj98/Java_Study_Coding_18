package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class boj_23290 {
    static ArrayList<Integer>[][] map; // 물고기 방향 정보
    static ArrayList<Integer>[][] smell; // 1, 2, 3 으로 체크
    static int[][] fishNum;
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 방향 정보
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[][] sharkDir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상어 상, 좌, 하, 우 사용
    static int sharkX, sharkY; // 상어 좌표
    static int[] maxSharkDir = new int[3]; // 상어가 움직일 방향 정보 -> max 값인 경우
    static int[] perArr = new int[3]; // 사전 순으로 앞선 상어 방향 정보 - 순열
    static int maxFish;
    static Queue<int[]> copyFish = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 맵 정보 초기화 (물고기 정보 저장)
        map = new ArrayList[4][4];
        smell = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList<>();
                smell[i][j] = new ArrayList<>();
            }
        }

        int n = Integer.parseInt(stk.nextToken());
        int cnt = Integer.parseInt(stk.nextToken()); // 마법 시전 횟수

        // 물고기 정보 저장
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;
            int dir = Integer.parseInt(stk.nextToken()) - 1;

            map[x][y].add(dir);
        }

        // 상어 좌표 저장
        stk = new StringTokenizer(br.readLine());
        sharkX = Integer.parseInt(stk.nextToken()) - 1;
        sharkY = Integer.parseInt(stk.nextToken()) - 1;

        // 마법 시전
        while (cnt-- != 0) {
            maxFish = -1;
            moveFish();
            moveShark(0, 0, sharkX, sharkY);
            makeSmell();
            fishPaste();
        }

        int ans = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                ans += map[i][j].size();

        sb.append(ans);
        System.out.println(sb);
    }

    // 물고기 이동
    static void moveFish() {
        ArrayList<Integer>[][] copyMap = new ArrayList[4][4]; // 이동한 물고기 저장할 맵
        fishNum = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                copyMap[i][j] = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 현재 위치의 모든 물고기들 탐색
                for (int cur : map[i][j]) {
                    copyFish.add(new int[] { i, j, cur }); // 현재 방향 물고기 저장

                    boolean check = false; // 움직이지 못하는 물고기 체크
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[(cur + 8 - k) % 8];
                        int ny = j + dy[(cur + 8 - k) % 8];

                        // 범위를 벗어나는 경우
                        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                            continue;

                        // 상어 위치, 물고기 냄새 위치 체크
                        if (nx == sharkX && ny == sharkY)
                            continue;

                        if (smell[nx][ny].size() != 0)
                            continue;

                        // 이동한 상어 저장
                        copyMap[nx][ny].add((cur + 8 - k) % 8);
                        check = true;
                        break;
                    }

                    // 움직이지 못하는 물고기 추가
                    if (!check)
                        copyMap[i][j].add(cur);
                }
            }
        }

        // 이동한 상어 원래 맵에 저장
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = copyMap[i][j];
                fishNum[i][j] = copyMap[i][j].size();
            }
        }
    }

    // 순열 (상, 좌 ,하, 우)
    static void moveShark(int cnt, int sum, int x, int y) {
        if (cnt == 3) {
            // 최댓값인 경우, 상어가 움직일 좌표에 저장
            if (maxFish < sum) {
                maxFish = Math.max(maxFish, sum);
                maxSharkDir = Arrays.copyOfRange(perArr, 0, 3);
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + sharkDir[i][0];
            int ny = y + sharkDir[i][1];

            // 범위를 벗어나는 경우
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                continue;

            perArr[cnt] = i;
            int size = fishNum[nx][ny];
            fishNum[nx][ny] = 0; // 상어가 간 곳은 0으로 변경
            moveShark(cnt + 1, sum + size, nx, ny);
            fishNum[nx][ny] = size; // 다시 원래 값으로 변경
        }
    }

    // 물고기 냄새 남기기
    static void makeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int size = smell[i][j].size();
                for (int k = size - 1; k >= 0; k--) {
                    int cur = smell[i][j].get(k) + 1;
                    smell[i][j].set(k, cur);

                    if (cur == 3) // 두 턴이 지나면 냄새가 사라짐
                        smell[i][j].remove(k);
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            sharkX += sharkDir[maxSharkDir[i]][0];
            sharkY += sharkDir[maxSharkDir[i]][1];

            if (map[sharkX][sharkY].size() == 0)
                continue;

            // 물고기 제거 및 냄새 남기기
            map[sharkX][sharkY].clear();
            smell[sharkX][sharkY].add(1);
        }
    }

    // 기존에 있던 물고기 복제
    static void fishPaste() {
        while (!copyFish.isEmpty()) {
            int[] cur = copyFish.poll();
            map[cur[0]][cur[1]].add(cur[2]);
        }
    }
}
