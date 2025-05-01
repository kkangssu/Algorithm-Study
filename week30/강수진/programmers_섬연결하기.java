import java.util.*;
import java.io.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int result = 0;
        int cnt = 0;
        
        for (int[] edge : costs) {
            int island1 = edge[0];
            int island2 = edge[1];
            int cost = edge[2];
            
            if (findParent(island1) != findParent(island2)) {
                union(island1, island2);
                result += cost;
                cnt++;
                
                if (cnt == n - 1) {
                    break;
                }
            }
        }
        
        return result;
    }
    
    static int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }  
}
