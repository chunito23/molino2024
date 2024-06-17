import java.util.ArrayList;
import java.util.List;

public class ModeloMolino {
    private List<Celda> celdas;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private List<ObservadorMolino> observadores;
    private boolean juegoTerminado;
    private FaseJuego faseActual;
    private ArrayList<Object> cambios = new ArrayList<>();
    private Celda celdaAux = new Celda(-1,-1);

    public ModeloMolino() {
        celdas = new ArrayList<>();
        jugador1 = new Jugador('*');
        jugador2 = new Jugador('/');
        jugadorActual = jugador1;
        observadores = new ArrayList<>();
        juegoTerminado = false;
        faseActual = FaseJuego.COLOCACION;
        inicializarCeldas();
        establecerVecinos();
        //estado inicial de la partida
        cambios.add(celdas);
        cambios.add(jugadorActual);
        cambios.add(faseActual);
    }

    private void inicializarCeldas() {
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

    private void establecerVecinos() {
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

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        if (jugador1.getFichasDisponibles() == 0 && jugador2.getFichasDisponibles() == 0) {
            faseActual = FaseJuego.MOVIMIENTO;
        }
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void agregarObservador(ObservadorMolino observador) {
        observadores.add(observador);
    }


    public void setCambios(ArrayList<Object> cambios) {
        this.cambios = cambios;
    }

    public void notificarObservadores(Object cambios) {
        System.out.println(faseActual + " " + jugadorActual.getSimbolo());
        for (ObservadorMolino observador : observadores) {
            observador.actualizar(cambios);
        }
    }

    public boolean hacerMovimiento(int indice) { //las celdas
        if (indice < 0 || indice >= 24 || juegoTerminado) {
            return false;
        }
        if (faseActual == FaseJuego.COLOCACION) {
            return colocarFicha(indice);
        } else if (faseActual == FaseJuego.MOVIMIENTO) {
            return MoverFicha(indice);
        } else if (faseActual == FaseJuego.ELIMINACION) {
            return Elimnar(indice);
        }
        return false;
    }

    private boolean colocarFicha(int indice) {
        if (jugadorActual.getFichasDisponibles() > 0 && jugadorActual.getSimbolo() != celdas.get(indice).getValor())
        {
            celdas.get(indice).setValor(jugadorActual.getSimbolo());
            jugadorActual.disminuirFichasDisponibles();
            jugadorActual.aumentarFichasEnTablero();
            if (comprobarMolino(indice)) {
                faseActual = FaseJuego.ELIMINACION;
            } else {
                cambiarJugador();
            };

            notificarObservadores(cambios);
            return true;
        }
        return false;
    }

    private boolean MoverFicha(int indice) {
        if(celdaAux.getX() == -1){
            celdaAux = celdas.get(indice);
            celdas.get(indice).setValor('-');
        }else{
            celdas.get(indice).setValor(celdaAux.getValor());
            celdaAux.setX(-1);
            cambiarJugador();
        }
        notificarObservadores(cambios);
        return true;
    }

    private boolean Elimnar(int indice) {
        boolean retorno;
        System.out.println("entra");
            if (jugadorActual.getSimbolo() != celdas.get(indice).getValor() && '-' != celdas.get(indice).getValor()){
                celdas.get(indice).setValor('-');
                if (jugadorActual == jugador1)
                {
                    jugador1.DisminuirFichasEnTablero();
                }else{
                    jugador2.DisminuirFichasEnTablero();
                }
                if(jugador1.getFichasDisponibles() == 0 && jugador2.getFichasDisponibles() == 0 )
                {
                    faseActual = FaseJuego.MOVIMIENTO;
                }else if(jugador1.getFichasEnTablero() == 0 && jugador2.getFichasEnTablero() == 0 ){
                    juegoTerminado = true;
                }else{
                    faseActual = FaseJuego.COLOCACION;
                }
                cambiarJugador();
                retorno = true;
            }else{
                retorno = false;
            }
            notificarObservadores(cambios);
            return retorno;

    }

    private boolean comprobarMolino(int indice) {
        Celda celda = celdas.get(indice);
        boolean molino = false;
        if (
                (celda.getVecinos().get(0).getValor() == jugadorActual.getSimbolo() &&
                 celda.getVecinos().get(1).getValor() == jugadorActual.getSimbolo()
                )
                ||
                (celda.getVecinos().get(2).getValor() == jugadorActual.getSimbolo() &&
                celda.getVecinos().get(3).getValor() == jugadorActual.getSimbolo())
        ) molino = true;
        return molino;
    }

    public FaseJuego getFaseActual() {
        return faseActual;
    }
}