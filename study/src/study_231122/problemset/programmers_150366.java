package study_231122.problemset;

import java.util.*;

class Solution {
    int[] parent = new int[2501];
    String[] value = new String[2501];

    public String[] solution(String[] commands) {
        StringTokenizer stk;
        List<String> ans = new ArrayList<>();

        // 초기화
        makeSet();

        for (String command : commands) {
            stk = new StringTokenizer(command);

            String op = stk.nextToken();

            if (op.equals("UPDATE")) {
                // 전체 값 수정
                if (stk.countTokens() == 2) {
                    String from = stk.nextToken();
                    String to = stk.nextToken();

                    for (int i = 1; i <= 2500; i++) {
                        // 다른 경우 스킵
                        if (!from.equals(value[i])) {
                            continue;
                        }

                        value[i] = to; // 변경
                    }
                } else { // 특정 위치의 값만 변경
                    int x = Integer.parseInt(stk.nextToken());
                    int y = Integer.parseInt(stk.nextToken());

                    String to = stk.nextToken();

                    // 해당 위치의 값 변경
                    int num = convertNum(x, y);
                    value[find(num)] = to;
                }
            } else if (op.equals("MERGE")) {
                int x1 = Integer.parseInt(stk.nextToken());
                int y1 = Integer.parseInt(stk.nextToken());
                int x2 = Integer.parseInt(stk.nextToken());
                int y2 = Integer.parseInt(stk.nextToken());

                int num1 = convertNum(x1, y1);
                int num2 = convertNum(x2, y2);

                int root1 = find(num1);
                int root2 = find(num2);

                // 부모가 같으면 MERGE 스킵
                if (root1 == root2) {
                    continue;
                }

                // 값이 있는 것 찾음
                String str = value[root1].isBlank() ? value[root2] : value[root1];
                value[root1] = value[root2] = "";

                union(root1, root2); // MERGE

                value[root1] = str; // 부모의 값 변경
            } else if (op.equals("UNMERGE")) {
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());

                int num = convertNum(x, y);
                int root = find(num);

                String str = value[root]; // 부모 값

                value[root] = ""; // 부모 값 초기화
                value[num] = str; // 지정된 위치의 값

                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    // 부모가 아닌 경우, 스킵
                    if (find(i) != root) {
                        continue;
                    }

                    list.add(i); // 부모인 경우, 추가
                }

                // 초기화
                for (int cur : list) {
                    parent[cur] = cur;
                }
            } else if (op.equals("PRINT")) {
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());

                int num = convertNum(x, y);
                int root = find(num);

                // 값이 없는 경우, EMPTY
                if (value[root].isBlank()) {
                    ans.add("EMPTY");
                } else { // 값이 있는 경우, 해당 값 저장
                    ans.add(value[root]);
                }
            }
        }

        return ans.toArray(new String[0]);
    }

    // 초기화
    public void makeSet() {
        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
            value[i] = "";
        }
    }

    // find
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    // union
    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    // 좌표를 번호로 변환
    public int convertNum(int x, int y) {
        int result = 50 * (x - 1);
        return result + y;
    }
}