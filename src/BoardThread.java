
public class BoardThread extends Thread {

    private BoardGrainListener boardGrainListener;
    private Grain grain =new Grain();

    public BoardThread(Grain grain) {
        this.grain = grain;
    }

    public void setBoardGrainListener(BoardGrainListener boardGrainListener) {
        this.boardGrainListener = boardGrainListener;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            grain.checkBoardLife();
            boardGrainListener.onAreaCompute(grain);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
