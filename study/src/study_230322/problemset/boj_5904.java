package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_5904 {
    static char ans; // 정답

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        divide(n); // 분할 정복

        sb.append(ans);
        System.out.println(sb);
    }

    // S(k)=S(k-1) + 'm'(1)+'o'(k+2) + S(k-1);
    static void divide(int n) {
        int center = 3, side = 4; // S(0) 개수, 가운데에 붙일 개수

        if (n == 1) // 맨 앞은 m
            ans = 'm';
        else if (n == 2 || n == 3) // 다음 두 자리수는 o
            ans = 'o';
        else {// 그 외의 경우 점화식을 통해 개수를 셈
            while (true) { // 개수가 n보다 크거나 같아질 때까지 계산
                if (center * 2 + side >= n)
                    break;
                center = center * 2 + side++;
            }

            if (center + 1 == n) // 위의 점화식에서 맨 앞에 S(k-1)이 center -> center 다음 자리는 무조건 m
                ans = 'm';
            else if (center + 1 < n && n <= center + side) { // 'o'(k+2) 위치
                ans = 'o';
            } else { // 뒤에 위치한 S(k-1)인 경우, 앞으로 땡겨서 재귀
                divide(n - center - side);
                return;
            }
        }
    }
}