package com.mycompany.geeksforgeeks.numbers;

// https://www.geeksforgeeks.org/problems/how-many-xs4514/1
// Given two integers L, R, and digit X. Find the number of occurrences of X in all the numbers
// in the range (L, R) excluding L and R.
public class DigitCounter {
    public int simpleCountX(int L, int R, int X) {
        // R <= 100000
        int low = L+1, right = R-1;
        int totalCnt = right - low + 1;

        if (totalCnt <= 0)
            return 0;

        int[] curr = new int[6];
        int startNum = low;
        for(int i = 0; i <= 5; i++) {
            curr[i] = startNum % 10;
            startNum = startNum / 10;
        }

        int ans = 0;
        for(int j = 0; j < totalCnt; j++) {
            // calculation of count of X in curr
            int upper = 5;
            while(curr[upper] == 0) upper--;
            for(int digit = upper; digit >= 0; digit--) {
                if (curr[digit] == X) ans++;
            }
            // curr = curr + 1
            int indToPlus = 0;
            while (curr[indToPlus] == 9) indToPlus++;
            curr[indToPlus]++;
            for(int i = indToPlus-1; i >= 0; i--)
                curr[i] = 0;
        }
        return ans;
    }

    // original solution from geeks2geeks
    int countXHelper(int N, int X)
    {

        int cnt = 0;

        for (int i = 1; i <= N; i *= 10) {
            int divi = i * 10;
            int quot = N / divi;
            int rem = N % divi;

            if (quot > 0) {
                cnt = cnt + (quot * i);
            }

            // Вычитаем "ведущие нули" которые идут первые 10 чисел в сотне (разряд десяток)
            // или первые 100 в тысяче (разряд тысяч)
            // они не входят в соответствующие числа
            if (X == 0) {
                cnt = cnt - i;
            }

            if (rem >= X * i) {
                // Хитрое выражение, которое обрабатывает много кейсов сразу
                // 1) Если было quot = 0 и X = 0 то ранее мы вычитали i из cnt ошибочно (избыточно)
                //    Тогда тут будет большой rem = N, и минимум окажется i - мы его прибавляем и компенсируем вычитание i ранее
                //    То есть по сути для этого кейса quot = 0 и X = 0 мы НИКАК НЕ МЕНЯЕМ cnt
                //    rem - X * i + 1 = rem+1 = N+1, при этом i <=N по условию цикла, поэтому минимумом всегда будет i

                // 2) Прибавление +1 тоже имеет двойной смысл - для X > 0 и (X = 0, quot > 0, rem > 0)
                //    это понятный корректный подсчет числа последовательных элементов между 2-мя позициями
                //    Для X = 0 и rem = 0 - всегда будет прибавление к cnt единицы - (если rem = 0 значит quot > 0,
                //    для самой первой группы мы уже прибавили i и далее мы вычли i,
                //    для остальных групп мы прибавили по i
                //   +1 так как нам надо добавить 0 в самой последнем числе которое не вошло ни в одну группу
                //    100, 300, 1000, 2000, 10000 и тп
                //    В explainable solution я это +1 делаю явно на этапе 1-ой формулы, а в учете остатка компенсирую
                //    Тут наоборот это прибавление +1 идет на этапе обработки остатка

                // 3)  Для X = 0 и rem > 0 - случай quot = 0 и X = 0 уже был рассмотрен
                //   А если quot > 0 то мы тут сделаем min(rem+1, i) и учтем число типа 100 300 2000 и тп
                //   которое мы не учитывали ранее при вычислении на основе quot
                //   В explainable solution мы +1 делаем ранее при вычислении на основе quot,
                //   поэтому в аналогичном кейсе вычитаем 1, другими словами, берем уже min(rem, i-1) для прибавления к cnt
                cnt = cnt + (Math.min(rem - X * i + 1, i));
            }
        }

        return cnt;
    }

