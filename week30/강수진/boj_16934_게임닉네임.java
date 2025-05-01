import java.util.*;
import java.io.*;

class Main {
    static class Trie {
        Trie[] child;
        
        Trie() {
            child = new Trie[26];
        }
    }
    
    static Trie root = new Trie();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> cnt = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            
            if(cnt.containsKey(name)){
                cnt.put(name, cnt.get(name) + 1);
            }
            else{
                cnt.put(name, 1);
            }
            
            String alias = find(name);
            
            if (alias.isEmpty()) {
                int num = cnt.get(name);
                if (num == 1) {
                    alias = name;
                } else {
                    alias = name + num;
                }
            }
            insert(name);
            
            sb.append(alias).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void insert(String name) {
        for (int i = 1; i <= name.length(); i++) {
        Trie now = root;
        for (int j = 0; j < i; j++) {
            int idx = name.charAt(j) - 'a';
            if (now.child[idx] == null) {
                now.child[idx] = new Trie();
            }
            now = now.child[idx];
            }
        }
    }
    
    static String find(String name) {
        for (int len = 1; len <= name.length(); len++) {

            Trie now = root;
            boolean found = true;
        
            for (int i = 0; i < len; i++) {
                int idx = name.charAt(i) - 'a';
                if (now.child[idx] == null) {
                    found = false;
                    break;
                }
                now = now.child[idx];
            }

            if (!found) {
                return name.substring(0, len);
            }
        }
        return "";
    }
}
