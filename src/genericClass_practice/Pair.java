package genericClass_practice;

import java.lang.reflect.Parameter;

/**
 * 一个泛型类就是具有一个或多个类型变量的类。定义一个泛型类，需要在类名后面加<T>,括号中应当是长度为1或较短的大写字母组合。
 * 在定义之后， T 就可以视为一个类型，可用于声明变量类型、返回值类型等。
 * @param <T>
 */
public class Pair<T>{
    private T first;
    private T second;

    Pair() {
        first = null;
        second = null;
    }

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
