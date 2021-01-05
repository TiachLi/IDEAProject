import com.li.domain.Teacher;
import com.li.service.ClassTable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ClassTableTest {
    @Test
    public void test1(){
        String[] subjects=new String[]{"语文","数学","英语"};
        List<Teacher> teachers=new ArrayList<Teacher>();
        Teacher teacher=new Teacher("zhang",new String[]{"1","3","4"},new String[]{"语文","数学","英语"});
        Teacher teacher1=new Teacher("wang",new String[]{"5","4","2"},new String[]{"物理","数学","英语"});
        Teacher teacher2=new Teacher("li",new String[]{"6","7","8"},new String[]{"语文","化学","英语"});
        teachers.add(teacher);
        teachers.add(teacher1);
        teachers.add(teacher2);
        ClassTable classTable =new ClassTable();
        classTable.arrayClasses(teachers);
    }
}
