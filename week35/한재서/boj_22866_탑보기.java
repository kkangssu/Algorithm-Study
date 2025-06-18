import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static class Building{
        int index;
        int height;
     
        public Building(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
 
        Stack<Building> stack = new Stack<>();
        String[] s = br.readLine().split(" ");

        Building[] arr = new Building[N + 1];
        int[][] indexAndGap = new int[N + 1][2];
        int[] cnt = new int[N + 1];
        for(int i=1; i <= N; i++){
            arr[i] = new Building(i, Integer.parseInt(s[i - 1]));
            Arrays.fill(indexAndGap[i],100001);
        }
 
        for(int i=1; i <= N; i++){
            while(!stack.isEmpty() && stack.peek().height <= arr[i].height){
                stack.pop();
            }
 
            cnt[i] += stack.size();
 
            if(!stack.isEmpty()){
                int gap = Math.abs(stack.peek().index - i);
                if(gap < indexAndGap[i][1]){
                    indexAndGap[i][0] = stack.peek().index;
                    indexAndGap[i][1] = gap;
                }
                else if(gap == indexAndGap[i][1] && stack.peek().index < indexAndGap[i][0]){
                    indexAndGap[i][0] = stack.peek().index;
                }
            }
 
            stack.push(arr[i]);
        }
 
        stack =new Stack<>();
        for(int i=N; i > 0; i--){
            while(!stack.isEmpty() && stack.peek().height <= arr[i].height){
                stack.pop();
            }
 
            cnt[i] +=stack.size();
 
            if(!stack.isEmpty()){
                int gap = Math.abs(stack.peek().index - i);
                if(gap < indexAndGap[i][1]){
                    indexAndGap[i][0] = stack.peek().index;
                    indexAndGap[i][1] = gap;
                }
                else if(gap == indexAndGap[i][1] && stack.peek().index < indexAndGap[i][0]){
                    indexAndGap[i][0] = stack.peek().index;
                }
            }
            stack.push(arr[i]);
        }
 
        StringBuilder sb = new StringBuilder();
        for(int i=1; i <= N; i++){
            if(cnt[i] == 0){
                sb.append(0).append("\n");
            } else{
                sb.append(cnt[i]).append(" ").append(indexAndGap[i][0]).append("\n");
            }
        }
        
        System.out.println(sb.toString());
    }
}
