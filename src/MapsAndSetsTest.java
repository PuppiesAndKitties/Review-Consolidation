import java.time.LocalDate;
import java.util.*;

public class MapsAndSetsTest {
    public static void main(String[] args) {
        //linkedListTest();
        //setTest();
        //treeSetTest();
        //priorityQueueTest();
        hashMapTest();
    }

    /**
     * LinkedList数据结构为链表，从链表中间删除一个元素，即需要被删除元素附近的链接。
     * 链表与泛型集合之间有一个非常重要的区别，那就是链表是一个有序集合，每个对象的位置十分重要。
     * LinkedList的add方法将对象添加到链表的尾部。但是如果需要将对象添加到链表的中间，那么这种
     * 依赖于位置的add方法将由迭代器负责。只有对自然有序的集合使用迭代器添加元素才有实际意义，
     * 若其中的元素全都无序，如set类型，因此，Iterator接口中没有add方法，而是集合类库提供一个
     * 子接口ListIterator中包含add方法。另外ListIterator接口的previous()和hasPrevious()方法还
     * 可以用来反向遍历链表。
     */
    private static void linkedListTest() {
        List<String> staff = new LinkedList<>();
        staff.add("tomy");
        staff.add("bob");
        staff.add("Alibi");
        /*Iterator<Sting>如果改为Iterator，那么staff中的默认元素为对象，则后两句
        String first\second = iterator.next()后面还需要添加toString()方法将对象类型转为字符串类型。*/
        Iterator<String> iterator = staff.iterator();  //获取该集合的迭代器.
        String first = iterator.next();
        String second = iterator.next();
        System.out.println(first);
        System.out.println(second);
        iterator.remove();   // remove方法将在迭代器位置之前的元素删掉
        //iterator.add(); //add将会报错，因为Iterator接口中没有add方法。
        ListIterator<String> listIterator = staff.listIterator();
        System.out.println(listIterator.next());
        listIterator.add("Ash");       // ListIterator迭代器的add方法将在迭代器位置之前添加一个新对象
        System.out.println(listIterator.next());
        listIterator.add("Thermite");
        //previous()方法获取迭代器位置之前的对象。执行完该方法后，迭代器位置向前移动一个单位。
        System.out.println(listIterator.previous());
        // set()方法用一个新元素来取代调用next或previous方法返回的上一个元素。
        listIterator.set("New Value");
        /*当一个迭代器在修改集合时，另一个迭代器对其进行遍历，一定会出现混乱的情况，例如一个
        迭代器指向另一个迭代器刚刚删除的元素前面，现在这个迭代器就是无效的，并且不该再使用。
        链表迭代器的设计使其能够检测到这种修改。如果迭代器发现它的集合被另一个迭代器修改了，
        或者被该集合的自身方法修改了，就会抛出一个ConcurrentModificationException异常。
        为了避免这种情况，可以根据需要给容器附加许多的迭代器，但是这些迭代器只能读取列表，
        另外单独附件一个能够读和写的迭代器。*/
    }

    /**
     * 散列集
     */
    private static void setTest() {
        Set<String> words = new HashSet<>(); //HashSet是Set接口的实现类
        long totalTime = 0;
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }
        Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++) {
            System.out.println(iter.next());
            System.out.println(" . . .");
            System.out.println(words.size() + " distinct words." + totalTime + " milliseconds.");
        }
    }

    /**
     * TreeSet实现了SortedSet接口,而SortedSet接口继承自Set
     * 要使用树集，则其中的元素必须实现Comparable接口，或者构造集时必须提供一个实现了Comparator接口的类的实例
     * 树集将会按照顺序排列元素，因此遍历时的顺序与添加元素的顺序是无关的，其排序使用红黑树结构完成
     */
    private static void treeSetTest() {
        SortedSet<String> sort = new TreeSet<>();
        sort.add("Bob");
        sort.add("Amy");
        sort.add("Thermite");
        for (String s :sort) System.out.println(s);
    }

    /**
     * PriorityQueue使用的数据结构成为堆heap，堆是一个可以自我调整的二叉树，对树执行添加(add)和删除(remove)
     * 操作，可以让最小的元素移动到根，而不必花费时间对元素进行排序。
     * PriorityQueue中的元素，进行迭代时不一定会按照顺序，但是remove方法一定是按照顺序的。
     */
    private static void priorityQueueTest() {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("迭代元素...");
        for (LocalDate date : pq) {
            System.out.println(date);
        }
        System.out.println("删除元素...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

    /**
     * 每当往映射中添加对象时，必须同时提供一个键。在下面的方法中，键是一个字符串，对应的值是LocalDate对象
     * 更新映射项的时候要注意，get方法中的传入参数Key对应的值如果不存在的话，是会返回null的，
     * 使用getOrDefault方法可以在对应映射不存在的情况下返回一个默认值
     *
     * 集合框架不认为映射是一种集合，但是可以通过Map.keySet() values() entrySet()方法获取该映射的视图(view)
     */
    private static void hashMapTest() {
        Map<String, LocalDate> births = new HashMap<>();
        births.put("JayChow", LocalDate.of(1980, 12, 6));
        births.put("Jolin", LocalDate.of(1979, 6, 20));
        births.put("ZhangXinYu", LocalDate.of(1997, 8, 8));
        births.put("GuoJinYi", LocalDate.of(1997, 5, 1));

        //打印所有键值对的信息，该顺序与put的顺序并不同
        System.out.println(births);
        //使用forEach()方法迭代所有键值对，该顺序与直接打印births的顺序是相同的
        births.forEach((k,v ) ->
                System.out.println("Key = "+ k +" , Value = " + v));
        //删除一个条目
        births.remove("JayChow");
        //替换一个条目
        births.replace("Jolin", LocalDate.of(1988, 8, 8));
        System.out.println(births);
        //查找一个值, 如果找不到则返回null
        System.out.println(births.get("ZhangXinYu"));
        System.out.println(births.get("Nobody"));

        //通过Map.keySet() values() entrySet()方法获取该映射的视图(view)
        Set<String> keys = births.keySet();
        System.out.println(keys);
        Collection<LocalDate> values = births.values();
        System.out.println(values);

        //以下方法可以简单地用Map.forEach()方法代替
        Set<Map.Entry<String, LocalDate>> entrySet = births.entrySet();
        for (Map.Entry<String, LocalDate> entry : entrySet) {
            String k = entry.getKey();
            LocalDate v = entry.getValue();
            System.out.println("k:" + k + ", v:" + v);
        }
    }
}