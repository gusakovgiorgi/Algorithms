package com.example.algorithms.sorting.mergeSort;

public class MergeSort {

    public static int[] sort(int[] arr){
        if (arr==null) throw new NullPointerException("array is null");
        if (arr.length==0 || arr.length==1) return arr;
        return sort(arr,0,arr.length);
    }

    private static int[] sort(int[] arr, int start, int end){
        // If we have one element return it
        int length=end-start;
        if (length==1){
            return new int[]{arr[start]};
        }

        int middle=length/2;
        int[] leftArr=sort(arr,start,start+middle);
        int[] rightArr=sort(arr,start+middle,end);

        return merge(leftArr,rightArr);
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int[] mergedArr=new  int[leftArr.length+rightArr.length];
        int i=0;
        int j=0;
        for (int k=0;k<mergedArr.length;k++){
            if (leftArr[i]<rightArr[j]){
                mergedArr[k]=leftArr[i];
                i++;
                if (i==leftArr.length){
                    System.arraycopy(rightArr,j,mergedArr,k+1,rightArr.length-j);
                    break;
                }
            }else {
                mergedArr[k]=rightArr[j];
                j++;
                if (j==rightArr.length){
                    System.arraycopy(leftArr,i,mergedArr,k+1,leftArr.length-i);
                    break;
                }
            }
        }
        return mergedArr;
    }
}
