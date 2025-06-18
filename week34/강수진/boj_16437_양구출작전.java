import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Island {
        long nums;
        char animal;
        int connect;
        
        Island(char animal, long nums, int connect) {
            this.nums = nums;
            this.animal = animal;
            this.connect = connect;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n+1];
        Island[] islands = new Island[n+1];
        islands[1] = new Island('S', 0, 0);
        for (int i = 2; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            char animal = st.nextToken().charAt(0);
            long nums = Long.parseLong(st.nextToken());
            int connect = Integer.parseInt(st.nextToken());
            
            islands[i] = new Island(animal, nums, connect);
            num[connect]++;
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 2; i < n+1; i++) {
            if(num[i] == 0) que.offer(i);
        }

        while(!que.isEmpty()){
            int current = que.poll();
            Island now = islands[current];
            if(current == 1) break;

            Island next = islands[now.connect];
            if(now.animal == 'W') {
                if(--num[now.connect] == 0) que.offer(now.connect);
                continue;
            }
            
            if(next.animal == 'W'){
                long m = now.nums - next.nums;
                if(m < 0) next.nums = m*(-1);
                else{
                    next.animal = 'S';
                    next.nums = m;
                }
            }
            else{
                long m = now.nums + next.nums;
                next.nums = m;
            }
            if(--num[now.connect] == 0) que.offer(now.connect);
        }

        System.out.println(islands[1].nums);
    }
}
