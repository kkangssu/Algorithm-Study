class Solution {
    public int solution(String s) {
        if (s.length() < 2) return s.length();
        
        char[] str = s.toCharArray();
        int N = str.length;
        int maxLength = 1;
        
        // 홀수 길이 팰린드롬 검사
        for (int i = 0; i < N; i++) {
            int left = i - 1;
            int right = i + 1;
            int length = 1;
            
            while (left >= 0 && right < N && str[left] == str[right]) {
                length += 2;
                left--;
                right++;
            }
            maxLength = Math.max(maxLength, length);
        }
        
        // 짝수 길이 팰린드롬 검사
        for (int i = 0; i < N - 1; i++) {
            if (str[i] == str[i + 1]) {
                int left = i - 1;
                int right = i + 2;
                int length = 2;
                
                while (left >= 0 && right < N && str[left] == str[right]) {
                    length += 2;
                    left--;
                    right++;
                }
                maxLength = Math.max(maxLength, length);
            }
        }
        
        return maxLength;
    }
}