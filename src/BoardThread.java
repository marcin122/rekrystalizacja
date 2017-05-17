
public class BoardThread extends Thread {

    private BoardGameListener boardGameListener;
    private Game game=new Game();

    public BoardThread(Game game) {
        this.game = game;
    }

    public void setBoardGameListener(BoardGameListener boardGameListener) {
        this.boardGameListener = boardGameListener;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            game.checkBoardLife();
            boardGameListener.onAreaCompute(game);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
