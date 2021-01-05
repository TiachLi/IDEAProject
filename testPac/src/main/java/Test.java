import com.atguigu.commonutils.R;
import com.atguigu.springcloud.entities.Payment;

public class Test {

    public static void main(String[] args) {
        Payment payment=new Payment();
        System.out.println(payment);

        R data = R.ok().data("1", 1);
        System.out.println(data);
    }
}
