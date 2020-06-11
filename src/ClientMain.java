import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientMain
{
    public static void main(String[] args) throws IOException
    {
        MyClient mclient = new MyClient();
        mclient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mclient.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mclient.setSize(screenSize.width/2,screenSize.height/2);
    }
}
