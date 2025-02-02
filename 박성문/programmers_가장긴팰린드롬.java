class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                int left = i;
                int right = j;
                if(j-i+1 <= answer){
                    continue;
                }
                boolean no = false;
                while(left < right){
                    
                    if(s.charAt(left++) != s.charAt(right--)){
                        no = true;
                        break;
                    }
                }
                if(!no){
                    answer =Math.max(answer, j-i+1);
                }
            }
        }

        return answer;
    }
}
