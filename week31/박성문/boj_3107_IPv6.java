package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//The main method must be in a class named "Main".
class Main {
	static String[] map;
	
 public static void main(String[] args) throws Exception {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
     StringTokenizer st = new StringTokenizer(br.readLine());
     map = st.nextToken().split(":");
     int size = map.length;
     int ok = 0;
     for(String x: map) {
    	 if(x.length()!=0) {
    		 ok++;
    	 }
     }

     
     StringBuilder sb = new StringBuilder();
     boolean no = false;
     for(int i = 0; i < size; i++) {
    	 if(map[i].length()!=0) {
    		 
    		 int tmpSize = map[i].length();
    		 
    		 for(int j = 0; j < 4- tmpSize;j++) {
    			 sb.append(0);
    		 }
    		 for(int j = 0; j < tmpSize; j++) {
    			 sb.append(map[i].charAt(j));
    		 }
        	 if(sb.toString().length()!=39)
        		 sb.append(":");
    		 
    	 } else {
    		 if(!no) {
        		 for(int j = 0; j < 8-ok; j++) {
            		 for(int k = 0; k < 4;k++) {
            			 sb.append(0);
            		 }
            		 sb.append(":");
        		 }
        		 no = true;
    		 }

    	 }

     }
     
     if(sb.toString().length() != 39) {
    	 int tmpSize = 8-size;
		 for(int j = 0; j < tmpSize; j++) {
    		 for(int k = 0; k < 4;k++) {
    			 sb.append(0);
    		 }
    		 if(j!=tmpSize-1)
    			 sb.append(":");
		 }
     }
     
     System.out.println(sb.toString());
 }

}
