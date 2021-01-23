import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int [] f = new int[m + 1];
        f[0] = 1;
        int val = 0;
        for(int i = 0; i < n ;i ++){
            val = scanner.nextInt();
            for(int j = i ;j <= m ;j ++) f[i] = f[j - val];
        }

        System.out.println(f[m]);
    }

}
