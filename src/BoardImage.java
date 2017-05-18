
public class BoardImage {
    private BoardImageInteface boardImageInteface;

    public void setBoardImageInteface(BoardImageInteface boardImageInteface) {
        this.boardImageInteface = boardImageInteface;
    }

    public void fillRandPoints(int num) throws InterruptedException {
        boardImageInteface.fillRandPoints(num);
    }

    public void setRadiusOption(boolean i){
        boardImageInteface.radiusOption(i);
    }

    public void startGame(){
        boardImageInteface.startGrowth();
    }

    public void clearBoard(){
        boardImageInteface.clearBoard();
    }

}
