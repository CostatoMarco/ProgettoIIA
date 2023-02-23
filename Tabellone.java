import java.util.Random;

public class Tabellone {
    private int n;
    private int M;
    private Cella[][] celle;
    private Cella[] posizioni;
    private Cella[] fine;

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
        if(M==0){
            M=1;
        }
        boolean[] start = createBooleanArray(n, M);
        inizializzazioneTabellone(n,start);
    }

    public Tabellone(Tabellone altro){
        this.n = altro.getn();
        this.M = altro.getM();
        this.celle = altro.getCelle();
        this.posizioni = altro.getPosizioni();
        this.fine = altro.getFine();

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
        posizioni = new Cella[M];
        fine = new Cella[M];
        int id = 0;

        for(int y = 0; y<n; y++){
            if(start[y]){
                Veicolo v = new Veicolo(id);              
                celle[0][y].addStart(v);
                celle[n-1][n-1-y].addFinish(v);
                posizioni[id]= celle[0][y];
                fine[id] = celle[n-1][n-1-y];
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

    
    public Tabellone getNextStato(int id, char d, Tabellone in){
        Cella cella = in.posizioni[id];
        Veicolo v = cella.getCar();
        Tabellone ret = new Tabellone(in);
        if(d == 'n'){
            return ret;
        }
        else if(d == 'u'){
            Cella su = ret.celle[cella.getX()][cella.getY()-1];
            if(cella.getAdiacenti().contains(su)){
                su.setOccupato(v);
                ret.celle[cella.getX()][cella.getY()-1] = su;
                cella.freeCella();
                ret.celle[cella.getX()][cella.getY()] = cella;
                ret.posizioni[id]=su;
            }
            return ret;
        }
        else if(d == 'r'){
            Cella destra = ret.celle[cella.getX()+1][cella.getY()];
            if(cella.getAdiacenti().contains(destra)){
                destra.setOccupato(v);
                ret.celle[cella.getX()+1][cella.getY()] = destra;
                cella.freeCella();
                ret.celle[cella.getX()][cella.getY()]=cella;
                ret.posizioni[id]=destra;
            }
            return ret;
        }
        else if(d == 'd'){
            Cella giu = ret.celle[cella.getX()][cella.getY()+1];
            if(cella.getAdiacenti().contains(giu)){
                giu.setOccupato(v);
                ret.celle[cella.getX()][cella.getY()+1] = giu;
                cella.freeCella();
                ret.celle[cella.getX()][cella.getY()]=cella;
                ret.posizioni[id]=giu;
            }
            return ret;
        }
        else {
            Cella sinistra = ret.celle[cella.getX()-1][cella.getY()];
            if(cella.getAdiacenti().contains(sinistra)){
                sinistra.setOccupato(v);
                ret.celle[cella.getX()-1][cella.getY()] = sinistra;
                cella.freeCella();
                ret.celle[cella.getX()][cella.getY()]=cella;
                ret.posizioni[id]=sinistra;
            }
            return ret;
        }
    }


    public Tabellone getNextStato(int[][] ids){
        Tabellone ret = new Tabellone(this);
        for(int i=0; i<ids.length;i++){
            if (ids[i][1]==0){
                ret=getNextStato(ids[i][0],'n',ret);
            }
            else if(ids[i][1]==1){
                ret=getNextStato(ids[i][0],'u',ret);
            }
            else if(ids[i][1]==2){
                ret=getNextStato(ids[i][0],'r',ret);
            }
            else if(ids[i][1]==3){
                ret=getNextStato(ids[i][0],'d',ret);
            }
            else{
                ret=getNextStato(ids[i][0],'l',ret);
            }
        }
        return ret;
    }


    public int getEuristicaStato(){
        int tmp = 0;
        for(int i=0; i<M; i++){
           tmp = tmp + (int)((Math.abs(posizioni[i].getX()-fine[i].getX()) + Math.abs(posizioni[i].getY()-fine[i].getY()))/2); 
        }
        return tmp;
    }


    public int getn(){
        return n;
    }
    public int getM(){
        return M;
    }
    public Cella[][] getCelle(){
        return celle;
    }
    public Cella[] getPosizioni(){
        return posizioni;
    }
    public Cella[] getFine(){
        return fine;
    }
}
