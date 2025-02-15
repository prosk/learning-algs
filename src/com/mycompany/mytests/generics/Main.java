package com.mycompany.mytests.generics;

/*
user@MacBook-Pro-Administrator generics % javac Main.java


The system is out of resources.
Consult the following stack trace for details.
java.lang.StackOverflowError
        at com.sun.tools.javac.code.Types$UnaryVisitor.visit(Types.java:4652)
        at com.sun.tools.javac.code.Types$26.visitClassType(Types.java:3846)
        at com.sun.tools.javac.code.Types$26.visitClassType(Types.java:3838)
        ......
        at com.sun.tools.javac.code.Type$ClassType.accept(Type.java:786)
        at com.sun.tools.javac.code.Types$DefaultTypeVisitor.visit(Types.java:4571)
        at com.sun.tools.javac.code.Types.isSubtype(Types.java:840)

 */

public class Main {
    /*
    interface Z {
    }

    interface N<x> {
    }

    interface L<x> {
    }

    interface Qlr<x> {
    }

    interface Qrl<x> {
    }

    interface E<x> extends
        Qlr<N<? super Qr<? super E<? super E<? super x>>>>>,
        Qrl<N<? super Ql<? super E<? super E<? super x>>>>> {
    }

    interface Ql<x> extends
        L<N<? super Ql<? super L<? super N<? super x>>>>>,
        E<Qlr<? super N<? super x>>> {
    }

    interface Qr<x> extends
        L<N<? super Qr<? super L<? super N<? super x>>>>>,
        E<Qrl<? super N<? super x>>> {
    }

    L<? super N<? super L<? super N<? super L<? super N<? super
        E<? super E<? super Z>>>>>>>> doit(Qr<? super E<? super E<? super Z>>> v) {
        return v;
    }

    public static void main(String[] args) {
        System.out.println("Hello generics!");
    }

     */
}
