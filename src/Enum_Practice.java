/**
 * 使用enum关键字创造的枚举类型，都是java.lang.Enum这个抽象类的子类。
 * 枚举类型中的枚举值都会调用一次构造函数。在自定义构造函数的时候，无需声明其为private，
 * 因为枚举类型的构造函数默认就是private。
 */
public class Enum_Practice {

      public enum Size{
          SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");

          private String abbreviation;

          Size(String abbreviation){
            this.abbreviation = abbreviation;
            System.out.println(abbreviation);
          }
          public String getAbbreviation(){
            return abbreviation;
          }
    }
    public static void main(String[] args) {
          Size s1 = Size.SMALL;
          Size s2 = Size.MEDIUM;
          Size s3 = Size.LARGE;
          Size s4 = Size.EXTRA_LARGE;
          System.out.println(s1.getAbbreviation());
          System.out.println(s2.getAbbreviation());
          System.out.println(s3.getAbbreviation());
          System.out.println(s4.getAbbreviation());
    }
}
