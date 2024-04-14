package study_240411.problemset;

import java.io.*;

public class boj_9082 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine()); // testcase

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine()); // 배열 크기

            String mineCnt = br.readLine();
            br.readLine();

            // 주변 지뢰 개수
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++) {
                cnt[i] = mineCnt.charAt(i) - '0';
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                // 모든 경우에 대해 왼쪽, 중간, 오른쪽이 모두 1이상인 경우, --
                if (i == 0) { // 맨 왼쪽
                    if (cnt[i] != 0 && cnt[i + 1] != 0) {
                        cnt[i]--;
                        cnt[i + 1]--;
                        ans++;
                    }
                } else if (i == n - 1) { // 맨 오른쪽
                    if (cnt[i - 1] != 0 && cnt[i] != 0) {
                        cnt[i - 1]--;
                        cnt[i]--;
                        ans++;
                    }
                } else { // 중간
                    if (cnt[i - 1] != 0 && cnt[i] != 0 && cnt[i + 1] != 0) {
                        cnt[i - 1]--;
                        cnt[i]--;
                        cnt[i + 1]--;
                        ans++;
                    }
                }
            }

            // 정답 저장
            sb.append(ans).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
