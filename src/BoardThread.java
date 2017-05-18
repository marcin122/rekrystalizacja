
public class BoardThread extends Thread {

    private BoardGrainListener boardGrainListener;
    private Grain grain =new Grain();
    private boolean stopGrowth=false;

    public void stopGrowth(boolean stopGrowth) {
        this.stopGrowth = stopGrowth;
    }

    public BoardThread(Grain grain) {
        this.grain = grain;
    }

    public void setBoardGrainListener(BoardGrainListener boardGrainListener) {
        this.boardGrainListener = boardGrainListener;
    }

    @Override
    public void run() {
        super.run();
        while (!stopGrowth) {
            grain.checkBoardLife();
            if(grain.numFreeBlock()==0)
                stopGrowth(true);
            boardGrainListener.onAreaCompute(grain);
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
