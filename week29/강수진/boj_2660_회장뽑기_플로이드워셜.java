import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = 100;  // 회원 수 최대 50명이니 dp배열이 100을 넘을 일은 없음
            }
        }

      // 직접 친구인 경우 입력 받음
        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1) break;
            dp[a][b] = 1;
            dp[b][a] = 1;
        }

      // 플로이드 워셜
      // 회원 l과 회원 r 사이 경유하는 회원 m
      // 회원 l과 회원 r 사이의 점수가 회원 m을 통해 연결되는 점수보다 크면 dp 갱신 
        for(int m = 1; m < n+1; m++){
            for(int l = 1; l < n+1; l++){
                for(int r = 1; r < n+1; r++){
                    dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m][r]);
                }
            }
        }

      // 각 회원의 최대 점수 score[]에 저장
        int[] score = new int[n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j) {
                    score[i] = Math.max(score[i], dp[i][j]);
                }
            }
        }

      // 회장이 될 수 있는 점수 min
        int min = 100;
        for(int i = 1; i < n+1; i++) {
            min = Math.min(min, score[i]);
        }
      // 회장 찾기
        int cnt = 0;
        for(int i = 1; i < n+1; i++) {
            if(score[i] == min) {
                sb.append(i).append(" ");
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
        System.out.println(sb);
    }
}
