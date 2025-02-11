package week21.강수진;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15661_링크와스타트 {

    static int N, min;
    static int[][] ability;
    static boolean[] team;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        ability = new int[N][N];
        team = new boolean[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        pickTeam(0, 0);
        System.out.println(min);
    }

    static void pickTeam(int idx, int linkNum){
        if(linkNum >= 1){
            abilityCompare(linkNum);
        }
        for(int i = idx; i < N; i++){
            team[i] = true;
            pickTeam(i+1, linkNum+1);
            team[i] = false;
        }
    }

    static void abilityCompare(int linkNum){
        int[] link = new int[linkNum];
        int[] start = new int[N-linkNum];
        int idxLink = 0, idxStart = 0;
        for(int i = 0; i < N; i++){
            if(team[i]) link[idxLink++] = i;
            else start[idxStart++] = i;
        }

        int diff = 0;
        for(int i = 0; i < linkNum-1; i++){
            for(int j = i+1; j < linkNum; j++){
                diff += ability[link[i]][link[j]] + ability[link[j]][link[i]];
            }
        }
        for(int i = 0; i < N-linkNum-1; i++){
            for(int j = i+1; j < N-linkNum; j++){
                diff -= ability[start[i]][start[j]] + ability[start[j]][start[i]];
            }
        }

        min = Math.min(min, Math.abs(diff));
    }
}
