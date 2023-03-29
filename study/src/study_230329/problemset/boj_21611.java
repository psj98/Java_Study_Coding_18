package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_21611 {
    static int n, m;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 }; // 위 아래 왼 오른
    static int[] moveX = { 0, 1, 0, -1 }, moveY = { -1, 0, 1, 0 }; // 왼 아래 오른 위
    static int[] marble;
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        marble = new int[3]; // 폭발한 구슬 개수
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(stk.nextToken()) - 1;
            int scope = Integer.parseInt(stk.nextToken());

            destroy(dir, scope); // 파괴
            makeQueue(); // queue 생성
            moveMarble(); // 배열에 넣기
            makeQueue();
            explosion(); // 폭발
            moveMarble();
            makeQueue();
            change(); // 변화
            moveMarble();
        }

        // 정답 계산 및 출력
        int ans = 0;
        for (int i = 1; i <= 3; i++) {
            ans += (i * marble[i - 1]);
        }

        sb.append(ans);
        System.out.println(sb);
    }

    // 구슬 파괴
    static void destroy(int dir, int scope) {
        int x = n / 2, y = n / 2;
        while (scope != 0) { // 해당 scope 만큼 이동하면서 0으로 변경
            x += dx[dir];
            y += dy[dir];
            map[x][y] = 0;
            scope--;
        }
    }

    // queue에 순서대로 넣기
    static void makeQueue() {
        queue = new ArrayDeque<>();
        int x = n / 2, y = n / 2, idx = 0, cnt = 1;
        while (true) { // 왼 아래 오른 위 순서대로
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    x += moveX[idx];
                    y += moveY[idx];

                    if (y == -1) {
                        return;
                    }

                    if (map[x][y] != 0)
                        queue.add(map[x][y]);
                }

                idx = (idx + 1) % 4; // 방향 이동
            }

            cnt++;
        }
    }

    // queue에서 빼서 배열에 저장
    static void moveMarble() {
        int x = n / 2, y = n / 2, idx = 0, cnt = 1;
        while (true) { // 왼 아래 오른 위 순서대로 저장
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    x += moveX[idx];
                    y += moveY[idx];

                    if (y == -1) {
                        return;
                    }

                    if (!queue.isEmpty()) // queue에 값이 있으면 해당 값 저장
                        map[x][y] = queue.poll();
                    else // 값이 없으면 0 저장
                        map[x][y] = 0;
                }

                idx = (idx + 1) % 4; // 방향 이동
            }

            cnt++;
        }
    }

    // 구슬 폭발
    static void explosion() {
        int destroyCnt = -1;
        while (destroyCnt != 0) { // 구슬 폭발이 안이루어지 때까지 반복
            Queue<Integer> next = new ArrayDeque<>(); // 연속된 개수를 셀 queue
            queue.add(0);

            int curNum = queue.poll(), cnt = 1;
            destroyCnt = 0; // 파괴된 횟수
            while (!queue.isEmpty()) {
                int num = queue.poll();

                if (curNum != num) {
                    if (cnt < 4) { // 4개 미만이면, 해당 값을 next에 cnt만큼 저장
                        for (int i = 0; i < cnt; i++)
                            next.add(curNum);
                    } else { // 4개 이상이면, marble 배열에 폭발한 구슬 개수 추가
                        destroyCnt++;
                        marble[curNum - 1] += cnt;
                    }

                    curNum = num;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }

            while (!next.isEmpty()) {
                queue.add(next.poll());
            }
        }
    }

    // 구슬 변화
    static void change() {
        Queue<Integer> next = new ArrayDeque<>(); // 구슬 그룹을 셀 queue
        queue.add(0);

        int curNum = queue.poll(), cnt = 1;
        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (curNum != num) { // 구슬이 다른 경우
                next.add(cnt); // 구슬 개수
                next.add(curNum); // 구슬 넘버

                curNum = num;
                cnt = 1;
            } else {
                cnt++;
            }
        }

        while (!next.isEmpty()) {
            queue.add(next.poll());
        }
    }
}
