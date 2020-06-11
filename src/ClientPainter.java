import javax.swing.*;
import java.awt.*;

public class ClientPainter extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (MyClient.selector == 1)
        {
            if (MyClient.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawRect(MyClient.x-100, MyClient.y-100, 200, 200);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(MyClient.x-100, MyClient.y-100, 200, 200);
            }
        }

        else if (MyClient.selector == 2)
        {
            if (MyClient.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawRect(MyClient.x-50, MyClient.y-100, 100, 200);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(MyClient.x-50, MyClient.y-100, 100, 200);
            }
        }

        else if (MyClient.selector == 3)
        {
            int a[] = {MyClient.x, MyClient.x-150, MyClient.x+150};
            int b[] = {MyClient.y, MyClient.y+150, MyClient.y+150};

            if (MyClient.type == 1)
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

        else if (MyClient.selector == 4)
        {
            if (MyClient.type == 1)
            {
                g.setColor(Color.BLUE);
                g.drawOval(MyClient.x-75, MyClient.y-75, 150, 150);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillOval(MyClient.x-75, MyClient.y-75, 150, 150);
            }
        }


    }
}
