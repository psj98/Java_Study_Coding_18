package mathematics;

import java.util.*;

public class math_1037 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        List<Integer> numList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int m = scan.nextInt();
            numList.add(m);
        }

        numList.sort(Comparator.naturalOrder());

        System.out.println(numList.get(0) * numList.get(n - 1));

        scan.close();
    }
}
