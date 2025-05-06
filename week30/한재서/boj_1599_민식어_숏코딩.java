import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        IntStream.range(0, N)
                .mapToObj(i -> {
                    try {
                        return br.readLine().replace("k", "c").replace("ng", "n~");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted()
                .map(s -> s.replace("c", "k").replace("n~", "ng"))
                .forEach(s -> sb.append(s).append("\n"));
                
        System.out.print(sb.toString());
    }
}
