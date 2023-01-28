package mathematics;

import java.util.Scanner;

public class math_2609 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();

        System.out.printf("%d\n%d", gcd(n, m), lcm(n, m));

        scan.close();
    }

    public static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
