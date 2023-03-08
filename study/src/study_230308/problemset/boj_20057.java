package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20057 {
    static int n, tx, ty, ans = 0; // 맵 크기, 토네이도 좌표, 밖으로 나간 모래 양
    static int[][] map; // 맵 크기
    static int[] dx = { 0, 1, 0, -1 }; // 방향 정보
    static int[] dy = { -1, 0, 1, 0 };

    // 토네이도가 왔을 때 퍼지는 곳 좌표
    static int[][] spreadX = {
            { 0, 0, -1, -1, -1, -2, 1, 1, 1, 2 }, // 좌
            { 1, 2, -1, 0, 1, 0, -1, 0, 1, 0 }, // 하
            { 0, 0, 1, 1, 1, 2, -1, -1, -1, -2 }, // 우
            { -1, -2, 1, 0, -1, 0, 1, 0, -1, 0 } }; // 상
    static int[][] spreadY = {
            { -1, -2, 1, 0, -1, 0, 1, 0, -1, 0 }, // 좌
            { 0, 0, -1, -1, -1, -2, 1, 1, 1, 2 }, // 하
            { 1, 2, -1, 0, 1, 0, -1, 0, 1, 0 }, // 우
            { 0, 0, 1, 1, 1, 2, -1, -1, -1, -2 } }; // 상
    static int[] ratio = { 0, 5, 1, 7, 10, 2, 1, 7, 10, 2 }; // 비율

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        // 초기화 및 맵 정보 저장
        tx = ty = n / 2; // 토네이도 시작 좌표
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        move(); // 토네이도 움직임

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 토네이도 움직이기
    static void move() {
        int idx = 0, num = 1; // 방향 정보, 몇 칸 이동하는지

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < num; j++) {
                    tx += dx[idx];
                    ty += dy[idx];

                    if (ty == -1) // y좌표가 -1이 되면 종료
                        return;

                    if (map[tx][ty] > 0) // 모래 양이 0보다 클 때, 모래를 퍼트림
                        spread(idx);
                }

                // 방향 정보 갱신
                idx++;
                idx %= 4;
            }

            num++;
        }
    }

    // 모래 퍼트리기
    static void spread(int idx) {
        int sum = 0, nx = 0, ny = 0; // y에서 뺄 모래 양

        for (int i = 1; i < 10; i++) {
            nx = tx + spreadX[idx][i];
            ny = ty + spreadY[idx][i];

            int amount = map[tx][ty] * ratio[i] / 100; // 모래 비율 계산
            sum += amount; // y에서 뺄 모래 양 갱신
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) // 범위 내인 경우, 해당 좌표에 모래 더함
                map[nx][ny] += amount;
            else // 범위 외인 경우, 정답에 모래 양을 더함
                ans += map[tx][ty] * ratio[i] / 100;
        }

        // 남아있는 모래 양의 위치
        nx = tx + spreadX[idx][0];
        ny = ty + spreadY[idx][0];

        if (nx >= 0 && nx < n && ny >= 0 && ny < n) // 범위 내인 경우, 기존 모래 양에서 퍼진 모래 양을 뺀만큼 더함
            map[nx][ny] += (map[tx][ty] - sum);
        else // 범위 외인 경우, 정답에 더함
            ans += (map[tx][ty] - sum);

        map[tx][ty] = 0; // 현재 위치의 모래 양을 0으로 갱신
    }
}
