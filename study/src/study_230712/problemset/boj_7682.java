package study_230712.problemset;

import java.io.*;

public class boj_7682 {
    static int xCnt, oCnt;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if (str.equals("end")) { // 기저 조건
                break;
            }

            // 초기화
            xCnt = 0;
            oCnt = 0;
            map = new char[3][3];

            // 맵 정보
            for (int i = 0; i < 9; i++) {
                map[i / 3][i % 3] = str.charAt(i);

                // X, O 개수 세기
                if (map[i / 3][i % 3] == 'X') {
                    xCnt++;
                } else if (map[i / 3][i % 3] == 'O') {
                    oCnt++;
                }
            }

            // 조건 체크
            if (getValid()) {
                sb.append("valid\n");
            } else {
                sb.append("invalid\n");
            }
        }

        // 정답 출력
        System.out.println(sb);
    }

    // 조건 체크
    static boolean getValid() {
        // X 5개, O 4개
        // 이기면 끝 => X 4개 O 3개인데 O에서 끝나면 X는 3개여야 함
        // 빙고가 있어야 끝남 => 다 채워지지 않은 경우
        boolean checkO = false, checkX = false;
        if (xCnt + oCnt == 9) {
            if (xCnt != oCnt + 1 || check('O')) // O가 이기면 안됨
                return false;
            return true;
        } else if (xCnt == oCnt) { // O가 이김
            checkO = check('O');
            checkX = check('X');

            if (checkO && !checkX) {
                return true;
            } else {
                return false;
            }
        } else if (xCnt == oCnt + 1) { // X가 이김
            checkO = check('O');
            checkX = check('X');

            if (!checkO && checkX) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    // 가로, 세로, 대각선 3개 연속 체크
    static boolean check(char c) {
        for (int i = 0; i < 3; i++) {
            // 가로
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) {
                return true;
            }

            // 세로
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) {
                return true;
            }
        }

        // 대각선
        if ((map[0][0] == c && map[1][1] == c && map[2][2] == c)
                || (map[0][2] == c && map[1][1] == c && map[2][0] == c)) {
            return true;
        }

        return false;
    }
}
