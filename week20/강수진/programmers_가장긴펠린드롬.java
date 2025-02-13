class Solution {

    static int max;

    public int solution(String s) {
        int l = s.length();
        max = 0;
        for(int i = 0; i < l; i++){
            palindrome(s, i, l);
        }

        return max;
    }
    static void palindrome(String s, int mid, int l){
        int start = mid-1;
        int end = mid+1;
        while(true){
            if(start < 0 || end >= l) {
                max = Math.max(max, end-start-1);
                break;
            }
            if(s.charAt(start) == s.charAt(end)) {
                start--;
                end++;
            }
            else{
                max = Math.max(max, end-start-1);
                break;
            }
        }

        start = mid;
        end = mid+1;
        while(true){
            if(start < 0 || end >= l) {
                max = Math.max(max, end-start-1);
                break;
            }
            if(s.charAt(start) == s.charAt(end)) {
                start--;
                end++;
            }
            else{
                max = Math.max(max, end-start-1);
                break;
            }
        }
    }
}