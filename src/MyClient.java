import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyClient extends JFrame implements ActionListener, MouseListener
{
    BorderLayout b1;

    private JButton raisehand;
    public static int totalshapes = 0;
    public static int raisegonder = 0;
    public static int raiseal = 0;
    public static double raisesec;
    static int timeremain = 51;

    private Socket socket = null;

    public DataInputStream FromServer;
    public DataOutputStream OutToServer;


    protected static int x;
    protected static int y;
    public static int selector;
    public static int type;

    private JTextField text;
    private JTextField text_2;

    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem exit;

    JPanel jpCenter;

    public static Timer myTimer = new Timer();

    public MyClient() throws IOException {
        super("Whiteboard App - Student");
        b1 = new BorderLayout();
        setLayout(b1);

        Container cont_2 = getContentPane();
        cont_2.setLayout(new BorderLayout());
        text_2= new JTextField(15);
        text_2.setText("Remain Time: ");
        text_2.setFont(new Font("Consolas", Font.BOLD, 25));
        text_2.setForeground(Color.BLACK);
        text_2.setBackground(Color.GRAY);
        text_2.setEditable(false);
        cont_2.add(text_2, BorderLayout.SOUTH);
        setSize(400, 100);
        setVisible(true);

        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        text = new JTextField(15);
        text.setText("Number of shapes drawn: " + totalshapes);
        text.setFont(new Font("Consolas", Font.BOLD, 25));
        text.setForeground(Color.BLACK);
        text.setBackground(Color.GRAY);
        text.setEditable(false);
        cont.add(text, BorderLayout.NORTH);
        setSize(400, 100);
        setVisible(true);

        raisehand = new JButton("Raise Hand !");
        raisehand.addActionListener(this);

        add(raisehand, BorderLayout.SOUTH);

        menu();
        center();
        Client("127.0.0.1",5000);
    }

    public void menu()
    {
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");

        exit  = new JMenuItem("Exit");
        exit.addActionListener(this);

        menu.add(exit);
        menuBar.add(menu);

        add(menuBar);
        setJMenuBar(menuBar);
    }

    public void center()
    {
        jpCenter = new ClientPainter();
        jpCenter.setBackground(Color.white);
        add(jpCenter, BorderLayout.CENTER);
    }

    public void Client(String address, int port) throws IOException {
        try {
            socket = new Socket(address,port);
            System.out.println("Connected");

        } catch (IOException u)
        {
            System.out.println(u);
        }

        TimerTask gorev = new TimerTask() {
            @Override
            public void run() {
                timeremain--;
                text_2.setText("Remain Time: " + timeremain + " minutes");

                if (timeremain == 0)
                {
                    myTimer.cancel();
                    timeisover();
                    System.exit(0);
                }
            }
        };
        myTimer.schedule(gorev,0,60000);

        while (x != -1)
        {
            try {

                OutToServer = new DataOutputStream(socket.getOutputStream());
                FromServer = new DataInputStream(socket.getInputStream());

                selector = FromServer.readInt();

                type = FromServer.readInt();

                x = FromServer.readInt();

                y = FromServer.readInt();

                totalshapes = FromServer.readInt();

                text.setText("Number of shapes drawn: " + totalshapes);


                if (raisesec == 5)
                {
                    raiseal = FromServer.readInt();

                    if (raiseal == 0)
                    {
                        if (raisegonder == 1)
                        {
                            OutToServer.writeInt(raisegonder);
                            text.setText("Number of shapes drawn: " + totalshapes);
                        }
                        else
                        {
                                raisesec = 6;
                        }
                    }
                    else
                    {
                        raisesec = 6;
                    }
                }
                else
                {
                    raisesec = 6;
                }

                boya(selector,type);

            }
            catch (IOException i)
            {
                System.out.println(i);
            }
        }
        try {
            socket.close();
        }
        catch (IOException i)
        {
            System.out.println(i);
        }
    }

    public void boya(int sel,int typ)
    {
        repaint();
    }

    public void sendRaise(int raisegonder) throws IOException
    {
        OutToServer.writeInt(raisegonder);
    }

    public void timeisover()
    {
        JOptionPane.showMessageDialog(this, "Time is Over !!!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            System.exit(0);
        }
        else if (e.getSource() == raisehand){
            raisegonder = 1;
            raisesec = 5;
            try {
                sendRaise(raisegonder);
                raisegonder = 0;
                raisesec = 0;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
