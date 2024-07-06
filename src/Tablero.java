import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tablero implements Serializable {
    private  List<Celda> celdas;
    private  boolean juegoTerminado;
    private  FaseJuego faseActual;
    private  Celda aux = new Celda(-1, -1);
    private  int Naux = -1;


    public Tablero() {
        celdas = new ArrayList<>();
        inicializarCeldas();
        establecerVecinos();
        juegoTerminado = false;
        faseActual = FaseJuego.COLOCACION;
    }


    public  boolean getjuegoTerminado() {
        return juegoTerminado;
    }

    public FaseJuego getFaseActual() {
        return faseActual;
    }

    private  void inicializarCeldas() {
        celdas.add(new Celda(0, 0));//0
        celdas.add(new Celda(0, 3));//1
        celdas.add(new Celda(0, 6));//2
        celdas.add(new Celda(1, 1));//3
        celdas.add(new Celda(1, 3));//4
        celdas.add(new Celda(1, 5));//5
        celdas.add(new Celda(2, 2));//6
        celdas.add(new Celda(2, 3));//7
        celdas.add(new Celda(2, 4));//8
        celdas.add(new Celda(3, 0));//9
        celdas.add(new Celda(3, 1));//10
        celdas.add(new Celda(3, 2));//11
        celdas.add(new Celda(3, 4));//12
        celdas.add(new Celda(3, 5));//13
        celdas.add(new Celda(3, 6));//14
        celdas.add(new Celda(4, 2));//15
        celdas.add(new Celda(4, 3));//16
        celdas.add(new Celda(4, 4));//17
        celdas.add(new Celda(5, 1));//18
        celdas.add(new Celda(5, 3));//19
        celdas.add(new Celda(5, 5));//20
        celdas.add(new Celda(6, 0));//21
        celdas.add(new Celda(6, 3));//22
        celdas.add(new Celda(6, 6));//23
    }

    private  void establecerVecinos() {
        // Celda 0
        celdas.get(0).agregarVecino(celdas.get(1));
        celdas.get(0).agregarVecino(celdas.get(2));
        celdas.get(0).agregarVecino(celdas.get(9));
        celdas.get(0).agregarVecino(celdas.get(21));

        // Celda 1
        celdas.get(1).agregarVecino(celdas.get(0));
        celdas.get(1).agregarVecino(celdas.get(2));
        celdas.get(1).agregarVecino(celdas.get(4));
        celdas.get(1).agregarVecino(celdas.get(7));

        // Celda 2
        celdas.get(2).agregarVecino(celdas.get(0));
        celdas.get(2).agregarVecino(celdas.get(1));
        celdas.get(2).agregarVecino(celdas.get(14));
        celdas.get(2).agregarVecino(celdas.get(23));

        // Celda 3
        celdas.get(3).agregarVecino(celdas.get(4));
        celdas.get(3).agregarVecino(celdas.get(5));
        celdas.get(3).agregarVecino(celdas.get(10));
        celdas.get(3).agregarVecino(celdas.get(18));

        // Celda 4
        celdas.get(4).agregarVecino(celdas.get(3));
        celdas.get(4).agregarVecino(celdas.get(5));
        celdas.get(4).agregarVecino(celdas.get(1));
        celdas.get(4).agregarVecino(celdas.get(7));

        // Celda 5
        celdas.get(5).agregarVecino(celdas.get(3));
        celdas.get(5).agregarVecino(celdas.get(4));
        celdas.get(5).agregarVecino(celdas.get(13));
        celdas.get(5).agregarVecino(celdas.get(20));

        // Celda 6
        celdas.get(6).agregarVecino(celdas.get(7));
        celdas.get(6).agregarVecino(celdas.get(8));
        celdas.get(6).agregarVecino(celdas.get(11));
        celdas.get(6).agregarVecino(celdas.get(15));


        // Celda 7
        celdas.get(7).agregarVecino(celdas.get(6));
        celdas.get(7).agregarVecino(celdas.get(8));
        celdas.get(7).agregarVecino(celdas.get(1));
        celdas.get(7).agregarVecino(celdas.get(4));

        // Celda 8
        celdas.get(8).agregarVecino(celdas.get(6));
        celdas.get(8).agregarVecino(celdas.get(7));
        celdas.get(8).agregarVecino(celdas.get(12));
        celdas.get(8).agregarVecino(celdas.get(17));

        // Celda 9
        celdas.get(9).agregarVecino(celdas.get(10));
        celdas.get(9).agregarVecino(celdas.get(11));
        celdas.get(9).agregarVecino(celdas.get(0));
        celdas.get(9).agregarVecino(celdas.get(21));

        // Celda 10
        celdas.get(10).agregarVecino(celdas.get(9));
        celdas.get(10).agregarVecino(celdas.get(11));
        celdas.get(10).agregarVecino(celdas.get(3));
        celdas.get(10).agregarVecino(celdas.get(18));

        // Celda 11
        celdas.get(11).agregarVecino(celdas.get(9));
        celdas.get(11).agregarVecino(celdas.get(10));
        celdas.get(11).agregarVecino(celdas.get(6));
        celdas.get(11).agregarVecino(celdas.get(15));

        // Celda 12
        celdas.get(12).agregarVecino(celdas.get(13));
        celdas.get(12).agregarVecino(celdas.get(14));
        celdas.get(12).agregarVecino(celdas.get(8));
        celdas.get(12).agregarVecino(celdas.get(17));

        // Celda 13
        celdas.get(13).agregarVecino(celdas.get(12));
        celdas.get(13).agregarVecino(celdas.get(14));
        celdas.get(13).agregarVecino(celdas.get(5));
        celdas.get(13).agregarVecino(celdas.get(20));

        // Celda 14
        celdas.get(14).agregarVecino(celdas.get(12));
        celdas.get(14).agregarVecino(celdas.get(13));
        celdas.get(14).agregarVecino(celdas.get(2));
        celdas.get(14).agregarVecino(celdas.get(23));

        // Celda 15
        celdas.get(15).agregarVecino(celdas.get(16));
        celdas.get(15).agregarVecino(celdas.get(17));
        celdas.get(15).agregarVecino(celdas.get(6));
        celdas.get(15).agregarVecino(celdas.get(11));

        // Celda 16
        celdas.get(16).agregarVecino(celdas.get(15));
        celdas.get(16).agregarVecino(celdas.get(17));
        celdas.get(16).agregarVecino(celdas.get(19));
        celdas.get(16).agregarVecino(celdas.get(22));

        // Celda 17
        celdas.get(17).agregarVecino(celdas.get(15));
        celdas.get(17).agregarVecino(celdas.get(16));
        celdas.get(17).agregarVecino(celdas.get(8));
        celdas.get(17).agregarVecino(celdas.get(12));

        // Celda 18
        celdas.get(18).agregarVecino(celdas.get(19));
        celdas.get(18).agregarVecino(celdas.get(20));
        celdas.get(18).agregarVecino(celdas.get(3));
        celdas.get(18).agregarVecino(celdas.get(10));

        // Celda 19
        celdas.get(19).agregarVecino(celdas.get(18));
        celdas.get(19).agregarVecino(celdas.get(20));
        celdas.get(19).agregarVecino(celdas.get(16));
        celdas.get(19).agregarVecino(celdas.get(22));

        // Celda 20
        celdas.get(20).agregarVecino(celdas.get(18));
        celdas.get(20).agregarVecino(celdas.get(19));
        celdas.get(20).agregarVecino(celdas.get(5));
        celdas.get(20).agregarVecino(celdas.get(13));

        // Celda 21
        celdas.get(21).agregarVecino(celdas.get(22));
        celdas.get(21).agregarVecino(celdas.get(23));
        celdas.get(21).agregarVecino(celdas.get(0));
        celdas.get(21).agregarVecino(celdas.get(9));

        // Celda 22
        celdas.get(22).agregarVecino(celdas.get(21));
        celdas.get(22).agregarVecino(celdas.get(23));
        celdas.get(22).agregarVecino(celdas.get(16));
        celdas.get(22).agregarVecino(celdas.get(19));

        // Celda 23
        celdas.get(23).agregarVecino(celdas.get(21));
        celdas.get(23).agregarVecino(celdas.get(22));
        celdas.get(23).agregarVecino(celdas.get(2));
        celdas.get(23).agregarVecino(celdas.get(14));
    }

    public  Object getCeldas() {
        return celdas;
    }

    public  boolean colocarFicha(int indice, Jugador actual, Jugador noActual) {
        if (actual.getFichasDisponibles() > 0 && celdas.get(indice).estaVacia()) {
            celdas.get(indice).setValor(actual.getSimbolo());
            actual.disminuirFichasDisponibles();
            actual.aumentarFichasEnTablero();
            if (comprobarMolino(indice,actual)) {
                faseActual = FaseJuego.ELIMINACION;
            } else if (actual.getFichasDisponibles() == 0 && noActual.getFichasDisponibles() == 0) {
                faseActual = FaseJuego.MOVIMIENTO;
            }
            return true;
        }
        return false;
    }

    public int MoverFicha(int indice, Jugador actual) {
        if (aux.getX() == -1){
            if (celdas.get(indice).estaVacia() || celdas.get(indice).getValor() != actual.getSimbolo()){
                return  0;
            }else{
                aux = new Celda(celdas.get(indice).getX(),celdas.get(indice).getY());
                aux.setValor(celdas.get(indice).getValor());
                aux.setVecinos(celdas.get(indice).getVecinos());
                aux.setX(celdas.get(indice).getX());
                aux.setY(celdas.get(indice).getY());
                Naux = indice;
                return 1;
            }
        }else{
            if (aux.getX() == celdas.get(indice).getX() && aux.getY() == celdas.get(indice).getY()){
                aux.setX(-1);
                return 2;
            }
            else if(aux.getValor() == celdas.get(indice).getValor()){
                return 0;
            }else if(MovimientoValida(aux,celdas.get(indice))){
                celdas.get(Naux).setValor('-');
                celdas.get(indice).setValor(actual.getSimbolo());
                Naux = -1;
                aux.setX(-1);
                if(comprobarMolino(indice,actual)){
                    faseActual = FaseJuego.ELIMINACION;
                }
            }else{
                return 0;
            }
            return 1;
        }
    }

    public  boolean Eliminar(int indice,Jugador actual,Jugador noActual) {
        boolean retorno;
        if (actual.getSimbolo() != celdas.get(indice).getValor() && '-' != celdas.get(indice).getValor())
        {
            celdas.get(indice).setValor('-');
            noActual.DisminuirFichasEnTablero(); //cambiar
            if(actual.getFichasEnTablero() == 0 || noActual.getFichasEnTablero() == 0 ) {
                juegoTerminado = true;
            }else if(actual.getFichasDisponibles() == 0 && noActual.getFichasDisponibles() == 0 )
            {
                faseActual = FaseJuego.MOVIMIENTO;
            }else{
                faseActual = FaseJuego.COLOCACION;
            }
            retorno = true;
        }else{
            retorno = false;
        }
        return retorno;
    }

    private  boolean comprobarMolino(int indice, Jugador actual) {
        Celda celda = celdas.get(indice);
        boolean molino = false;
        if (
                (celda.getVecinos().get(0).getValor() == actual.getSimbolo() &&
                        celda.getVecinos().get(1).getValor() == actual.getSimbolo()
                )
                        ||
                        (celda.getVecinos().get(2).getValor() == actual.getSimbolo() &&
                                celda.getVecinos().get(3).getValor() == actual.getSimbolo())
        ){
            molino = true;
        }
        return molino;
    }

    public boolean MovimientoValida(Celda casillaInicio, Celda casillaObjetivo){
        boolean valido = false;
        int numvecino = esVecino(casillaInicio,casillaObjetivo);
        int mayor;
        int menor;
        if (casillaObjetivo.estaVacia() && numvecino != -1){ //verificar qeu no puedas mover una vacia
            if(numvecino < 2){
                mayor = mayor(casillaInicio.getY(),casillaObjetivo.getY());
                menor = menor(casillaInicio.getY(),casillaObjetivo.getY());
                if ((mayor - menor) == 3){
                    valido = true;
                }else if((mayor - menor) == 2 && (casillaInicio.getY() == 1 || casillaInicio.getY() == 3 || casillaInicio.getY() == 5)){
                    valido = true;
                }else if((mayor - menor) == 1){
                    valido = true;
                }
            }else{
                mayor = mayor(casillaInicio.getX(),casillaObjetivo.getX());
                menor = menor(casillaInicio.getX(),casillaObjetivo.getX());
                if ((mayor - menor) == 3){
                    valido = true;
                }else if(((mayor - menor) == 2) && (casillaInicio.getX() == 1 || casillaInicio.getX() == 3 || casillaInicio.getX() == 5)){
                    valido = true;
                }else if((mayor - menor) == 1){
                    valido = true;
                }
            }
        }
        return valido;
    }

    private  int mayor(int a, int b){
        if (a > b){
            return a;
        }else{
            return b;
        }
    }

    private int menor(int a, int b){
        if (a < b){
            return a;
        }else{
            return b;
        }
    }

    private  int esVecino(Celda casillaInicio, Celda casillaObjetivo){ //me da alrevez horinzontal y vertical
        int vecino = -1;
        int x1;
        int y1;
        int x2 = casillaObjetivo.getX();
        int y2 = casillaObjetivo.getY();
        for(int i = 0;i<casillaInicio.getVecinos().size();i++){//cambiar
            x1 = casillaInicio.getVecinos().get(i).getX();
            y1 = casillaInicio.getVecinos().get(i).getY();
            if (x1 == x2 && y1 == y2){
                vecino = i;
            }
        }
        return vecino;
    }

}
