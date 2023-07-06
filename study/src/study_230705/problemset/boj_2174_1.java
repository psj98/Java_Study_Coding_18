package study_230705.problemset;

import java.io.*;
import java.util.*;

public class boj_2174_1 {
    static int n, m;
    static int[][] map;
    static int[] dx = { -1, 0, 1, 0 }; // S E N W
    static int[] dy = { 0, 1, 0, -1 };
    static ArrayList<Robot> robots = new ArrayList<>();
    static ArrayList<Ops> ops = new ArrayList<>();

    // 로봇 클래스
    static class Robot {
        int x, y, dir; // 보는 방향

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    // 명령어 클래스
    static class Ops {
        int robotNo, moveCnt;
        char dir;

        public Ops(int robotNo, char dir, int moveCnt) {
            this.robotNo = robotNo;
            this.dir = dir;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        map = new int[n][m];

        stk = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(stk.nextToken());
        int b = Integer.parseInt(stk.nextToken());

        // 로봇 위치 및 보는 방향 - 로봇 번호 순서대로
        for (int i = 0; i < a; i++) {
            stk = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stk.nextToken()) - 1;
            int x = Integer.parseInt(stk.nextToken()) - 1;
            char charDir = stk.nextToken().charAt(0);

            // 초기 방향 설정
            int dir = -1;
            if (charDir == 'S') {
                dir = 0;
            } else if (charDir == 'E') {
                dir = 1;
            } else if (charDir == 'N') {
                dir = 2;
            } else if (charDir == 'W') {
                dir = 3;
            }

            map[x][y] = i + 1; // 로봇 초기 위치 설정
            robots.add(new Robot(x, y, dir));
        }

        // 로봇 행 저장
        for (int i = 0; i < b; i++) {
            stk = new StringTokenizer(br.readLine());
            int robotNo = Integer.parseInt(stk.nextToken());
            char dir = stk.nextToken().charAt(0);
            int moveCnt = Integer.parseInt(stk.nextToken());

            ops.add(new Ops(robotNo, dir, moveCnt));
        }

        sb.append(move());
        System.out.println(sb);
    }

    // 이동
    static String move() {
        for (Ops curOps : ops) {
            // 로봇 찾기
            Robot curRobot = robots.get(curOps.robotNo - 1);

            if (curOps.dir == 'L') { // 왼쪽으로 회전 - NWSE
                curRobot.dir = (curRobot.dir + curOps.moveCnt) % 4;
            } else if (curOps.dir == 'R') { // 오른쪽으로 회전 - NEWS
                curRobot.dir = (curRobot.dir + 100 - curOps.moveCnt) % 4;
            } else { // 이동
                int nx = curRobot.x;
                int ny = curRobot.y;

                map[nx][ny] = 0;

                for (int j = 0; j < curOps.moveCnt; j++) {
                    // 로봇 이동
                    nx += dx[curRobot.dir];
                    ny += dy[curRobot.dir];

                    // 충돌 확인
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        return new StringBuilder().append("Robot ").append(curOps.robotNo)
                                .append(" crashes into the wall").toString();
                    }

                    // 로봇 충돌 판정
                    if (map[nx][ny] != 0) {
                        return new StringBuilder().append("Robot ").append(curOps.robotNo)
                                .append(" crashes into robot ").append(map[nx][ny]).toString();
                    }
                }

                map[nx][ny] = curOps.robotNo;
                curRobot.x = nx;
                curRobot.y = ny;
            }
        }

        return "-1";
    }
}