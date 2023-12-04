package study_231122.problemset;

class Solution {
    boolean[] binaryArr; // 이진트리 배열
    int ans; // 가능 여부

    public int[] solution(long[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            String num = Long.toBinaryString(numbers[i]);

            int numLen = num.length();

            // binary를 만들 수 있는 가능한 이진트리 찾기
            int h = 1;
            while (Math.pow(2, h) - 1 < numLen) {
                h++;
            }

            int treeLen = (int) Math.pow(2, h) - 1; // 가능한 이진트리 길이
            int idx = treeLen - numLen;
            binaryArr = new boolean[treeLen];

            // 맨 앞은 0으로 채우기
            for (int j = 0; j < numLen; j++) {
                binaryArr[idx++] = (num.charAt(j) == '1' ? true : false);
            }

            ans = 1;
            find(0, treeLen - 1, false);
            answer[i] = ans;
        }

        return answer;
    }

    // 가능한지 찾기
    public void find(int start, int end, boolean check) {
        int mid = (start + end) / 2; // root 찾기

        // root가 0이면 불가능
        if (check && binaryArr[mid]) {
            ans = 0;
            return;
        }

        // 같을 때까지 진행
        if (start != end) {
            find(start, mid - 1, !binaryArr[mid]); // 왼쪽 트리
            find(mid + 1, end, !binaryArr[mid]); // 오른쪽 트리
        }
    }
}