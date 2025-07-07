import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_5052_전화번호목록 {

    static class TrieNode {
        TrieNode[] children = new TrieNode[10];
        boolean isEnd = false;
    }
    static class Trie {
        TrieNode root;

        Trie(){
            root = new TrieNode();
        }

        // true면 일관성 있음, false면 일관성 없음
        boolean insert(String number) {
            TrieNode now = root;
            for (char ch : number.toCharArray()) {
                if(ch < '0' || ch > '9') continue;
                int idx = ch - '0';
                if (now.children[idx] == null) {
                    now.children[idx] = new TrieNode();
                }
                now = now.children[idx];

                // number의 접두어인 번호가 저장되어 있는 경우
                if (now.isEnd) return false;
            }

            // number가 저장되어 있는 번호의 접두어인 경우
            for (int i = 0; i < 10; i++) {
                if (now.children[i] != null) return false;
            }

            now.isEnd = true;
            return true;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int T = 0; T < t; T++){
            int n = Integer.parseInt(br.readLine());
            boolean isConsistent = true;
            Trie numbers = new Trie();
            for(int i = 0; i < n; i++){
                String number = br.readLine();
                if(!isConsistent) continue;
                if(!numbers.insert(number)){
                    isConsistent = false;
                }
            }
            if(isConsistent) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
