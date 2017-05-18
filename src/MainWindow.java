import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;


public class MainWindow extends JFrame implements ActionListener {
    private JButton losujButton;
    private JPanel panel1;
    private JPanel panel2;
    private JTextField textField1;
    private JButton startButton;
    private JRadioButton losoweRadioButton;
    private JRadioButton rownomierneRadioButton;
    private DrawPanel drawPanel;

    private ButtonGroup losujGroup=new ButtonGroup();

    private BufferedImage img;
    private BoardImage boardImage;

    public MainWindow() {

        losujGroup.add(losoweRadioButton);
        losujGroup.add(rownomierneRadioButton);
        losoweRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardImage.setRadiusOption(false);
            }
        });
        rownomierneRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardImage.setRadiusOption(true);
            }
        });

                panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.add(new DrawPanel("DrawPanel"));

        boardImage =new BoardImage();
        losujButton.addActionListener(this);
        startButton.addActionListener(this);

        setContentPane(panel1);
        setSize(new Dimension(620,500));
        setTitle("Rozrost ziaren");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Component[] components=panel2.getComponents();
        drawPanel= (DrawPanel) components[0];
        //img=drawPanel.getImg();
        boardImage.setBoardImageInteface(drawPanel);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img,0,0,this);
    }

    public static void main(String[] args){
        MainWindow window=new MainWindow();
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== losujButton){
            int num=Integer.parseInt(textField1.getText());
            try {
                boardImage.fillRandPoints(num);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource()== startButton){
            boardImage.startGame();
        }
    }
}
