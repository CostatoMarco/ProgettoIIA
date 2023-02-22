import java.util.ArrayList;
import java.util.List;



public class Cella {
    private int x;
    private int y;
    private List<Cella> adiacenti; 
    boolean start;
    boolean finish;
    Veicolo startCar;
    Veicolo finishCar;
    boolean occupato;
    Veicolo occupatoCar;

    public Cella(int x, int y){
        this.x = x;
        this.y = y;
        adiacenti = new ArrayList<>();
        start = false;
        finish = false;
        startCar = null;
        finishCar = null;
        occupato = false;
        occupatoCar = null;
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
    public void addStart(Veicolo v){
        start = true;
        startCar = v;
    }
    public void addFinish(Veicolo v){
        finish = true;
        finishCar = v;
    }
}
