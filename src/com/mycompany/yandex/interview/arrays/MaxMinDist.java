package com.mycompany.yandex.interview.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import static com.mycompany.yandex.interview.arrays.MaxMinDist.BuildingType.HOUSE;
import static com.mycompany.yandex.interview.arrays.MaxMinDist.BuildingType.SHOP;

public class MaxMinDist {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    private final static Random rnd = new Random();

    // чужое решение с Питона перевел на Джаву - оно не проходит все рандомные тесты
    int getMaxMinDistWithStack(int[] arr) {
        int ans = 0;
        int nShop = -arr.length;
        Stack<Integer> steck = new Stack<>();
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == HOUSE.value && i - nShop > ans) {
                steck.add(i);
            } else if (arr[i] == SHOP.value) {
                while (!steck.isEmpty()) {
                    int candidate = steck.pop();
                    if (i - candidate > ans) {
                        if (i - candidate >= candidate - nShop) {
                            ans = candidate - nShop;
                            steck.clear();
                        } else {
                            ans = i - candidate;
                        }
                    }
                }
                nShop = i;
            }
        }
        if (!steck.isEmpty()) {
            ans = steck.pop() - nShop;
        }
        return ans;
    }

    /*
     Решение на Питоне через стек

     # 1 - дом, 0 - магазин
попробуйте такой тест [1, 0, 1, 0, 1, 1, 2, 2, 2, 1, 2, 1, 2, 1, 0],
правильный ответ 5
ERROR BF ans = 5 my ans = 2

def find_max(a: list):
    max_ = 0
    n_shop = -len(a)
    steck = []
    for i in range(len(a)):
        if a[i] == 1 and i - n_shop > max_:
            steck.append(i)
        elif a[i] == 0:
            while steck:
                candidate = steck.pop()
                if i - candidate > max_:
                    if i - candidate >= candidate - n_shop:
                        max_ = candidate - n_shop
                        steck.clear()
                    else:
                        max_ = i - candidate
            n_shop = i
    if steck:
        max_ = steck.pop() - n_shop
    return max_


print(find_max([int(x) for x in '1110111111111110111']))  #6
print(find_max([int(x) for x in '011111111111']))  # 11

     */

    public static void main(String[] args) {
        // new MaxMinDist().runTests();
        new MaxMinDist().testForRandomArrays();
        out.close();
    }

    void runTests() {
        int dist = getMaxMinDist(new int[]{1, 0, 0, 1, 1, 0, 0, 2});
        out.println(dist == 6 ? "OK" : "ERROR " + dist);

        dist = getMaxMinDist(new int[]{1, 0, 0, 2, 1, 1, 0, 0, 0, 2, 1, 0 });
        out.println(dist == 3 ? "OK" : "ERROR " + dist);

        dist = getMaxMinDist(new int[]{1, 0, 0, 2, 1, 1, 0, 0,  0, 0, 0, 2, 1, 0 });
        out.println(dist == 4 ? "OK" : "ERROR " + dist);

        dist = getMaxMinDist(new int[]{1, 0, 0, 2, 1, 1, 0, 0, 0, 2, 1, 0, 1, 1, 1, 0, 0 });
        out.println(dist == 7 ? "OK" : "ERROR " + dist);

        dist = getMaxMinDist(new int[]{1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 2, 1, 0, 1, 1, 1, 0, 0 });
        out.println(dist == 8 ? "OK" : "ERROR " + dist);

        dist = getMaxMinDist(new int[]{1, 0, 0, 2, 1, 1, 0, 0, 0, 2, 1, 1, 0, 0, 0, 1, 0, 2, 0, 0, 1  });
        out.println(dist == 4 ? "OK" : "ERROR " + dist);
    }

    /*
     Дан массив из 0 1 2
     2 - магазин
     0 - жилой дом
     1 - офис
     Найти максимальное ближайшее расстояние от жилого дома до магазина

     1 000 000 11 0 2 0 0 0 0 1 1 0 1 0 0 2 2 2 2 0 2 1 2 0 0 1 0 0 2 1 0 0 0 1 0 1

     */

    public enum BuildingType {
        HOUSE(0),
        OFFICE(1),
        SHOP(2);

        int value;

        BuildingType(int value) {
            this.value = value;
        }
    }

    // betweenHouseIdx - индексы жилых домов, ограниченных 2-мя магазинами либо одним магазином и границей массива
    // время O(N + общее число жилых домов, ограниченных магазинами слева и справа) = O(N)
    // extra space = O( максимальное число жилых домов, ограниченных 2-мя магазинами либо магазином и границей массива)
    int getMaxMinDist(int[] arr) {
        int prevShopInd = -1, ans = 0;
        List<Integer> betweenHousesIdx = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == SHOP.value && prevShopInd >= 0) {
                int maxMin = 0;
                for(int j: betweenHousesIdx)
                    maxMin = Math.max(maxMin, Math.min(j - prevShopInd, i - j));
                ans = Math.max(ans, maxMin);
                prevShopInd = i;
                betweenHousesIdx.clear();
            } else if (arr[i] == SHOP.value && prevShopInd == -1) {
                if (!betweenHousesIdx.isEmpty())
                    ans = Math.max(ans, i - betweenHousesIdx.get(0));
                prevShopInd = i;
                betweenHousesIdx.clear();
            } else if (arr[i] == HOUSE.value) {
                betweenHousesIdx.add(i);
            }
        }
        if (!betweenHousesIdx.isEmpty())
            ans = Math.max(ans, betweenHousesIdx.get(betweenHousesIdx.size()-1) - prevShopInd);
        return ans;
    }

    int getMaxMinDistBF(int[] arr) {
        int ans = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == HOUSE.value) {
                int minDistToShop = Integer.MAX_VALUE;
                // to left
                for(int j = i-1; j >= 0 ; j--) {
                    if (arr[j] == SHOP.value) {
                        minDistToShop = Math.min(minDistToShop, i - j);
                        break;
                    }
                }
                // to right
                for(int j = i+1; j < arr.length ; j++) {
                    if (arr[j] == SHOP.value) {
                        minDistToShop = Math.min(minDistToShop, j - i);
                        break;
                    }
                }
                ans = Math.max(ans, minDistToShop);
            }
        }
        return ans;
    }

    void testForRandomArrays() {
        int testCount = 200, n = 12;
        int successTestCnt = 0;
        for(int i = 0; i < testCount; i++) {
            int[] arr = new int[n];
            boolean houses = false, shops = false;
            for(int j = 0; j < n; j++) {
                int val = rnd.nextInt(0, 3);
                arr[j] = val;
                houses = houses || val == HOUSE.value;
                shops = shops || val == SHOP.value;
            }
            if (houses && shops) {
                System.out.println("Test " + i);
                System.out.println(Arrays.toString(arr));
                int bfDist = getMaxMinDistBF(arr);
                int dist = getMaxMinDistWithStack(arr); // getMaxMinDist(arr);
                if (bfDist == dist) {
                    System.out.println("OK ans = " + dist);
                    successTestCnt++;
                } else {
                    System.out.println("ERROR BF ans = " + bfDist + " my ans = " + dist);
                }
            }
        }
        System.out.println("ALL TESTS " + testCount);
        System.out.println("SUCCESS TESTS " + successTestCnt);
    }

    void run() {
        int a = readInt();
        int ans = a + a;
        out.println(ans);
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}