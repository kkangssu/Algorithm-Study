import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();
        int[][] check = new int[first.length() + 1][second.length() + 1];
        for(int i=1; i < first.length() + 1; i++) {
            for(int j=1; j < second.length() + 1; j++) {
                if(first.charAt(i - 1) == second.charAt(j - 1)) {
                    check[i][j] = check[i - 1][j - 1] + 1;
                } else {
                    check[i][j] = Math.max(check[i - 1][j], check[i][j - 1]);
                }
            }
        }

        System.out.println(check[first.length()][second.length()]);
    }
}
