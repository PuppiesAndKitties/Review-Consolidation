package genericClass_practice;

public class PairTest1 {
    public static void main(String[] args) {
        String words[] = {"Mary","had","a","little","lamb"};
        // 在使用泛型类时，需要为泛型类指定一个或多个具体类型才能够使用。
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min:"+mm.getFirst());
        System.out.println("max:"+mm.getSecond());
    }
}
class ArrayAlg{
    public static Pair<String> minmax(String [] a) {
        if (a == null || a.length==0) return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (min.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min,max);
    }
}
