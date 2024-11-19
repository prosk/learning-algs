package com.mycompany.yandex.interview.arrays;
public class ArrayReverser {

    public static void main(String[] args) {
        int[] myArr = new int[] {2, 5, -7, 3, 6, 8, 98};
        new ArrayReverser().reverse(myArr);

        for(int i = 0; i < myArr.length; i++) {
            System.out.print(myArr[i] + " ");
        }
        System.out.println();
    }

    public void reverse(int[] arr) {
        for(int i = 0, j = arr.length - 1; i < j; i++, j--) {
            swap(arr, i, j);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
