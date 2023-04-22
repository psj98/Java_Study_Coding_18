package study_230419.problemset;

import java.io.*;
import java.util.*;

public class boj_2138 {
    static int n, m, ans = 0;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int zero = 0;
    static boolean[][] visited;
    static ArrayList<int[]> checkVisit;
    static ArrayList<int[]> pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        // 초기화 및 값 장
        pos = new ArrayList<>();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        while (true) {
            pos = new ArrayList<>();
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) // 방문 체크
                        continue;

                    if (map[i][j] <= 0) // 무지개, 검은색 블록 스킵
                        continue;

                    findBlock(i, j, map[i][j]); // 블록 찾기

                    for (int[] cur : checkVisit) // 무지개 블록 원래대로 되돌리기
                        visited[cur[0]][cur[1]] = false;
                }
            }

            // 제거할 블록이 없는 경우, 종료
            if (pos.size() == 0)
                break;

            ans += pos.size() * pos.size(); // 정답 갱신

            for (int[] cur : pos)
                map[cur[0]][cur[1]] = -2; // 블록 제거하기

            gravity(); // 중력
            rotate(); // 반시계 방향 회전
            gravity(); // 중력
        }

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 블록 찾기
    static void findBlock(int x, int y, int num) {
        Queue<int[]> queue = new ArrayDeque<>();
        ArrayList<int[]> bomb = new ArrayList<>();
        int curZero = 0; // 무지개 블록 개수 찾기
        checkVisit = new ArrayList<>(); // 0인 배열의 위치 따로 저장

        queue.add(new int[] { x, y });
        bomb.add(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                // 방문 체크
                if (visited[nx][ny])
                    continue;

                // 같은 블록 체크
                if (map[nx][ny] == num) {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny });
                    bomb.add(new int[] { nx, ny });
                    continue;
                }

                // 무지개 블록 체크
                if (map[nx][ny] == 0) {
                    curZero++;
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny });
                    bomb.add(new int[] { nx, ny });
                    checkVisit.add(new int[] { nx, ny });
                }
            }
        }

        // 2 이상인 경우에 갱신
        if (bomb.size() < 2)
            return;

        // 현재 블록의 개수가 더 많은 경우 갱신
        if (bomb.size() > pos.size()) {
            pos.clear();
            for (int i = 0; i < bomb.size(); i++)
                pos.add(bomb.get(i));

            zero = curZero;
        } else if (bomb.size() == pos.size()) { // 현재 블록의 개수와 같은 경우
            if (curZero >= zero) { // 무지개 블록의 개수가 많거나 같은 경우에만 갱신
                                   // -> 행, 열은 앞에서부터 순차적으로 기준 블록을 잡기 때문에 따로 조건을 줄 필요 없음
                pos.clear();
                for (int i = 0; i < bomb.size(); i++)
                    pos.add(bomb.get(i));

                zero = curZero;
            }
        }
    }

    // 중력
    static void gravity() {
        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            int stdX = n - 1, stdY = i; // 기준 블록 위치
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] == -2) // -2인 경우 스킵
                    continue;

                if (map[j][i] == -1) { // 검은색 블록이 나올 경우, 기준 블록부터 위로 올라가면서 블록 저장
                    while (!queue.isEmpty()) {
                        map[stdX--][stdY] = queue.poll();
                    }

                    stdX = j - 1; // 좌표 갱신

                    continue;
                }

                queue.add(map[j][i]);
                map[j][i] = -2;
            }

            // 마지막 남은 블록까지 저장
            while (!queue.isEmpty()) {
                map[stdX--][stdY] = queue.poll();
            }
        }
    }

    // 반시계 방향 회전
    static void rotate() {
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++)
            copyMap[i] = Arrays.copyOf(map[i], n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map[n - j - 1][i] = copyMap[i][j];
    }
}
