import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ServerMain
{
    public static void main(String[] args) throws IOException
    {
        MyServer mserver = new MyServer();
        mserver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mserver.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mserver.setSize(screenSize.width / 2, screenSize.height / 2);
    }
}
