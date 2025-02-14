import java.util.*;

class Solution {
    
    static boolean check(int r, int c){
        return r >=5 || r <0 || c >=5 || c < 0;
        }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};
        Queue<int[]> q = new LinkedList<>();
        loop: for(int t = 0; t < places.length; t++){
            
            char[][] map = new char[5][5];
            String[] s = places[t];
            for(int i = 0; i < 5; i++){
               map[i] = s[i].toCharArray();
 
            }
            
            
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    
                    if(map[i][j]=='P'){
                        boolean[][] visited = new boolean[5][5];
                        
                        q.add(new int[] {i,j});
                        visited[i][j] = true;
                        
                        while(!q.isEmpty()) {
                            
                            int[] idx = q.poll();
                            int r = idx[0];
                            int c = idx[1];
                            
                            for(int d = 0; d < 4; d++){
                                int nr = r + dr[d];
                                int nc = c + dc[d];
                                
                                if(check(nr,nc) || visited[nr][nc]){
                                    continue;
                                }
                                if(Math.abs(nr-i) + Math.abs(nc-j) >=3 )
                                    continue;
                                
                                if(Math.abs(nr-i)+Math.abs(nc-j) <=2 &&  
                                   map[nr][nc]!='P'){
                                    visited[nr][nc] = true;
                                    q.add(new int[] {nr,nc});
                                    continue;
                                }
                                
                                if(Math.abs(nr-i)+Math.abs(nc-j) == 1 &&  
                                   map[nr][nc]=='P'){
                                    q.clear();
                                   
                                     answer[t] = 0;
                                     continue loop;
                                }
                                
                                if(Math.abs(nr-i)+Math.abs(nc-j) == 2 &&  
                                   map[nr][nc]=='P'){
                                    
                                    
                                    if(nr < i && nc < j) {
                                        
                                        if(map[i-1][j] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                       if(map[i][j-1] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }
                                    if(nr < i && nc== j) {
                                        
                                        if(map[i-1][j] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }                                    
                                   if(nr < i && nc > j) {
                                        
                                        if(map[i-1][j] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                       
                                        if(map[i][j+1] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }         
                                    
                                   if(nr == i && nc > j) {
                                        
                                        if(map[i][j+1] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }                                                                                  
                                   if(nr > i && nc > j) {
                                        
                                        if(map[i][j+1] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                       
                                        if(map[i+1][j] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }   
                                    
                                   if(nr > i && nc == j) {
                                        
                                        if(map[i+1][j] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                    }                                              

                                   if(nr > i && nc < j) {
                                        
                                        if(map[i+1][j] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                       
                                        if(map[i][j-1] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }        
                                    
                                   if(nr == i && nc < j) {
                                        
                                        if(map[i][j-1] != 'X'){
                                            q.clear();
                                            answer[t] = 0;
                                            continue loop;
                                        }
                                        
                                    }                                         
                                    
                                    
                                    
                                 
                               
                                }
                                q.add(new int[] {nr,nc} );
                                visited[nr][nc] = true;
                                     
                                }
                                
                                
                            }
                            
                            
                        }
                        
                        
                    }
                    
                }
            
             
                answer[t] = 1;
          
           
            }
            
            return answer; 
    }
        
        
    }

