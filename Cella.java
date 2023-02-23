import java.util.ArrayList;
import java.util.List;



public class Cella {
    private int x;
    private int y;
    private List<Cella> adiacenti; 
    private boolean start;
    private boolean finish;
    private Veicolo startCar;
    private Veicolo finishCar;
    private boolean occupato;
    private Veicolo occupatoCar;

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
    public Veicolo getCar(){
        return occupatoCar;
    }

    public List<Cella> getAdiacenti(){
        return adiacenti;
    }
    public void addStart(Veicolo v){
        start = true;
        startCar = v;
        occupato = true;
        occupatoCar = v;
    }
    public void addFinish(Veicolo v){
        finish = true;
        finishCar = v;
    }
    public void setOccupato(Veicolo v){
        occupato = true;
        occupatoCar = v;
    }
    public void freeCella(){
        occupato = false;
        occupatoCar = null;
    }
    public boolean getFinish(){
        return finish;
    }
    public boolean getStart(){
        return start;
    }
    public Veicolo getStartCar(){
        return startCar;
    }
    public Veicolo getFinishCar(){
        return finishCar;
    }
}
