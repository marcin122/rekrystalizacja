import java.awt.*;
import java.util.ArrayList;

public class PalleteColors {

    ArrayList<Color> colors=new ArrayList<>();

    public PalleteColors() {
        colors.add(Color.WHITE);

//        for(int i=0; i<255; i+=20) {
//            for(int j=0; j<255; i+=20) {
//                for(int k=0; k<255; i+=20) {
//                    colors.add(new Color(i,j,k));
//                }
//            }
//        }

        for(int i=0;i<255;i+=20)
            for(int j=0;j<255;j+=20)
                for(int k=0;k<255;k+=20)
                    colors.add(new Color(i,j,k));
    }

    public int getNumColors(){
        return colors.size();
    }

    public Color getColor(int i) {
        return colors.get(i);
    }
}
