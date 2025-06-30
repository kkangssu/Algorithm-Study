import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_8983_사냥꾼 {

    static class Animal{
        int x, length;
        Animal(int x, int length){
            this.x = x;
            this.length = length;
        }
    }
    static int[] hunters;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        hunters = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            hunters[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(hunters);

        Animal[] animals = new Animal[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            animals[i] = new Animal(x, l-y);
        }

        int result = 0;
        for(int i = 0; i < n; i++){
            if(animals[i].length < 0) continue;
            if(hunt(animals[i].x, animals[i].length)) result++;
        }

        System.out.println(result);
    }

    public static boolean hunt(int x, int length){
        int left = 0;
        int right = m-1;

        while(left <= right){
            int mid = (left + right)/2;

            if(Math.abs(x - hunters[mid]) <= length){
                return true;
            }
            else if(hunters[mid] < x){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return false;
    }
}
