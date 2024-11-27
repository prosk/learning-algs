package com.mycompany.yandex.interview.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class UsersAndEmails {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new UsersAndEmails().run();
        out.close();
    }

    /*
    Имеется n пользователей, каждому из них соответствует список email-ов (всего m email-ов). Например:
user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru
user2 -> foo@gmail.com, ups@pisem.net
user3 -> xyz@pisem.net, vasya@pupkin.com
user4 -> ups@pisem.net, aaa@bbb.ru
user5 -> xyz@pisem.net

Считается, что если у двух пользователей есть общий email, значит это один и тот же пользователь.

Требуется построить и реализовать алгоритм, выполняющий слияние пользователей. На выходе должен быть список пользователей с их email-ами (такой же как на входе).

В указанном примере ответ на задачу будет следующий:
user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru, ups@pisem.net, aaa@bbb.ru
user3 -> xyz@pisem.net, vasya@pupkin.com

      user1 -- a
      user2 -- b
      user3 -- c
      user4 -- a, b, c

      ==> это все вообще один юзер с почтами a, b, c

      -- Решение от поступашек

      Решение:

Честно говоря, задача прикольная. С одной стороны, простая, а с другой — можно легко уйти не туда.

Подумаем про графы. Было бы хорошо выделить юзеров отдельно и множество почт отдельно.

Давайте визуально нарисуем слева вершины, которые соответствуют юзерам. В нашем примере их 5.

А справа выпишем множество различных почт.

После из каждой вершины слева проведем ребро к вершине справа, если у определенного юзера есть такая-то почта. Например, из вершины слева, которая отвечает за user1, проведется три ребра в правую сторону.

Если кто не понял, то это двудольный граф. И вся задача сводится к тому, чтобы найти количество компонент связностей. То есть делаем просто обход графа и запоминаем набор юзеров, которые посетили, и набор почт.

Например, запускаем ДФС с вершины user1 и посещаем вершины user2, user4, xxx@ya.ru, foo@gmail.com, lol@mail.ru, ups@pisem.net. В качестве ответа вы берете любого юзера и все почты, которые успели посетить. Дальше запускаете ДФС от непосещенной вершины (это вершина user3) и запускаете ДФС.

Единственное вам нужно пронумеровать вершины. Вы можете завести словарь куда будете писать номер вершины которому соответствует строка. Например
'user1' - 0,
'user2' - 1,
'xxx@ya.ru - 2'
.......
То есть каждой строке дать число. Зачем мы это делаем???
Попробуйте построить граф на строках и написать дфс, думаю веселье такое себе. Так что пишем словарь который будет строки переводить в числа. Также полезно создать еще один словарь, который будет по индексу вершины узнавать что за строка. Например из примера выше для индекса 2 соответствует строка xxx@ya.ru.

Асимптотика линейная.

Буду благодарен, если напишите код.

     */

    void run() {
        Map<String, Set<String>> userToEmail = new HashMap<>();
        userToEmail.put("user1", new HashSet<>(Arrays.asList("xxx@ya.ru", "foo@gmail.com",
                "lol@mail.ru")));
        userToEmail.put("user2", new HashSet<>(Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        userToEmail.put("user3", new HashSet<>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        userToEmail.put("user4", new HashSet<>(Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        userToEmail.put("user5", new HashSet<>(Arrays.asList("xyz@pisem.net")));
        Map<String, String> emailToUser = new HashMap<>();
        for(String user: userToEmail.keySet()) {
            Set<String> emails = userToEmail.get(user);
            String prevUser = null;
            for(String email: emails) {
                prevUser = emailToUser.get(email);
                if (prevUser != null) break;
            }
            String addToUser = prevUser != null ? prevUser : user;
            for(String email: emails) {
                userToEmail.get(addToUser).add(email);
            }
            if (prevUser != null) {

            }
        }
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