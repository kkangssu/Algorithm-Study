import java.util.*;
import java.io.*;
public class Main {
    // Trie의 각 노드를 표현하는 클래스
    static class TrieNode {
        Map<Character, TrieNode> children; // 자식 노드들을 저장하는 맵 (문자 -> 노드)
        public TrieNode() {
            children = new HashMap<>(); // 자식 노드들을 저장할 해시맵 초기화
        }
        private boolean isEndOfWord; // 단어의 끝을 표시하는 플래그
        int childCount = 0; // 분기점의 개수를 세는 변수
        
        // 자식 노드 맵을 반환하는 getter
        public Map<Character, TrieNode> getChildren() {
            return children;
        }
        
        // 자식 노드 맵을 설정하는 setter
        public void setChildren(Map<Character, TrieNode> children) {
            this.children = children;
        }
        
        // 단어의 끝인지 확인하는 getter
        public boolean isEndOfWord() {
            return isEndOfWord;
        }
        
        // 단어의 끝 여부를 설정하는 setter
        public void setEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }
    }
    
    // Trie 자료구조를 구현한 클래스
    static class Trie {
        private TrieNode root; // 루트 노드
        
        // 생성자: 루트 노드 초기화
        Trie() {
            this.root = new TrieNode();
        }
        
        // 단어를 Trie에 삽입하고, 유일한 접두사 반환
        String insert(String word) {
            TrieNode currentNode = root; // 현재 노드를 루트로 설정
            int uniquePrefixLength = word.length(); // 기본값으로 단어 전체 길이 설정
            boolean foundNewBranch = false; // 새로운 분기점 발견 여부
            
            // 단어의 각 문자를 순회하며 Trie에 삽입
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                // 현재 문자에 해당하는 자식 노드가 없으면 새로 생성
                if (!currentNode.children.containsKey(currentChar)) {
                    currentNode.children.put(currentChar, new TrieNode());
                    // 첫 번째 새 분기점에서 유일한 접두사 길이 결정
                    if (!foundNewBranch) {
                        uniquePrefixLength = i + 1;
                        foundNewBranch = true;
                        currentNode.childCount++;
                    }
                }
                // 다음 노드로 이동
                currentNode = currentNode.children.get(currentChar);
            }
            
            // 단어가 처음 등장하는 경우
            if (!wordOccurrences.containsKey(word)) {
                wordOccurrences.put(word, 1); // 단어 출현 횟수 1로 설정
                return word.substring(0, uniquePrefixLength); // 유일한 접두사 반환
            } else {
                // 이미 등장한 단어인 경우
                wordOccurrences.put(word, wordOccurrences.get(word) + 1); // 출현 횟수 증가
                // 유일한 접두사에 출현 횟수를 붙여서 반환
                return word.substring(0, uniquePrefixLength).concat(Integer.toString(wordOccurrences.get(word)));
            }
        }
    }
    
    // 각 단어의 출현 횟수를 저장하는 맵
    static Map<String, Integer> wordOccurrences;
  
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numWords = Integer.parseInt(br.readLine()); // 입력받을 단어의 개수
        Trie trie = new Trie(); // Trie 자료구조 초기화
        wordOccurrences = new HashMap<>(); // 단어 출현 횟수 맵 초기화
        
        // 각 단어를 입력받아 Trie에 삽입하고 결과 출력
        for (int i = 0; i < numWords; i++) {
            String inputWord = br.readLine(); // 단어 입력
            System.out.println(trie.insert(inputWord)); // Trie에 삽입하고 결과 출력
        }
    }
}
