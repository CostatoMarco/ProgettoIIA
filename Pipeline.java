public class Pipeline{
    public static void main (String[] args){
        Tabellone tabellone;
        boolean[] start = {true,true,false};
        try{
         tabellone = new Tabellone(3,2,start);
         int x = tabellone.getPosizioni()[1].getX();
         int y = tabellone.getPosizioni()[1].getY();
         System.out.println(""+x+":"+y); 
         int[][] ids = {{1,2}};
         Tabellone spostato = tabellone.getNextStato(ids);
         spostato = spostato.getNextStato(ids);
         x = spostato.getPosizioni()[0].getX();
         y = spostato.getPosizioni()[0].getY();
         System.out.println(""+x+":"+y);
         System.out.println(spostato.checkWinCon());
        }
        catch(Exception e) {
            System.out.println("Ops");
            System.exit(1);
        }
    }
}