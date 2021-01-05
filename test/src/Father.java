import java.util.Scanner;

public  class Father implements ert {

    private privateMoney pm;

    public String name="father";
    public Father(){
    }

   public int test( Byte x,int y){
        System.out.println("father test");
       System.out.println(name);
       return  (x+y);
    }

    public void fatherTest(){
        System.out.println("Father Only");
    }

    @Override
    public final void eat() {
        System.out.println("father eat");
        pm.addMoney();

    }

    @Override
    public int test(Object o) {
        return (int)o;

    }

    public final void eat(int a) {
        System.out.println("father eat");
        pm.addMoney();

    }
    public class privateMoney{
    public  int a;
        public void addMoney(){
            System.out.println("add money");
            System.out.println(pm);
        }
    }

    public float aFun(float a, float b){
        return 0;
    }

}
