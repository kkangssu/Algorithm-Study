import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {
    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        public Node() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String str) {
            Node node = this.root;

            for(int i=0; i < str.length(); i++) {
                char c = str.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            node.isEnd = true;
        }

        public boolean search(String str) {
            Node node = this.root;

            for(int i=0; i < str.length(); i++) {
                char c = str.charAt(i);

                if(node.child.containsKey(c)) {
                    node = node.child.get(c);
                } else {
                    return false;
                }
            }

            return !node.child.isEmpty();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        root: for(int t=0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] numbers = new String[N];
            Trie telephoneBook = new Trie();

            for(int i=0; i < N; i++) {
                numbers[i] = br.readLine();
                telephoneBook.insert(numbers[i]);
            }

            Arrays.sort(numbers);
            for(int i=0; i < N; i++) {
                if(telephoneBook.search(numbers[i])) {
                    sb.append("NO").append("\n");
                    continue root;
                }
            }

            sb.append("YES").append("\n");
        }

        System.out.println(sb.toString());
    }
}
