
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

    public void startGrowth(int opt, boolean perBC){
        boardImageInteface.startGrowth(opt, perBC);
    }

    public void clearBoard(){
        boardImageInteface.clearBoard();
    }

    public void pauseGrowth(){
        boardImageInteface.pauseGrowth();
    }
    public void startGrowth(){
        boardImageInteface.startGrowth();
    }
}
