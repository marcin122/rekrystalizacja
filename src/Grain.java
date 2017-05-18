
public class Grain {
    private int[][] board;
    private int[][] previousBoard;
    private int[][] color;

    public Grain() {
        board=new int[21][21];
        previousBoard=new int[21][21];
        color=new int[21][21];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=0;
                previousBoard[i][j]=0;
                color[i][j]=0;
            }
        }
    }

    public void newGrain(){
        clearBoard();
        clearPreviousBoard();
    }

    public void setBlock(int x, int y, boolean radiusOption, int color){
        if(radiusOption){
            board[x][y]=1;

            int m=0;

            for(int i=y-3, n=0;i<y+4;i++,n++){
                for(int j=x-m;j<x+m+1;j++){
                    if(i==y && j==x) continue;
                    if(i>=0 && i<21 && j>=0 && j<21)
                        board[j][i]=2;
                }
                if(n<3) m++;
                else m--;
            }
//            for(int i=0;i<21;i++) {
//                for (int j = 0; j < 21; j++)
//                    System.out.print(board[j][i]);
//                System.out.println();
//            }
        }
        else{
            board[x][y]=1;
        }
    }

    public void clearBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=0;
            }
        }
    }

    public void clearPreviousBoard(){
        for(int i=0;i<previousBoard.length;i++){
            for(int j=0;j<previousBoard[i].length;j++){
                previousBoard[i][j]=0;
            }
        }
    }

    public boolean isBlockFree(int x, int y){
        if(board[x][y]==0) return true;
        else return false;
    }


    public void checkBlockLife(int x, int y){
        int numLife=0;

//        for(int i=x-1;i<x+2;i++){
//            if(i >= 0 && i < 21) {
//                for (int j = y - 1; j < y + 2; j++) {
//                    if (j >= 0 && j < 21) {
//                        if (previousBoard[i][j])
//                            numLife++;
//                        if (i == x && j == y && previousBoard[i][j]) numLife--;
//                    }
//                }
//            }
//        }
//        if(numLife==3){
//            board[x][y] = true;
//        }
//        else if(numLife==2 && previousBoard[x][y]){
//            board[x][y]=true;
//        }
//        else{
//            board[x][y]=false;
//        }
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
