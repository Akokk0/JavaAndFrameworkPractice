package com.akokko.practise;

/*
* 一个有一些 0 和 1 组成的数组，最多可以将K个数从 1 变成 0 。
返回仅包含 0 的最长（连续）子数组的长度。
Sample 1：
输入：A = [0,0,0,1,1,1,0,0,0,0,1], K = 2
输出：6
解释： [0,0,0,1,1,(0),0,0,0,0,(0)] 小括号括起的就是改变的

Sample 2：
输入：A = [1,1,0,0,1,1,0,0,0,1,0,0,1,1,1,0,0,0,0], K = 3
输出：10
解释：
[1,1,0,0,(0),(0),0,0,0,(0),0,0,1,1,1,0,0,0,0] 小括号括起的就是改变的

Notes：
1. 程序里面一个类要实现这个方法，用main程序测试
 public int longestZeros(int[] A, int K) {}
2. 不能使用任何java.util.*; java.lang.*; 以外的包
* */

public class TestDemo {

    private static int max = 0;

    public static void main(String[] args) {
        int[] A = {0,0,0,1,1,1,0,0,0,0,1};
        int K = 2;
        process(A, 0, K);
        System.out.println(max);
    }


    public static void process(int[] arr,int index,int k){
        if(k == 0 || index == arr.length){//如果已经将k个0变成1了，或者到达了字符串末尾了
            int tmp = 0;//开始统计计数连续1的个数
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == 1){
                    tmp++;
                    max = Math.max(max, tmp);
                }
                else{//一旦遇到0，之前的计数清零
                    tmp = 0;
                }
            }
            return ;//结束
        }

        //回溯的思想
        for(int j = index; j < arr.length; j++){
            if(arr[j] == 0){
                arr[j] = 1;
                process(arr,index + 1,k - 1);
                arr[j] = 0;
            }
        }

    }

}
