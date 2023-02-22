import java.util.Random;

public class Tabellone {
    private int n;
    private int M;
    private Cella[][] celle;

    public Tabellone(int n, int M){
        this.n = n;
        this.M = M;
        celle = new Cella[n][n];
        for (int x=0; x<n;x++){
            for (int y=0; y<n; y++){
                celle[x][y] = new Cella(x,y);
            }
        }
        for(int x=0; x<n;x++){
            for(int y=0; y<n; y++){
                if(x!=0){
                    celle[x][y].addAdiacente(celle[x-1][y]);
                }
                if(x!=n-1){
                    celle[x][y].addAdiacente(celle[x+1][y]);
                }
                if(y!=0){
                    celle[x][y].addAdiacente(celle[x][y-1]);
                }
                if(y!=n-1){
                    celle[x][y].addAdiacente(celle[x][y+1]);
                }
            }
        }
    }
    public Tabellone(){
        Random rand = new Random();
        n = rand.nextInt(50)+1;
        M = rand.nextInt(n);
    }
    public int getn(){
        return n;
    }
    public int getM(){
        return M;
    }
}
