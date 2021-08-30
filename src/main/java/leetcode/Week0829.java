package leetcode;

import java.util.Arrays;

/**
 * @author ：hc
 * @date ：Created in 2021/8/29 10:40
 * @modified By：8月29日周赛
 */
public class Week0829 {
    /**
     * 1.easy
     * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
     *
     * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     *
     * 返回可能的 最小差值 。
     *
     * 输入：nums = [90], k = 1
     * 输出：0
     * 解释：选出 1 名学生的分数，仅有 1 种方法：
     * - [90] 最高分和最低分之间的差值是 90 - 90 = 0
     * 可能的最小差值是 0
     *
     * 输入：nums = [9,4,1,7], k = 2
     * 输出：2
     * 解释：选出 2 名学生的分数，有 6 种方法：
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
     * - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
     * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
     * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
     * 可能的最小差值是 2
     * @param nums nums
     * @param k k
     * @return 可能的最小差值
     */
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        // 排序
        Arrays.sort(nums);
        // 选以k-2为间隔的两个index，慢慢后移找最小值
        int minIndex = 0;
        int maxIndex = minIndex + k - 1;
        // 其他情况往后找min就行
        int resMin = nums[maxIndex] - nums[minIndex];
        while (maxIndex < nums.length) {
            int tmp = nums[maxIndex] - nums[minIndex];
            if (tmp < resMin) {
                resMin = tmp;
            }
            minIndex++;
            maxIndex++;
        }
        return resMin;
    }


    public String kthLargestNumber(String[] nums, int k) {
        long[] numsLong = new long[nums.length];
        // 字符串转数字
        for (int i = 0; i < nums.length; i++) {
            numsLong[i] = Long.parseLong(nums[i]);
        }
        Arrays.sort(numsLong);
        // 第k大，就是取index为length - k对应的值
        return String.valueOf(numsLong[nums.length - k]);
    }

    public static void main(String[] args) {
        Week0829 week0829 = new Week0829();
//        int[] nums = new int[]{9,4,1,7};
//        int i = week0829.minimumDifference(nums, 3);
//        System.out.println(i);

        String[] nums = new String[]{"7","6","3","10"};
        String s = week0829.kthLargestNumber(nums, 1);
        System.out.println(s);
    }
}
