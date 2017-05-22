import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawPanel extends JPanel implements MouseListener, BoardImageInteface, BoardGrainListener {

    private BufferedImage img;
    private String name;
    private int[] border=new int[21];
    private Grain grain;
    private BoardThread boardThread;
    private boolean radiusOption=false;
    private PalleteColors palleteColors=new PalleteColors();
    private Random random=new Random();

    public DrawPanel(String name) {
        this.name=name;
        img=new BufferedImage(400,400,BufferedImage.TYPE_INT_ARGB);
        drawBackground();

        for(int i=1, n=0;i<img.getWidth();i+=21, n++){
            border[n]=i;
        }

        addMouseListener(this);

        grain =new Grain();
    }

    public BufferedImage getImg() {
        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        g2.drawImage(img,0,0,this);
    }

    public void drawWhitePoint(int x, int y){
        img.setRGB(x,y,Color.WHITE.getRGB());
        this.repaint();
    }

    public void drawRedPoint(int x, int y){
        img.setRGB(x,y,Color.RED.getRGB());
        this.repaint();
    }

    public void drawBackground(){
        for(int i=1;i<img.getHeight();i++){
            for(int j=1;j<img.getWidth();j++) {
                drawWhitePoint(i,j);
            }
        }
    }

    public void drawPoint(int x, int y, Color c){
        img.setRGB(x,y,c.getRGB());
        this.repaint();
    }

    public void updateBoard(Grain g){

        for(int i=0;i<400;i++){
            for(int j=0;j<400;j++){
                if(g.isBlockFree(i,j,false)){
//                    drawPoint(i,j,grain.getBlockColor(i,j));
                }
                else{
                    drawPoint(i,j,palleteColors.getColor(grain.getBlockColor(i,j)));
                }
            }
        }
    }

    @Override
    public void clearBoard(){
        drawBackground();
        boardThread.stopGrowth(true);
        try {
            boardThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        grain.newGrain();
    }

    @Override
    public void startGrowth(int opt, boolean perBC) {
        grain.setSasOption(opt);
        grain.setPeriodicBC(perBC);

            boardThread = new BoardThread(grain);
            boardThread.setBoardGrainListener(this);
        boardThread.stopGrowth(false);
        if(!boardThread.isAlive()) {
            boardThread.start();
        }
    }

    public int calculatePositionBoard(int n){
        int position=0;
        for(int i=0;i<border.length;i++){
            position=i;
            if(i+1>=border.length || n<border[i+1]) break;
        }
        return position;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int posX=calculatePositionBoard(x);
        int posY=calculatePositionBoard(y);

            if(grain.isBlockFree(posX,posY,radiusOption)){
                int color=random.nextInt(palleteColors.getNumColors());
                grain.setBlock(posX, posY, radiusOption, color);
                drawPoint(posX, posY, palleteColors.getColor(color));
            }
            else{
                JOptionPane.showMessageDialog(null,"Wybrane pole jest zajęte", "Błąd",JOptionPane.ERROR_MESSAGE);
            }
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
    public void fillRandPoints(int num)  {

            for (int i = 0; i < num; ) {
                int posX = random.nextInt(400);
                int posY = random.nextInt(400);
                if (grain.isBlockFree(posX, posY, radiusOption)) {
                    int color = random.nextInt(palleteColors.getNumColors());
                    grain.setBlock(posX, posY, radiusOption, color);
                    drawPoint(posX, posY, palleteColors.getColor(color));
                    i++;
                }
            }
    }

    @Override
    public void radiusOption(boolean i) {
        radiusOption=i;
    }

    @Override
    public void pauseGrowth() {
        try {
            boardThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startGrowth() {
        boardThread.notify();
    }

    @Override
    public void onAreaCompute(Grain grain) {
        updateBoard(grain);
    }
}
