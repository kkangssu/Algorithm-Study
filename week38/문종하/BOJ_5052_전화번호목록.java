import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    00:15
    전화번호의 길이는 길어야 10자리이며,
    목록에 있는 두 전화번호가 같은 경우는 없다.

    긴급전화: 911
    상근: 97 625 999
    선영: 91 12 54 26
*/
public class BOJ_5052_전화번호목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];

            for (int i = 0; i < n; i++)
                numbers[i] = br.readLine();

            Arrays.sort(numbers);

            boolean flag = true;
            for (int i = 0; i < n - 1; i++)
                if (numbers[i + 1].startsWith(numbers[i])) {
                    flag = false;
                    break;
                }

            sb.append(flag ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }
}
