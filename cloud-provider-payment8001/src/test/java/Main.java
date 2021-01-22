import java.io.*;
import java.util.*;


public class Main {
    static int n;
    static int ans;
    static boolean [] col ,udg , dg;
    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = scanner.nextInt();
        col = new boolean[n];
        udg = new boolean[2 * n];
        dg = new boolean[2 * n];

        dfs(0);
        for(int i = 0 ;i < res.size() ;i ++) {
            List<Integer> t = res.get(i);
            for(int j = 0 ;j < t.size() ;j ++) bw.write(t.get(j) + " ");
            bw.newLine();
        }
        bw.write(ans + "");
        bw.close();
    }
    public static void dfs(int u) {
        if(u == n) {
            ans ++;
            if(ans < 3) res.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0 ;i < n ;i ++) {
            if(!col[i] && !udg[u - i + n] && !dg[u + i]) {
                col[i] = udg[u - i + n] = dg[u + i] = true;
                path.add(i + 1);
                dfs(u + 1);
                path.remove(path.size() - 1);
                col[i] = udg[u - i + n] = dg[u + i] = false;
            }
        }
    }
}
