package study_230607.problemset;

import java.io.*;

public class boj_17609 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /**
         * 투 포인터
         * 
         * 1. 왼쪽 끝, 오른쪽 끝에서 시작해서 같으면 한 칸씩 이동
         * 2. 다른 경우, left+1 ~ right와 left ~ right-1이 같은지 확인 -> 같으면 유사 회문
         * 3. 둘 다 다른 경우, 회문 X
         */

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = br.readLine(); // 문자열 입력

            int left = 0, right = str.length() - 1; // 초기값
            if (check(left, right, str)) // 회문 체크
                sb.append(0);
            else { // 회문이 아닌 경우, 유사 회문 체크
                if (pseudo(left, right, str))
                    sb.append(1);
                else
                    sb.append(2);
            }

            sb.append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 회문 체크
    static boolean check(int left, int right, String str) {
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }

    // 유사 회문 체크
    static boolean pseudo(int left, int right, String str) {
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) { // 값이 다른 경우
                if (check(left + 1, right, str)) // left+1 ~ right 체크
                    return true; // 유사 회문
                if (check(left, right - 1, str)) // left ~ right-1 체크
                    return true; // 유사 회문
                return false; // 회문이 아님
            }

            left++;
            right--;
        }

        return true;
    }
}