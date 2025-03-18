import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    static int[] discount, emoticon;
    static int[][] user;
    static int maxPlus, maxSale;
    
    static int[] solution(int[][] users, int[] emoticons) {
        discount = new int[emoticons.length];
        user = users;
        emoticon = emoticons;
        
        back(0);
        int[] result = {maxPlus, maxSale};
        return result;
    }

    static void back(int idx){
        if(idx == emoticon.length){
            int sale = 0;
            int plus = 0;
            for(int i = 0; i < user.length; i++){
                int pay = 0;
                for(int j = 0; j < emoticon.length; j++){
                    if(user[i][0] <= discount[j]){
                        pay += emoticon[j]/100 * (100-discount[j]);
                    }
                }
                //System.out.println("pay: " + pay);
                if(pay >= user[i][1]) plus++;
                else sale += pay;
            }

            //System.out.println("sale: " + sale + " plus: " + plus);

            if(plus > maxPlus){
                maxPlus = plus;
                maxSale = sale;
            }
            else if(plus == maxPlus) maxSale = Math.max(maxSale, sale);
            
            return;
        }
        for(int i = 1; i <= 4; i++){
            discount[idx] = i*10;
            back(idx+1);
        }
    }
}
