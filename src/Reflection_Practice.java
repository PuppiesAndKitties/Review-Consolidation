import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

public class Reflection_Practice {
    public static void main(String[] args) {
        // generate_Class();             //生成Class类
        // use_Class_analysis();         //用反射分析类的能力
        running_analysis();             //在运行时使用反射分析对象
    }

    /**
     * 生成Class类的3种方法；使用反射方法根据类名创建对象
     */
    private static void generate_Class() {
        // 获取Class类的第一种方法：调用该类对象的getClass()方法
        Random generator = new Random();
        Class cl = generator.getClass();
        System.out.println(cl.getName());
        try{
            // 获取Class类的第二种方法：调用Class类的forName()方法
            Class cl_created_by_name = Class.forName(cl.getName());
            System.out.println(cl_created_by_name.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }
        // 获取Class类的第三种方法：调用该类型的.class属性
        Class cl_called_by_attribute = Random.class;
        System.out.println(cl_called_by_attribute.toString());


        String s = "java.util.Random";
        try {
            // 将forName和getDeclaredConstructor().newInstance()配合使用，就可以根据存储在字符串中的类名创建一个对象
            Object m = Class.forName(s).getDeclaredConstructor().newInstance();
            System.out.println(m.toString());
            System.out.println(m.getClass().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 反射机制最重要的内容：检查类的结构
     */
    public static void use_Class_analysis() {
        String name = "java.util.Random";
        try {
            //如果类不是Object类，就打印类名和超类名
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class) {
                System.out.println(" extends " + supercl.getName());
            }
            System.out.println("{");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Class类的getFields\getMethods\getConstructors方法返回的是public域、方法和构造器数组，其中包括超类的共有成员。
     * 而getDeclaredFields\Methods\Constructors方法返回的是全部域、方法和构造器，其中包括私有和受保护成员，但不包括超类的成员。
     * 打印一个类的所有构造器
     */
    private static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("  ");
            // Modifier类——修饰符工具类，提供了static 方法和常量，对类和成员访问修饰符进行解码。
            String modifiers = Modifier.toString(c.getModifiers()); // 使用Modifier类的toString方法解码并打印出单个构造器函数的修饰符
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");

            //打印参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印一个类的所有方法
     */
    private static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();
            System.out.print("  ");
            //打印出方法的修饰符、返回类型和方法名
            String modifiers = Modifier.toString(m.getModifiers()); // 使用Modifier类的toString方法解码并打印出单个构造器函数的修饰符
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + name + "("); // 打印返回值和方法名

            //打印方法的参数类型
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印一个类的所有域
     */
    private static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }

    /**
     *在运行时使用反射分析对象。
     */
    private static void running_analysis() {
        Tmp_Test_Class tmpTestClass = new Tmp_Test_Class(4.55555,"Where is the love?");
        Class cl = Tmp_Test_Class.class;
        try {
            Field f1 = cl.getDeclaredField("param2");
            Field f2 = cl.getDeclaredField("param1");
            // 对于私有域，需要调用Field、Method或Constructor对象的setAccessible方法
            f1.setAccessible(true);
            f2.setAccessible(true);
            // f.get方法，输入参数为想要查看其f域的对象，返回值是该对象的f域的值，但是传回的参数是Object对象
            Object v1 = f1.get(tmpTestClass);
            System.out.println(v1);
            // 对于String类型，将其作为Object返回没有任何问题。但是对于double类型，而Java中数值类型并非对象，这时应该
            // 使用getDouble方法，反射机制会自动将获取到的域值打包到相应的对象包装器中，这里将打包成Double。
            Object v2 = f2.getDouble(tmpTestClass);
            System.out.println(v2);
            // 使用f.set(obj,value)方法将obj对象的f域设置为value值
            f1.set(tmpTestClass,"Love is everywhere");
            System.out.println(tmpTestClass.getParam2());
            System.out.println(tmpTestClass.param2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Tmp_Test_Class{
        private double param1;
        private String param2;
        Tmp_Test_Class() {
            param1 = 1.00;
            param2 = "Sentence";
        }
        Tmp_Test_Class(double param1, String param2) {
            this.param1 = param1;
            this.param2 = param2;
        }
        private String  getParam2(){
            return param2;
        }
    }
}