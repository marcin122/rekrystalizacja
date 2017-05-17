
public class BoardImage {
    private BoardImageInteface boardImageInteface;

    public void setBoardImageInteface(BoardImageInteface boardImageInteface) {
        this.boardImageInteface = boardImageInteface;
    }

    public void fillRandPoints(int num) throws InterruptedException {
        boardImageInteface.fillRandPoints(num);
    }

    public void startGame(){
        boardImageInteface.startGame();
    }

    public void clearBoard(){
        boardImageInteface.clearBoard();
    }

}
