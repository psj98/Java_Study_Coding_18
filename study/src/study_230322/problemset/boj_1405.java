package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1405 {
    static int n;
    static boolean[][] visited = new boolean[30][30]; // 방문 배열
    static int[] dx = { 0, 0, 1, -1 }; // 방향 정보 (동, 서, 남, 북)
    static int[] dy = { 1, -1, 0, 0 };
    static double[] percent = new double[4]; // 확률 배열
    static double ans = 0; // 정답

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < 4; i++)
            percent[i] = Integer.parseInt(stk.nextToken()) * 0.01; // 확률 저장

        visited[15][15] = true; // 시작 위치
        dfs(0, 1, 15, 15);

        // 정답 출력
        sb.append(ans);
        System.out.println(sb);
    }

    // 합을 인자로 전달하는 백트래킹
    static void dfs(int cnt, double sum, int x, int y) {
        if (cnt == n) { // 이동 횟수가 같아지면 정답에 추가
            ans += sum;
            return;
        }

        // dfs
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!visited[nx][ny]) { // 방문하지 않은 곳에 대해
                visited[nx][ny] = true; // 방문 체크
                dfs(cnt + 1, sum * percent[i], nx, ny); // dfs
                visited[nx][ny] = false; // 원래대로
            }
        }
    }
}