import java.util.Random;

public class Grain {
    private int[][] board;
    private int[][] previousBoard;
    private int sasOption=0;

    private boolean periodicBC=true;


    int pos1;
    int pos2;
    int pos3;
    Random random=new Random();

    public Grain() {
        randPos();

        board=new int[21][21];
        previousBoard=new int[21][21];

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=0;
                previousBoard[i][j]=0;
            }
        }
    }

    public void randPos(){
        pos1=random.nextInt(8);
        pos2=random.nextInt(8);
        pos3=random.nextInt(8);
        while(pos1==pos2){
            pos2=random.nextInt(8);
        }
        while (pos3==pos1 || pos3==pos2){
            pos3=random.nextInt(8);
        }
    }

    public void setPeriodicBC(boolean periodicBC) {
        this.periodicBC = periodicBC;
    }

    public void setSasOption(int sasOption) {
        this.sasOption = sasOption;
    }

    public void newGrain(){
        clearBoard();
        clearPreviousBoard();
    }

    public void setBlock(int x, int y, boolean radiusOption, int colorBlock){
        if(radiusOption){
            board[x][y]=colorBlock;
            previousBoard[x][y]=colorBlock;

            int m=0;

            for(int i=y-3, n=0;i<y+4;i++,n++){
                for(int j=x-m;j<x+m+1;j++){
                    if(i==y && j==x) continue;
                    if(i>=0 && i<21 && j>=0 && j<21){
                        board[j][i]=-1;
                        previousBoard[j][i]=-1;
                    }
                }
                if(n<3) m++;
                else m--;
            }
        }
        else{
            board[x][y]=colorBlock;
            previousBoard[x][y]=colorBlock;
        }
    }

    public int numFreeBlock(){
        int num=0;

        for(int i=0;i<21;i++){
            for(int j=0;j<21;j++){
                if(board[i][j]==0) num++;
            }
        }
        return num;
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

    public boolean isBlockFree(int x, int y, boolean radiusOption){
        if(radiusOption){
            if(board[x][y]==0) return true;
            else return false;
        }
        else {
            if(board[x][y]==0 || board[x][y]==-1) return true;
            else return false;
        }
    }

    public int getBlockColor(int x, int y){
        return board[x][y];
    }

    public void Moore(int x, int y){
        int[] tab=new int[8];
        int n=0;
        for (int i=0;i<tab.length;i++) tab[i]=0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && j < 21 && i >= 0 && i < 21) {
                    if (i==x && y==j) {
                        continue;
                    }
                    tab[n] = previousBoard[i][j];
                    n++;
                }
                else{
                    n++;
                }
            }
        }
        board[x][y]=max(tab);
    }

    public void vonNeumman(int x, int y){
        int[] tab=new int[4];
        for (int i=0;i<tab.length;i++) tab[i]=0;

            if(y>0) tab[0] = previousBoard[x][y - 1];
            if(x<20) tab[1] = previousBoard[x + 1][y];
            if(y<20) tab[2] = previousBoard[x][y + 1];
            if(x>0) tab[3] = previousBoard[x - 1][y];


        board[x][y]=max(tab);
    }

    public void hexagonalLeft(int x, int y){
        int[] tab=new int[6];
        int n=0;
        for (int i=0;i<tab.length;i++) tab[i]=0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if(i==x+1 && j==y-1) continue;
                if(i==x-1 && j==y+1) continue;
                if (j >= 0 && j < 21 && i >= 0 && i < 21) {
                    if (i==x && y==j) {
                        continue;
                    }
                    tab[n] = previousBoard[i][j];
                    n++;
                }
                else{
                    n++;
                }
            }
        }
        board[x][y]=max(tab);
    }

    public void hexagonalRight(int x, int y){
        int[] tab=new int[6];
        int n=0;
        for (int i=0;i<tab.length;i++) tab[i]=0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if(i==x+1 && j==y+1) continue;
                if(i==x-1 && j==y-1) continue;
                if (j >= 0 && j < 21 && i >= 0 && i < 21) {
                    if (i==x && y==j) {
                        continue;
                    }
                    tab[n] = previousBoard[i][j];
                    n++;
                }
                else{
                    n++;
                }
            }
        }
        board[x][y]=max(tab);
    }

    public void hexagonalRand(int x, int y){


        int[] tab=new int[8];
        int n=0;
        for (int i=0;i<tab.length;i++) tab[i]=0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && j < 21 && i >= 0 && i < 21) {
                    if (i==x && y==j) {
                        continue;
                    }
                    tab[n] = previousBoard[i][j];
                    n++;
                }
                else{
                    n++;
                }
            }
        }
        tab[pos1]=0;
        tab[pos2]=0;
        board[x][y]=max(tab);
    }

    public void pentagonalRand(int x, int y){
        Random random=new Random();
        int pos=random.nextInt(4);

        int[] tab=new int[8];
        int n=0;
        for (int i=0;i<tab.length;i++) tab[i]=0;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && j < 21 && i >= 0 && i < 21) {
                    if (i==x && y==j) {
                        continue;
                    }
                    tab[n] = previousBoard[i][j];
                    n++;
                }
                else{
                    n++;
                }
            }
        }

        tab[pos1]=0;
        tab[pos2]=0;
        tab[pos3]=0;

        board[x][y]=max(tab);
    }

    public int max(int[] tab){
        int[] n=new int[tab.length];
        int maxNum,val,num,maxVal=0;

        maxNum = 0;
        for(int i = 0; i < tab.length; i++) {
            if(tab[i]!=0 && tab[i]!=-1) {
                val = tab[i];
                num = 0;

                for(int j=0;j<tab.length;j++) if(val==tab[j]) num++;

                if(maxVal<val){
                    maxNum=num;
                    maxVal=val;
                }

            }
        }
        return maxVal;
    }

    public void growthGrain(int x, int y){
        switch (sasOption){
            case 1:
                vonNeumman(x,y);
                break;
            case 2:
                Moore(x,y);
                break;
            case 3:
                hexagonalLeft(x,y);
                break;
            case 4:
                hexagonalRight(x,y);
                break;
            case 5:
                hexagonalRand(x,y);
                break;
            case 6:
                pentagonalRand(x,y);
                break;
        }

    }


    public void checkBlockLife(int x, int y){
        if(previousBoard[x][y]==0 || previousBoard[x][y]==-1) {
            growthGrain(x,y);
        }
        else{
            board[x][y]=previousBoard[x][y];
        }
    }

    public void checkBoardLife(){
        randPos();

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
