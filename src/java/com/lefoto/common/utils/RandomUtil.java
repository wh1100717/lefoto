/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.util.Random;

/**
 *
 * @author Eric
 */
public class RandomUtil {

    static public int[] getUnDuplicateNums(int size, int start, int end) {
        int[] t1 = new int[size];
        /* 
         * 创建一个Random对像r，r.nextInt()这个方法每次都会产生一个不同的数 
         * 此处也可用int num=(int)( Math.random()*100) 的方法替代 
         */
        Random random = new Random();
        int cycleFlag = 0;//cycleFlag:用来控制产生不同的随机数  
        while (cycleFlag < size) {//循环产生随机但是不同的数  
            /* 
             * tempNum:临时接收产生的随机数 
             *         因为每次r.nextInt()都会得到不同的随机数， 
             *         所以要用此变量接收，以便在后面使用 
             * ifExistSecond:新产生的随机数是否已经在数组中存在， 
             *         如果如果存在，则为1，不存在则为0 
             */
            int tempNum = 0;
            boolean isExist = false;


            tempNum = random.nextInt(end - start) + start;//接收新产生的随机数  
            /* 
             * 判断是否是第一次产生随机数，如果是， 
             * 则直接把这第一个数放到数组的头部 
             */
            if (cycleFlag == 0) {
                t1[cycleFlag] = tempNum;
                ++cycleFlag;//产生下一个随机数  
            } else {
                /* 
                 * 如果数组中已经有元素，则需要把产生的新随机数 
                 * 与数组中的每个进行比较 
                 */
                for (int m = 0; m < cycleFlag; m++) {
                    if (tempNum == t1[m]) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {//如果新生的随机在数组中不存在，则添加到数组中  
                    //System.out.println(tempNum);  
                    t1[cycleFlag] = tempNum;
                    //System.out.println("cycleFlag:"+cycleFlag);  
                    ++cycleFlag;//产生下一个随机数  
                }
            }
        }
        t1 = asc_sort(t1);
        return t1;
    }

    //正需
    static public int[] asc_sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
