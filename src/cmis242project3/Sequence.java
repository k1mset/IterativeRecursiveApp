/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmis242project3;

/**
 * File: Sequence.java
 * Date: 4/19/2018
 * Author: Dillan Cobb
 * Purpose: Utility class for the Gui.java that holds methods for calculating 
 * the n-th terms and returning the efficiency of the methods used.
 */

public final class Sequence {
    // Varible to be used for the efficiency counter
    static int efficicency;
    
    // public computeIterative method to compute the iterative sequence of 
    // numbers
    public static int computeIterative(int num) {
        int prevNum = 0;
        int secPrevNum = 0;
        
        for (int i = num; i > 0; i--) {
            num = (prevNum * 2) + secPrevNum;
            secPrevNum = prevNum;
            
            if (prevNum == 0) {
                prevNum = 1;
                num = 1;
            }
            else 
                prevNum = num;
            
            efficicency++;
        }
        
        return num;
    }
    
    // pulbic computeRecursive method to compute the recursive sequence of
    // numbers
    public static int computeRecursive(int num, int cnt, int prevNum, 
            int secPrevNum) {
        if (cnt == 0)
            return num;
        
        cnt--;
        num = (prevNum * 2) + secPrevNum;
        secPrevNum = prevNum;
        
        if (prevNum == 0) {
            prevNum = 1;
            num = 1;
        }
        else 
            prevNum = num;
        
        efficicency++;
        return computeRecursive(num, cnt, prevNum, secPrevNum);
    }
    
    // public getEfficiency returns the number of times the computeIterative or
    // computeRecursive method were ran
    public static int getEfficiency() {
        int result = efficicency;
        efficicency = 0;
        return result;
    }
}
