package cn.huaming.al;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部分和
 */
public class Nearest {

    public static void main(String[] args) {
        for (int n = 0; n <= 42; n++) {
            String numsStr = "792,422,490.5,490.5,937.4,568.67,853.01,490.5,838.89,297.4,1428.91,1768.42,41.22,1610.12,44.64,751.51,126.48,43.2,727.2,122.4,0.28,3.88,3.88";
            String[] numStrs = numsStr.split(",");

            List<BigDecimal> array =
                    Arrays.stream(numStrs)
                            .map(num -> BigDecimal.valueOf(Double.parseDouble(num)))
                            .collect(Collectors.toList());
//            List<BigDecimal> array = collect.stream().sorted(BigDecimal::compareTo).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            double a = n*6.55;
//            BigDecimal targetSum = BigDecimal.valueOf(10000.97).subtract(BigDecimal.valueOf(7499.61));
            BigDecimal targetSum = BigDecimal.valueOf(10000.97).add(BigDecimal.valueOf(a));
            int[] nums = new int[array.size()];
            if (dfs(0, BigDecimal.valueOf(0), array, targetSum, nums)) {
                System.out.println("YES");
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == 1) {
                        System.out.print(array.get(i) + " ");
                    }
                }
                System.out.println("n="+n);
                break;
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * 思路:通过深度搜索算法穷举该数组所有数字和的可能
     *
     * @param deep
     * @param sum
     * @param array
     * @param targetSum
     * @return
     */
    public static boolean dfs(
            int deep,
            BigDecimal sum,
            List<BigDecimal> array,
            BigDecimal targetSum,
            int[] nums) {
        System.out.println("deep=" + deep + ",sum=" + sum);

        // 当deep==数组长度时说明此时已经到底
        if (deep == array.size()) {
            return sum.compareTo(targetSum) == 0;
        }

    /*
     深度未到底，此时两种情况 1.sum不加下一个数字 2.sum加下一个数字 再分别递归，通过这种方式可以穷举出所有可能的和
     假设一次dfs的T(n)=O(1)，因为总共有2^n次方种组成和的方式 则该算法最终的时间复杂度是T(n)=O(2^n)
    */
        if (dfs(deep + 1, sum, array, targetSum, nums)) {
            nums[deep] = 0;
            return true;
        }

        // 再判断+的一方
        if (dfs(deep + 1, sum.add(array.get(deep)), array, targetSum, nums)) {
            nums[deep] = 1;
            return true;
        }

        return false;
    }
}
