package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_2550 {
    static int[] index, dp;
    static Node[] num;

    static class Node {
        int num, pos; // 값, 위치

        Node(int num, int pos) {
            this.num = num;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        index = new int[n + 1];
        dp = new int[n + 1];
        num = new Node[n + 1];

        // 전구 위치를 index의 위치에 i값으로 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(stk.nextToken());
            index[cur] = i;
        }

        // num에 현재 값과 index[값]의 위치를 저장
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(stk.nextToken());
            num[i] = new Node(cur, index[cur]);
        }

        int idx = 1; // 초기값
        dp[1] = num[1].pos;
        index[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[idx] < num[i].pos) { // dp 값이 작은 경우
                dp[++idx] = num[i].pos; // 현재 값을 dp 다음 값에 저장
                index[i] = idx; // idx 저장
            } else { // dp 값이 클 경우
                int low = lowerbound(1, idx, num[i].pos); // 넣을 위치 찾기
                dp[low] = num[i].pos; // 해당 위치에 num의 위치 저장
                index[i] = low; // idx(low) 저장
            }
        }

        sb.append(idx).append("\n"); // 스위치 수 출력

        ArrayList<Integer> list = new ArrayList<>(); // 부분 증가 수열 저장
        for (int i = n; i > 0; i--) {
            if (index[i] == idx) {
                list.add(num[i].num);
                idx--;
            }
        }

        Collections.sort(list); // 오름차순 정렬

        // 정답 출력
        for (int i = 0; i < list.size(); i++)
            sb.append(list.get(i)).append(" ");

        System.out.println(sb);
    }

    // binary search -> 값을 넣을 위치 찾기
    static int lowerbound(int left, int right, int num) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (dp[mid] >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
