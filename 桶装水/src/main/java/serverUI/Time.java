package serverUI;

/*
 * #Time.java文档
 */
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Time implements ActionListener{
    /**
     * 反序列化
     */
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private JButton plusbutton;

    /*
     * 构造方法，用于创建计时器对象
     */
    public Time(JButton plusbutton){
        this.plusbutton = plusbutton;
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == plusbutton){// 清除标签内容
            timer.stop();
        }

    }

}