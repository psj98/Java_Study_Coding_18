package study_231011.problemset;

import java.io.*;

public class boj_2661 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        find(n, "");
    }

    static void find(int n, String str) {
        if (str.length() == n) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            // 조건 체크
            if (!check(str + String.valueOf(i))) {
                continue;
            }

            find(n, str + String.valueOf(i));
        }
    }

    static boolean check(String str) {
        for (int i = 1; i <= str.length() / 2; i++) {
            String left = str.substring(str.length() - i * 2, str.length() - i);
            String right = str.substring(str.length() - i, str.length());

            if (left.equals(right)) {
                return false;
            }
        }

        return true;
    }
}