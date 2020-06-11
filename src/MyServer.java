import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class MyServer extends JFrame implements ActionListener, MouseListener {
    BorderLayout b1;

    protected static int x;
    protected static int y;
    public static int type = 0;
    public static int selector = 0;
    public static int totalshapes = 0;
    public static int raiseal = 0;
    public static int raiseal2 = 0;
    static int timeremain = 51;

    private Socket socket = null;
    private ServerSocket server = null;



    public DataOutputStream OutToClient;
    public DataInputStream FromClient;


    protected static JTextField text;
    protected static JTextField text_2;

    JMenuBar menuBar = new JMenuBar();
    JMenu draw,menu;
    JMenuItem square, rectangle, triangle, circle,exit;

    JPanel jpCenter;

    public static Timer myTimer = new Timer();

    public MyServer() throws IOException {
        super("Whiteboard App - Teacher");
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

        menu();
        center();
        Server(5000);
    }

    public void menu() {
        menuBar = new JMenuBar();

        draw = new JMenu("Draw");
        menu = new JMenu("Menu");

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        square = new JMenuItem("Square");
        square.addActionListener(this);

        rectangle = new JMenuItem("Rectangle");
        rectangle.addActionListener(this);

        triangle = new JMenuItem("Triangle");
        triangle.addActionListener(this);

        circle = new JMenuItem("Circle");
        circle.addActionListener(this);

        menu.add(exit);

        draw.add(square);
        draw.add(rectangle);
        draw.add(triangle);
        draw.add(circle);


        menuBar.add(draw);
        menuBar.add(menu);


        add(menuBar);
        setJMenuBar(menuBar);
    }

    public void center() {
        jpCenter = new Painter();
        jpCenter.addMouseListener(this);
        jpCenter.setBackground(Color.white);
        add(jpCenter, BorderLayout.CENTER);
    }

    public void Server(int port)
    {
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");


            socket = server.accept();
            System.out.println("Client Accepted");

            PrintWriter writer = new PrintWriter("Attendance.txt", "UTF-8");
            writer.println("Student's IP Address: " + socket.getRemoteSocketAddress().toString());
            writer.close();

            OutToClient = new DataOutputStream(socket.getOutputStream());
            FromClient = new DataInputStream(socket.getInputStream());

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

            while (raiseal != -5) {

                raiseal2 = FromClient.readInt();

                if (raiseal2 == 1)
                {
                    JOptionPane.showMessageDialog(this, "Someone Raised Hand !!!"); // Someone ı ip ile değiştir.
                }

            }

        }
        catch (IOException i)
        {
            System.out.println(i);
        }
    }

    public void timeisover()
    {
        JOptionPane.showMessageDialog(this, "Time is Over !!!");
    }


    public void sendX(int xcor) throws IOException {
        OutToClient.writeInt(xcor);
    }

    public void sendY(int ycor) throws IOException {
        OutToClient.writeInt(ycor);
    }

    public void sendTS(int ts) throws IOException {
        OutToClient.writeInt(ts);
    }

    public void sendSelector(int sel) throws IOException {
        OutToClient.writeInt(sel);
    }

    public void sendType(int sel) throws IOException {
        OutToClient.writeInt(sel);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (selector == 1 || selector == 2 || selector == 3 || selector == 4) {
            totalshapes++;

                try {
                    sendSelector(selector);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try {
                    sendType(type);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                text.setText("Number of shapes drawn: " + totalshapes);
                x = e.getX();

                try {
                    sendX(x);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                y = e.getY();

                try {
                    sendY(y);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try {
                    sendTS(totalshapes);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                repaint();

            } else
                JOptionPane.showMessageDialog(this, "Select the shape first!");
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == square) {
            int choise = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter\n1- Square\n2- Fill Square"));
            type = choise;
            JOptionPane.showMessageDialog(this, "Now Click Center Panel for Creating a Square");
            selector = 1;
        } else if (e.getSource() == rectangle) {
            int choise = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter\n1- Rectangle\n2- Fill Rectangle"));
            type = choise;
            JOptionPane.showMessageDialog(this, "Now Click Center Panel for Creating a Rectangle");
            selector = 2;
        } else if (e.getSource() == triangle) {
            int choise = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter\n1- Triangle\n2- Fill Triangle"));
            type = choise;
            JOptionPane.showMessageDialog(this, "Now Click Center Panel for Creating a Triangle");
            selector = 3;
        } else if (e.getSource() == circle) {
            int choise = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter\n1- Circle\n2- Fill Circle"));
            type = choise;
            JOptionPane.showMessageDialog(this, "Now Click Center Panel for Creating a Circle");
            selector = 4;
        }
        else if (e.getSource() == exit){
            System.exit(0);
        }
    }
}
