package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class alg_1244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(stk.nextToken());

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            String check = stk.nextToken();
            int num = Integer.parseInt(stk.nextToken());

            if (check.equals("1")) {
                for (int j = 1; j <= n; j++) {
                    if (j % num == 0)
                        if (arr[j] == 1)
                            arr[j] = 0;
                        else
                            arr[j] = 1;
                }
            } else {
                int idx = 1;

                if (arr[num] == 1)
                    arr[num] = 0;
                else
                    arr[num] = 1;

                while (num - idx > 0 && num + idx <= n) {
                    if (arr[num - idx] == arr[num + idx]) {
                        if (arr[num - idx] == 0)
                            arr[num - idx] = arr[num + idx] = 1;
                        else
                            arr[num - idx] = arr[num + idx] = 0;
                        idx++;
                    }

                    else
                        break;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i != 1 && (i - 1) % 20 == 0)
                System.out.println();
            System.out.print(arr[i] + " ");
        }
    }
}
