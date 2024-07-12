import java.io.Serializable;
import java.util.ArrayList;

public class cambio implements Serializable {
    private FaseJuego fj;
    private Jugador ja;
    private Jugador jna;
    private Jugador Aux;
    private ArrayList<Celda> celdas;
    private int idtablero;

    public cambio(FaseJuego fj,ArrayList<Celda> celdas,Jugador ja){
        this.fj = fj;
        this.celdas =celdas;
        this.ja = ja;
    }

    public Jugador getAux() {
        return Aux;
    }

    public void setAux(Jugador aux) {
        Aux = aux;
    }


    public FaseJuego getFj() {
        return fj;
    }

    public void setFj(FaseJuego fj) {
        this.fj = fj;
    }


    public Jugador getJna() {
        return jna;
    }

    public void setJna(Jugador jna) {
        this.jna = jna;
    }

    public int getIdtablero() {
        return idtablero;
    }

    public void setIdtablero(int idtablero) {
        this.idtablero = idtablero;
    }



    public Jugador getJa() {
        return ja;
    }

    public void setJa(Jugador ja) {
        this.ja = ja;
    }

    public ArrayList<Celda> getCeldas() {
        return celdas;
    }

    public void setCeldas(ArrayList<Celda> celdas) {
        this.celdas = celdas;
    }


}
