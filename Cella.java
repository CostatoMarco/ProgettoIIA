import java.util.ArrayList;
import java.util.List;



public class Cella {
    private int x;
    private int y;
    private List<Cella> adiacenti; 
    public Cella(int x, int y){
        this.x = x;
        this.y = y;
        adiacenti = new ArrayList<>();
    }
    public void addAdiacente(Cella cella){
        adiacenti.add(cella);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public List<Cella> getAdiacenti(){
        return adiacenti;
    }
}
