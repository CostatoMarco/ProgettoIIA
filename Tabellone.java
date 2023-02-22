import java.util.Random;

public class Tabellone {
    private int n;
    private int M;
    private Cella[][] celle;
    private int[][] posizioni;

    public Tabellone(int n, int M, boolean[] start) throws Exception{
        if(n<M){
            throw new Exception("n deve essere più grande di M");
        }
        this.n = n;
        this.M = M;
        int cont = 0;
        for(int i = 0; i<start.length; i++){
            if(start[i]){
                cont++;
            }
        }
        if(start.length!=n || cont != M){
            throw new Exception("array di start non valido");
        }
        inizializzazioneTabellone(n,start);
    }

    public Tabellone(int n, int M) throws Exception{
        if(n<M){
            throw new Exception("n deve essere più grande di M");
        }
        this.n = n;
        this.M = M;
        boolean[] start=createBooleanArray(n, M);
        inizializzazioneTabellone(n,start);

    }

    public Tabellone(){
        Random rand = new Random();
        n = rand.nextInt(50)+1;
        M = rand.nextInt(n);
        boolean[] start = createBooleanArray(n, M);
        inizializzazioneTabellone(n,start);
    }

    private void inizializzazioneTabellone(int n,boolean[] start) {
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
        posizioni = new int[M][2];
        int id = 0;

        for(int y = 0; y<n; y++){
            if(start[y]){
                Veicolo v = new Veicolo(id);              
                celle[0][y].addStart(v);
                celle[n][n-1-y].addFinish(v);
                posizioni[id][1] = 0;
                posizioni[id][2] = y;
                id++;
            }
        }
    }

    private boolean[] createBooleanArray(int n, int m) {
        
    
        boolean[] arr = new boolean[n];
        Random rand = new Random();
    
        
        for (int i = 0; i < m; i++) {
            arr[i] = true;
        }
    
        
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            boolean temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    
        return arr;
    }
  







    public int getn(){
        return n;
    }
    public int getM(){
        return M;
    }
}
