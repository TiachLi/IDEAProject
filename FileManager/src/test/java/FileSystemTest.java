import com.FileIndex;
import com.FileNode;
import com.FileSystem;
import org.junit.Test;
import sun.security.provider.MD2;

import java.util.Scanner;

public class FileSystemTest {
    @Test
    public void test1(){
        FileSystem fileSystem=new FileSystem("c");
        fileSystem.cd("c");
        fileSystem.md("b1");
        fileSystem.md("b2");
      //  fileSystem.dir();
        fileSystem.cd("c/b2");
        fileSystem.md("c1");
        fileSystem.md("c2");
        fileSystem.md("c2");
      //  fileSystem.dir();
        fileSystem.cd("c/b1");
        fileSystem.md("c1");
        fileSystem.md("c2");
     //   fileSystem.dir();
        fileSystem.cd("s");
        fileSystem.md("c/b2/c1/d1");
        fileSystem.cd("c/b2/c1/d1");
        fileSystem.md("e1");
       // fileSystem.dir();
        fileSystem.rd("c/b2/c1");
        fileSystem.dir("c/b2");
        fileSystem.md("c/b2/c1");
        fileSystem.cd("c/b2");
        fileSystem.dir();
        fileSystem.edit("wen jian");
        fileSystem.edit("wen jian1");
        fileSystem.md("c/b1");
        fileSystem.del("c/b2/wen jian");
        fileSystem.edit("wen jian");
        fileSystem.edit("c/b1/wen jian3");
        fileSystem.md("c/b1/wen jian jia");
        fileSystem.dir("c/b1");

    }

   @Test
    public void test2(){
       FileSystem fileSystem=new FileSystem("c");
       fileSystem.cd("c");
       fileSystem.md("b1");
       fileSystem.md("b1");
       fileSystem.md("c/b1/c1");
       fileSystem.md("c/b1/c2");
       fileSystem.rd("c/b1");
       fileSystem.md("b1");
       fileSystem.dir();
    }
    @Test
    public void test3(){
    FileNode fileNode =new FileNode();
    FileNode fileNode1=new FileNode();
    fileNode.setFatherNode(fileNode1);
        change(fileNode);
        System.out.println(fileNode.getFileName());
        System.out.println(fileNode1.getFileName());
    }
    public void change(FileNode fileNode){
        fileNode.setFileName("change");
        fileNode=null;
    }

    @Test
    public void test4(){
        FileNode fileNode =new FileNode();
        fileNode.setFileName("1");
        change(fileNode);
        System.out.println(fileNode.getFileName());
    }



}
