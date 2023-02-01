package bruteforce.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class recursion_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int[] arr = new int[12];
        arr[0] = 1;

        int t = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= 3; i++)
            for (int j = 1; j <= i; j++)
                arr[i] += arr[i - j];

        for (int i = 4; i < 12; i++)
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];

        for (int i = 0; i < t; i++) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            System.out.println(arr[n]);
        }
    }
}
