package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_20056 {
    static int n;
    static ArrayList<FireBall> fireBalls; // 파이어볼 저장 리스트
    static ArrayList<FireBall>[][] moveMap; // 파이어볼 이동 시, 맵
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 파이어볼 방향 정보
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    // 파이어볼 클래스
    static class FireBall {
        int x, y, mass, speed, dir;

        FireBall(int x, int y, int mass, int speed, int dir) {
            this.x = x;
            this.y = y;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        // 초기화
        fireBalls = new ArrayList<>();
        moveMap = new ArrayList[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                moveMap[i][j] = new ArrayList<>();

        // 파이어볼 입력
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int mass = Integer.parseInt(stk.nextToken());
            int speed = Integer.parseInt(stk.nextToken());
            int dir = Integer.parseInt(stk.nextToken());

            fireBalls.add(new FireBall(x, y, mass, speed, dir)); // 파이어볼 저장
        }

        // k만큼 파이어볼 움직이고 퍼트리기
        for (int i = 0; i < k; i++) {
            move();
            spread();
        }

        // 질량 합 출력
        int sum = 0;
        for (FireBall fireBall : fireBalls)
            sum += fireBall.mass;

        sb.append(sum);
        System.out.println(sb);
    }

    // 파이어볼 이동
    static void move() {
        for (FireBall fireBall : fireBalls) {
            // 파이어볼 좌표 이동
            fireBall.x = (fireBall.x + dx[fireBall.dir] * fireBall.speed) % n;
            fireBall.y = (fireBall.y + dy[fireBall.dir] * fireBall.speed) % n;

            if (fireBall.x < 0)
                fireBall.x += n;
            if (fireBall.y < 0)
                fireBall.y += n;

            moveMap[fireBall.x][fireBall.y].add(fireBall); // moveMap에 이동한 파이어볼 저장
        }

        fireBalls.clear(); // 파이어볼 리스트 비움
    }

    // 파이어볼 퍼트리기
    static void spread() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (moveMap[i][j].size() >= 2) { // 파이어볼이 2개 이상인 곳
                    int mass = 0;
                    int speed = 0;
                    boolean even = false, odd = false; // 방향 짝수, 홀수 체크

                    // 파이어볼들의 질량, 속도 합 및 방향 짝수, 홀수 정보 체크
                    for (FireBall fireBall : moveMap[i][j]) {
                        mass += fireBall.mass;
                        speed += fireBall.speed;

                        if (fireBall.dir % 2 == 0)
                            even = true;
                        else
                            odd = true;
                    }

                    mass /= 5; // 질량 계산
                    speed /= moveMap[i][j].size(); // 속도 계산

                    // 질량이 0이면 파이어볼 사라짐
                    if (mass == 0) {
                        moveMap[i][j].clear();
                        continue;
                    }

                    if (even && odd) { // 짝수, 홀수 둘 다 있으면 1, 3, 5, 7로 퍼짐
                        for (int k = 1; k < 8; k += 2)
                            fireBalls.add(new FireBall(i, j, mass, speed, k));
                    } else { // 짝수 또는 홀수만 있으면 0, 2, 4, 6으로 퍼짐
                        for (int k = 0; k < 8; k += 2)
                            fireBalls.add(new FireBall(i, j, mass, speed, k));
                    }
                } else if (moveMap[i][j].size() == 1) { // 파이어볼이 하나만 있는 경우, 그대로 저장
                    fireBalls.add(moveMap[i][j].get(0));
                }

                moveMap[i][j].clear(); // 파이어볼 이동 맵 초기화
            }
        }
    }
}
