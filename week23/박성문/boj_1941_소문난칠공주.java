package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static char[][] map;
    static int answer;
    static int[] visited;
    static boolean[] visited2;
    static Queue<int[]> q;

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];

        for(int i = 0; i < 5; i++){
            map[i] = br.readLine().toCharArray();
        }
        

        answer = 0;
        visited = new int[7];
        q = new LinkedList<>();
        check(0,0,0,0);

        System.out.println(answer);
        
    }

    static void check(int cnt,int start, int s, int y){


        if(cnt >= 4 && y >= 4){
            return;
        }
       
        if(cnt==7) {
        	
        	visited2 = new boolean[7];
        	visited2[0] = true;
        	int count = 1;
        	q.add(new int[] {visited[0]/5, visited[0]%5});
        	
        	while(!q.isEmpty()) {
        		
        		int[] idx = q.poll();
        		
        		
        		for(int i = 0; i < 7; i++) {
        			if(visited2[i])
        				continue;
        			
        			if(Math.abs(visited[i]/5 - idx[0]) + Math.abs(visited[i]%5 - idx[1]) <= 1) {
        				visited2[i] = true;
        				count++;
        				q.add(new int[] {visited[i]/5, visited[i]%5});
        			}
        			
        			
        		}
        		
        		
        	}
        	
        	if(count==7)
        		answer++;
        	q.clear();
       
        	return;
	
        }

        
        for(int i = start; i < 25; i++) {
        	
        	visited[cnt] =i;
        	
        	if(map[i/5][i%5] == 'Y')
        		check(cnt+1,i+1, s, y+1);
        	else
        		check(cnt+1,i+1,s+1,y);
        	
        }
        
    }
    
}
