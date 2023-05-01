package study_230426.problemset;

import java.io.*;
import java.util.*;

public class boj_16235_1 {
    static int n; // 맵 크기
    static ArrayList<Integer>[][] map; // 나무
    static int[][] food; // 현재 양분
    static int[][] addFood; // 겨울에 추가할 양분
    static int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
    static int[] dy = { 0, 0, 1, -1, 1, -1, 1, -1 };

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        // 초기화 및 양분 정보 입력
        food = new int[n][n];
        addFood = new int[n][n];
        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map[i][j] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                food[i][j] = 5;
                addFood[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 초기 나무 정보
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken()) - 1;
            int y = Integer.parseInt(stk.nextToken()) - 1;
            
            map[x][y].add(Integer.parseInt(stk.nextToken()));
        }

        // 봄, 여름, 가을, 겨울
        while (k-- != 0) {
            springAndSummer();
            autumn();
            winter();
        }

        // 남은 나무 수 계산
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ans += map[i][j].size();

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    static void springAndSummer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Collections.sort(map[i][j], Collections.reverseOrder()); // 내림차순 정렬 (나이 순)

                // 나이만큼 양분 먹기
                int dead = 0;
                int size = map[i][j].size();

                for (int k = size - 1; k >= 0; k--) {
                    int age = map[i][j].get(k); // 현재 나이
                    if (age <= food[i][j]) {
                        food[i][j] -= age; // 나무 나이만큼 양분 감소
                        map[i][j].set(k, age + 1); // 나무 나이 증가
                    } else {
                        dead += age / 2; // 죽은 나무 나이 / 2 만큼 더함
                        map[i][j].remove(k); // 해당 나무 삭제
                    }
                }

                food[i][j] += dead; // 양분에 추가
            }
        }
    }

    static void autumn() {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int age : map[i][j]) {
                    if (age % 5 != 0) // 5로 나눠지지 않는 경우 스킵
                        continue;

                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // 범위 체크
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                            continue;

                        queue.add(new int[] { nx, ny }); // 위치 추가
                    }
                }
            }
        }

        // 해당 위치에 나이가 1인 나무 추가
        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.poll()[1];

            map[x][y].add(1);
        }
    }

    // 양분 추가
    static void winter() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                food[i][j] += addFood[i][j];
    }
}
