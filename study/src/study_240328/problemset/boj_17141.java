package study_240328.problemset;

import java.io.*;
import java.util.*;

public class boj_17141 {
    static int n, m, emptyCnt = 0, answer = Integer.MAX_VALUE;
    static int[][] map; // 맵 정보
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static ArrayList<int[]> virus; // 바이러스 위치
    static boolean[] check; // 선택할 바이러스

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken()); // 연구소 크기
        m = Integer.parseInt(stk.nextToken()); // 바이러스 개수

        // 초기화
        map = new int[n][n];
        virus = new ArrayList<>();

        // 맵 정보
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());

                if (map[i][j] == 0) { // 빈 공간 개수
                    emptyCnt++;
                } else if (map[i][j] == 2) { // 바이러스 정보
                    map[i][j] = 0; // 0으로 초기화
                    virus.add(new int[] { i, j }); // 바이러스 저장
                }
            }
        }

        emptyCnt += virus.size() - m; // 빈 공간 개수
        check = new boolean[virus.size()];

        combination(0, 0); // 바이러스 조합 찾기

        // 정답 출력
        sb.append(answer == Integer.MAX_VALUE ? -1 : answer);
        System.out.println(sb);
    }

    // 바이러스 조합 찾기
    static void combination(int idx, int cnt) {
        // 기저 조건
        if (cnt == m) {
            // 맵 복사
            int[][] copyMap = new int[n][n];
            for (int i = 0; i < n; i++) {
                copyMap[i] = Arrays.copyOfRange(map[i], 0, n);
            }

            bfs(copyMap); // BFS
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            int[] pos = virus.get(i);

            map[pos[0]][pos[1]] = 2;
            check[i] = true;
            combination(i + 1, cnt + 1);
            check[i] = false;
            map[pos[0]][pos[1]] = 0;
        }
    }

    // BFS
    static void bfs(int[][] bfsMap) {
        // 바이러스 시작 위치 저장
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < virus.size(); i++) {
            if (!check[i]) {
                continue;
            }

            queue.add(virus.get(i));
        }

        int cnt = 0, time = -1; // 바이러스가 퍼진 개수, 시간
        while (!queue.isEmpty()) {
            // 이미 시간이 더 많은 경우, 종료
            if (answer <= time) {
                break;
            }

            int size = queue.size(); // 현재 queue 크기
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 범위 체크
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    // 빈 공간 체크
                    if (bfsMap[nx][ny] != 0) {
                        continue;
                    }

                    bfsMap[nx][ny] = 2; // 바이러스 퍼뜨리기
                    cnt++; // 개수 증가
                    queue.add(new int[] { nx, ny });
                }
            }

            time++; // 시간 증가
        }

        // 모두 바이러스가 퍼진 경우, 최솟값 갱신
        if (emptyCnt == cnt) {
            answer = Math.min(answer, time);
        }
    }
}
