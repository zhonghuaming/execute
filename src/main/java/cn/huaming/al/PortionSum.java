package cn.huaming.al;

/**
 * 给定参数a1,a2,...,an,判断是否可以从中选出若干数，使得他们的和恰好为k
 * @author haofan.whf
 * @version $Id: PortionSum.java, v 0.1 2018年06月07日 下午5:17 haofan.whf Exp $
 */
public class PortionSum {

    public static void main(String args[]){
        int[] array = new int[]{1,2,3,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8,8,1,2,3,8};
        int targetSum = 11111111;
        PortionSum ps = new PortionSum();
        if(ps.dfs(0,0, array, targetSum)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    /**
     * 思路:通过深度搜索算法穷举该数组所有数字和的可能
     * @param deep
     * @param sum
     * @param array
     * @param targetSum
     * @return
     */
    private boolean dfs(int deep, int sum, int[] array, int targetSum){
        //当deep==数组长度时说明此时已经到底
        if(deep == array.length){
            return sum == targetSum;
        }

        /**
         * 深度未到底，此时两种情况
         * 1.sum不加下一个数字
         * 2.sum加下一个数字
         * 再分别递归，通过这种方式可以穷举出所有可能的和
         * 假设一次dfs的T(n)=O(1)，因为总共有2^n次方种组成和的方式
         * 则该算法最终的时间复杂度是T(n)=O(2^n)
         */
        if(dfs(deep + 1, sum, array, targetSum)){
            return true;
        }

        //再判断+的一方
        if(dfs(deep + 1, sum + array[deep], array, targetSum)){
            return true;
        }

        return false;
    }

}