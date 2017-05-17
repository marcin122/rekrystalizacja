
public class Game {
    private boolean[][] board;
    private boolean[][] previousBoard;

    public Game() {
        board=new boolean[21][21];
        previousBoard=new boolean[21][21];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=false;
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                previousBoard[i][j]=false;
            }
        }
    }

    public void newGame(){
        clearBoard();
        clearPreviousBoard();
    }

    public void setBlock(int x, int y){
        previousBoard[x][y]=true;
    }

    public void clearBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=false;
            }
        }
    }

    public void clearPreviousBoard(){
        for(int i=0;i<previousBoard.length;i++){
            for(int j=0;j<previousBoard[i].length;j++){
                previousBoard[i][j]=false;
            }
        }
    }

    public boolean isBlockFree(int x, int y){
        if(board[x][y]==false) return true;
        else return false;
    }

    public void checkBlockLife(int x, int y){
        int numLife=0;

        for(int i=x-1;i<x+2;i++){
            if(i >= 0 && i < 21) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (j >= 0 && j < 21) {
                        if (previousBoard[i][j])
                            numLife++;
                        if (i == x && j == y && previousBoard[i][j]) numLife--;
                    }
                }
            }
        }
        if(numLife==3){
            board[x][y] = true;
        }
        else if(numLife==2 && previousBoard[x][y]){
            board[x][y]=true;
        }
        else{
            board[x][y]=false;
        }
    }

    public void checkBoardLife(){
        clearBoard();
        for(int i=0;i<previousBoard.length;i++){
            for(int j=0;j<previousBoard[i].length;j++){
                checkBlockLife(i,j);
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                previousBoard[i][j]=board[i][j];
            }
        }

    }
}