    // more explainable solution
    int myCountXHelper(int N, int X)
    {
        int cnt = 0;

        // place - разряд 1- единицы 10 - десятки 100 - сотни и тд
        for (int place = 1; place <= N; place *= 10) {
            // groupSize - число чисел в одной группе для которой считаем число появлений цифры X в разряде place
            int groupSize = place * 10;
            int fullGroupCnt = N / groupSize;
            int numbersInPartialGroupCnt = N % groupSize; // 0 <= numbersInPartialGroupCnt < groupSize
            int digitOccurencesInOneGroup = place; // число появлений цифры X в разряде place в ОДНОЙ группе

            if (X > 0) {
                // more simple case when X is non zero digit
                cnt += (fullGroupCnt * digitOccurencesInOneGroup);

                // такая логика с остатком так как внутри одной группы сначала идут digitOccurencesInOneGroup единиц,
                // потом digitOccurencesInOneGroup двоек, потом троек и тд до digitOccurencesInOneGroup девяток
                if (numbersInPartialGroupCnt >= X*place) {
                    if (numbersInPartialGroupCnt < (X+1)*place) {
                        cnt += (numbersInPartialGroupCnt - X*place + 1);
                    } else {
                        cnt += digitOccurencesInOneGroup;
                    }
                }

            } else {
                // X == 0
                //  в самой первой группе нулей в разряде place нету
                // например для 2-го разряда нет нулей в числах 0-99, для 3-го разряда нет нулей в числах 0-999
                // (само число 0 и "ведушие" нули слева мы не считаем)
                // +1 так как нам надо добавить 0 в самой последнем числе которое не вошло ни в одну группу
                // 100, 300, 1000, 2000, 10000 и тп

                // основная часть
                if (fullGroupCnt > 1) {
                    cnt = cnt + (fullGroupCnt-1) * digitOccurencesInOneGroup + 1;
                } else if (fullGroupCnt == 1) {
                    cnt = cnt + 1;
                }
                // остаток
                if (fullGroupCnt >= 1) {
                    if (numbersInPartialGroupCnt > 0) {
                        if (numbersInPartialGroupCnt < place) {
                            cnt += (numbersInPartialGroupCnt + 1);
                        } else {
                            cnt += digitOccurencesInOneGroup;
                        }
                        cnt--; // вычитам 1 так как мы учли 0 в числах типа 100 300 5000 в основной части
                    }
                } else {
                    // fullGroupCnt = 0
                    // есть только остаток, но в самой первой группе (еще и неполной) нулей нету
                    // нулей нет даже в полной первой группе типа 0-99 0-999 и тд
                }
            }
        }
        return cnt;
    }

    int countX(int L, int R, int X) {
        return countXHelper(R - 1, X) - countXHelper(L, X);
    }

    int myCountX(int L, int R, int X) {
        return myCountXHelper(R - 1, X) - myCountXHelper(L, X);
    }

    public static void main(String[] args) {
        DigitCounter digitCounter = new DigitCounter();
        /*System.out.println("Simple solution");
        System.out.println("L=10, R=19, X=1: ans = " + digitCounter.simpleCountX(10, 19, 1));
        System.out.println("L=18, R=81, X=9: ans = " + digitCounter.simpleCountX(18, 81, 9));
        System.out.println("L=504, R=7382, X=0: ans = " + digitCounter.simpleCountX(504, 7382, 0));
        System.out.println("L=73, R=1028, X=5: ans = " + digitCounter.simpleCountX(73, 1028, 5));
        System.out.println("L=70, R=102, X=0: ans = " + digitCounter.simpleCountX(70, 102, 0)); */

        System.out.println("Optimal solution");
        System.out.println("L=10, R=19, X=1: ans = " + digitCounter.countX(10, 19, 1));
        System.out.println("L=18, R=81, X=9: ans = " + digitCounter.countX(18, 81, 9));
        System.out.println("L=504, R=7382, X=0: ans = " + digitCounter.countX(504, 7382, 0));
        System.out.println("L=73, R=1028, X=5: ans = " + digitCounter.countX(73, 1028, 5));
        System.out.println("L=70, R=102, X=0: ans = " + digitCounter.countX(70, 102, 0));

        System.out.println("Explainable Optimal solution");
        System.out.println("L=10, R=19, X=1: ans = " + digitCounter.myCountX(10, 19, 1));
        System.out.println("L=18, R=81, X=9: ans = " + digitCounter.myCountX(18, 81, 9));
        System.out.println("L=504, R=7382, X=0: ans = " + digitCounter.myCountX(504, 7382, 0));
        System.out.println("L=73, R=1028, X=5: ans = " + digitCounter.myCountX(73, 1028, 5));
        System.out.println("L=70, R=102, X=0: ans = " + digitCounter.myCountX(70, 102, 0));
    }

}
