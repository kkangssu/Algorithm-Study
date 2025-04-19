import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

class Main {
    static int N, M, fuel;
    static int[][][] field;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] myLoca;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        field = new int[N][N][3];
        myLoca = new int[2];
        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) field[i][j][2] = -1;
            }
        }
        st = new StringTokenizer(br.readLine());
        myLoca[0] = Integer.parseInt(st.nextToken()) - 1;
        myLoca[1] = Integer.parseInt(st.nextToken()) - 1;
        for(int i=0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int starty = Integer.parseInt(st.nextToken()) - 1;
            int startx = Integer.parseInt(st.nextToken()) - 1;
            
            field[starty][startx][2]++;
            field[starty][startx][0] = Integer.parseInt(st.nextToken()) - 1;
            field[starty][startx][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        takePassenger(myLoca);
    }

    static void takePassenger(int[] arr) {
        if(field[arr[0]][arr[1]][2] > 0) {
            field[arr[0]][arr[1]][2] = 0;
            M--;
            toGoal(arr);
        } else {
            Queue<int[]> queue = new LinkedList<>();
            int[] nextPassenger = new int[2];
            nextPassenger[0] = N;
            nextPassenger[1] = N;
            boolean[][] visited = new boolean[N][N];
            queue.offer(arr);
            visited[arr[0]][arr[1]] = true;
            while(!queue.isEmpty()) {
                fuel--;
                int size = queue.size();
                for(int i=0; i < size; i++) {
                    int[] cur = queue.poll();
                    for(int j=0; j < 4; j++) {
                        int[] next = new int[2];
                        next[0] = cur[0] + dy[j];
                        next[1] = cur[1] + dx[j];
                        if(check(next) && !visited[next[0]][next[1]] && field[next[0]][next[1]][2] > -1) {
                            if(field[next[0]][next[1]][2] == 1) {
                                if(next[0] < nextPassenger[0]) {
                                    nextPassenger[0] = next[0];
                                    nextPassenger[1] = next[1];
                                } else if(next[0] == nextPassenger[0] && next[1] < nextPassenger[1]) {
                                    nextPassenger[0] = next[0];
                                    nextPassenger[1] = next[1];
                                }
                            }
                            visited[next[0]][next[1]] = true;
                            queue.offer(next);
                        }
                    }
                }
                if(fuel == 0) {
                    System.out.println(-1);
                    return;
                }
                if(nextPassenger[0] < N) {
                    field[nextPassenger[0]][nextPassenger[1]][2] = 0;
                    M--;
                    toGoal(nextPassenger);
                    return;
                }
            }
            System.out.println(-1);
        }
    }

    static void toGoal(int[] arr) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(arr);
        visited[arr[0]][arr[1]] = true;
        int time = 0;
        while(!queue.isEmpty()) {
            time++;
            fuel--;
            int size = queue.size();
            for(int i=0; i < size; i++) {
                int[] cur = queue.poll();
                for(int j=0; j < 4; j++) {
                    int[] next = new int[2];
                    next[0] = cur[0] + dy[j];
                    next[1] = cur[1] + dx[j];
                    if(check(next) && !visited[next[0]][next[1]] && field[next[0]][next[1]][2] > -1) {
                        if(next[0] == field[arr[0]][arr[1]][0] && next[1] == field[arr[0]][arr[1]][1]) {
                            fuel += time * 2;
                            if(M == 0) {
                                System.out.println(fuel);
                                return;
                            }
                            takePassenger(next);
                            return;
                        }
                        visited[next[0]][next[1]] = true;
                        queue.offer(next);
                    }
                }
            }
            if(fuel == 0) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(-1);
    }

    static boolean check(int[] arr) {
        return arr[0] >= 0 && arr[0] < N && arr[1] >= 0 && arr[1] < N;
    }
}
