import javax.swing.*;
import java.awt.*;

public class Painter extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (MyServer.selector == 1)
        {
            if (MyServer.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawRect(MyServer.x-100, MyServer.y-100, 200, 200);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(MyServer.x-100, MyServer.y-100, 200, 200);
            }
        }

        else if (MyServer.selector == 2)
        {
            if (MyServer.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawRect(MyServer.x-50, MyServer.y-100, 100, 200);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(MyServer.x-50, MyServer.y-100, 100, 200);
            }
        }

        else if (MyServer.selector == 3)
        {
            int a[] = {MyServer.x, MyServer.x-150, MyServer.x+150};
            int b[] = {MyServer.y, MyServer.y+150, MyServer.y+150};

            if (MyServer.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawPolygon(a,b,3);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillPolygon(a,b,3);
            }
        }

        else if (MyServer.selector == 4)
        {
            if (MyServer.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawOval(MyServer.x-75, MyServer.y-75, 150, 150);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillOval(MyServer.x-75, MyServer.y-75, 150, 150);
            }
        }


    }
}
