package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        Stack<long[]> stack = new Stack<>();
        ArrayList<long[]> pos = new ArrayList<>();
        ArrayList<long[]> list = new ArrayList<>();
        long a = 0; // 다른 봉우리에 의해 포함되지 않는 봉우리 개수
        long b = 0; // 다른 봉우리를 포함하지 않는 봉우리 개수
        int minNum = Integer.MAX_VALUE, idx = 0; // 가장 왼쪽의 변을 찾기 위해

        // 입력
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            pos.add(new long[] { x, y });
            if (x < minNum) { // 가장 왼쪽 변 찾기 (x가 가장 작을 때)
                minNum = x;
                idx = i;
            }
        }

        Collections.rotate(pos, -idx); // x가 가장 작은 것을 맨 앞으로 이동
        if (pos.get(0)[0] != pos.get(1)[0]) // x가 같은 두 좌표가 붙어있게 하기 위함
            Collections.rotate(pos, 1);
        pos.add(new long[] { pos.get(0)[0], pos.get(0)[1] }); // 뒤의 값을 빼기 위해서 맨 앞의 값을 뒤로 넣어줌

        // x좌표가 같은 값일 때, 두 y좌표의 부호가 다른게 들어올 수 밖에 없음
        long check = 0, matchNum = 1;
        for (int i = 0; i < n; i++) {
            long num = pos.get(i)[1] * pos.get(i + 1)[1]; // 부호가 다르면
            if (num < 0) {
                list.add(new long[] { pos.get(i)[0], matchNum }); // 매치되는 봉우리의 양쪽 변을 넣음 (matchNum으로 확인)
                check++;
                if (check % 2 == 0) // 두 개가 넣어지면 매칭된 것이기 때문에 matchNum 증가
                    matchNum++;
            }
        }

        list.sort((o1, o2) -> o1[0] < o2[0] ? -1 : ((o1[0] == o2[0]) ? 0 : 1)); // x좌표 기준으로 정렬

        for (int i = 0; i < list.size(); i++) {
            long num = list.get(i)[1]; // matchNum

            // 스택이 비어있으면, 봉우리에 포함되지 않는 것이기 때문에 a++
            if (stack.empty()) {
                stack.push(new long[] { num, 0 }); // 0은 봉우리를 포함하는지 체크하기 위한 값
                a++;
            } else { // 스택이 비어있지 않으면
                if (stack.peek()[0] == num) { // peek()의 matchNum과 스택에 들어갈 matchNum이 같으면
                    if (stack.peek()[1] == 0) { // 봉우리를 포함하지 않으면, b++
                        b++;
                    }

                    stack.pop(); // 스택에서 빼줌
                } else { // 다르면, 봉우리가 안에 더 있다는 뜻이므로 peek()의 matchNum을 1로 변경
                    stack.peek()[1] = 1;
                    stack.push(new long[] { num, 0 }); // 스택에 값 추가
                }
            }
        }

        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }
}
