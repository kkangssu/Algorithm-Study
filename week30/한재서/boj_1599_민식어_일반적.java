import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('k', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('g', 6);
        map.put('h', 7);
        map.put('i', 8);
        map.put('l', 9);
        map.put('m', 10);
        map.put('n', 11);
        map.put('c', 12);
        map.put('o', 13);
        map.put('p', 14);
        map.put('r', 15);
        map.put('s', 16);
        map.put('t', 17);
        map.put('u', 18);
        map.put('w', 19);
        map.put('y', 20);

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];

        for(int i = 0; i < N; i++){
            String input = br.readLine();
            input = input.replaceAll("ng", "c");
            arr[i] = input;
        }

        Arrays.sort(arr, ((o1, o2) -> {
            for(int i = 0; i < Math.min(o1.length(), o2.length()); i++){
                int o1Num = map.get(o1.charAt(i));
                int o2Num = map.get(o2.charAt(i));
                if(o1Num != o2Num){
                    return o1Num - o2Num;
                }
            }
            return o1.length() - o2.length();
        }));

        for(String s : arr){
            s = s.replaceAll("c", "ng");
            sb.append(s).append("\n");
        }

        System.out.println(sb.toString());
    }
}
