package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bruteforce_2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> arr = new ArrayList<>();

        int sum = 0;
        boolean check = false;

        for (int i = 0; i < 9; i++) {
            int n = Integer.parseInt(br.readLine());
            sum += n;
            arr.add(n);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                int num1 = arr.get(i);
                int num2 = arr.get(j);

                if (sum - num1 - num2 == 100) {
                    arr.set(i, 0);
                    arr.set(j, 0);
                    check = true;
                    break;
                }
            }

            if (check)
                break;
        }

        Collections.sort(arr);

        for (int i = 2; i < 9; i++)
            sb.append(arr.get(i)).append("\n");

        System.out.println(sb);
    }
}
