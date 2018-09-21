package genericClass_practice;

import java.time.LocalDate;

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1815, 12, 10),
                LocalDate.of(1903,12,3),
                LocalDate.of(1910,6,22)
        };
        Pair<LocalDate> mm = ArrayAlg2.minmax(birthdays);
        System.out.println("min="+mm.getFirst());
        System.out.println("max="+mm.getSecond());
    }
}

/**
 * 定义一个泛型方法，需要在修饰符与类型之间添加<T>符号。
 * 其中extends表示T 应该是绑定类型的子类型。T 和绑定类型可以使类，也可以是接口。
 * 选择关键字extends的原因是更接近子类的概念。一个类型变量或通配符可以有多个限定： <T extends Comparable & Serializable>
 */
class ArrayAlg2 {
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i])>0) min = a[i];
            if (min.compareTo(a[i])<0) max = a[i];
        }
        return new Pair<>(min, max);
    }
}
