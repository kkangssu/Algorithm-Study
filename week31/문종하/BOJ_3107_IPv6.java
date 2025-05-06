import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3107_IPv6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String IPv6 = br.readLine(); // 최대 39글자 / 숫자 0-9, 알파벳 소문자 a-f, 콜론 :

        boolean isLast = IPv6.endsWith("::");
        if(isLast || IPv6.split("::").length == 2){
            String [] arr = IPv6.split(":");
            boolean isFirst = IPv6.startsWith("::");
            int gap = 8 - arr.length + 1 + (isFirst?1:0) - (isLast?1:0);
            StringBuilder sb = new StringBuilder();
            if(!isFirst) sb.append(":");
            for (int i = 0; i < gap; i++)
                sb.append("0000:");

            IPv6 = IPv6.replace("::", sb.toString());
        }

        String [] arr = IPv6.split(":");

        for (int i = 0; i < arr.length; i++)
            arr[i] = String.format("%4s", arr[i]).replace(" ", "0");

        System.out.println(String.join(":", arr)); // IPv6의 축약되지 않은 형태를 출력
    }
}
