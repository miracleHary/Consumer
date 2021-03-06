package com.trade.controller;

import com.trade.config.TreeNode;
import io.swagger.models.auth.In;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmsController {
    /**
     * 场景描述：
     * 现在有一个有序且不重复的数组 arr ，我们需要用二分法去找到我们的target值
     * 算法分析：
     * 1.我们的函数需要有两个形参，arr[]和 target，
     * 2.mid值要随着数组二分后进行变化,例如 长度为 9 的数组二分以后mid下标为 4 ，假设我们的值比中间下标的值小,那么将会放在mid的左边，这时候算法需要对mid的下标进行重新计算,得出新下标为2,依次类推找到我们的target。
     * 3.循环体，用while作为循环体
     **/
    public static Integer binarySearch(int arr[], int target) {
        //我们的low值为初始值,high值为初始长度为数组总长度
        int low = 0;
        int high = arr.length - 1;
        System.out.println("开始前" + new Timestamp(System.currentTimeMillis()));
        while (low <= high) {

            //mid值将数组二分
            int mid = low + (high - low) / 2;

            //分别比对target和arr[mid]的大小,target大则在右半边,反之则在左半边,挖除mid的值;
            if (target < arr[mid]) {

                //下标最大值为当前mid的前一位
                high = mid - 1;

            } else if (target > arr[mid]) {

                //下标最大值为当前mid的后一位
                low = mid + 1;

            } else {

                //当 target == arr[mid]
                System.out.println("开始后" + new Timestamp(System.currentTimeMillis()));
                return mid;

            }
        }

        return -1;

    }

    private  int pivotIndex(int startIndex,int lastIndex,int[] arr){
        //选举基准值，默认为数组的第一个索引下标
        int pivot = arr[startIndex];
        int left  = startIndex;
        int right = lastIndex;

        //开始循环
        while (left != right){
            //数组开始查询，先从后往前查，arr[right] >= pivot 的时候 指针right向左移动一位， arr[right] < pivot的时候,right指针保持不动,切换到left指针
            while (left < right && arr[right]>= pivot){
                right--;
            }
            //当arr[right] < pivot的时候,切换到left指针,当left指针的值小于pivot的时候，left指针向右移动一位,反之则left和right的值进行交换
            while (left < right && arr[left] <= pivot){
                left++;
            }
            if(left < right){
                int temp   = arr[left];
                arr[left]  = arr[right];
                arr[right] = temp;
            }
        }
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        return left;
    }

    /**递归快排
     * @param startIndex 初始下标
     * @param lastIndex  结尾下标
     * @param arr        数组
     */
    public void quickSort(int startIndex,int lastIndex,int[] arr){
        if(startIndex >= lastIndex){
            return;
        }

        int pivotIndex = pivotIndex(startIndex, lastIndex, arr);
        quickSort(startIndex,pivotIndex-1,arr);
        quickSort(pivotIndex+1,lastIndex,arr);
    }
    /**
     * 1.两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     * 2.给出两个整数 x 和 y，计算它们之间的汉明距离。
     * 注意：
     *   0 ≤ x, y < 2^31.
     * 示例:
     *   输入: x = 1, y = 4
     *   输出: 2
     *   解释:
     *      1   (0 0 0 1)
     *      4   (0 1 0 0)
     *             ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     */
     public int hammingDistance(int x, int y) {
          int distance = Integer.bitCount(x^y);
          return distance;
     }
    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
         //如果root1的val节点为null,则返回root2的val
         if(root1 == null){
             return root2;
         }
         //反之则返回root1的val
         if(root2 == null){
             return root1;
         }
         //确立新的根节点,并且根节点分布移动至左右节点
         TreeNode merged = new TreeNode(root1.val + root2.val);
         merged.left     = mergeTrees(root1.left,root2.left);
         merged.right    = mergeTrees(root1.right,root2.right);

         return merged;
    }

    /**
     * 翻转二叉树
     *
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     */
    public TreeNode invertTree(TreeNode root) {
        //设置临时变量
        TreeNode newNode = new TreeNode(root.val);

        if(root.left.val != root.right.val){
            newNode.right = invertTree(root.left);
            newNode.left  = invertTree(root.right);
        }


        return newNode;
    }
}