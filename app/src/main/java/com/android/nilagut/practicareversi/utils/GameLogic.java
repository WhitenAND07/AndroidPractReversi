package com.android.nilagut.practicareversi.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameLogic implements Parcelable {

    public static final Creator<GameLogic> CREATOR = new Creator<GameLogic>() {
        @Override
        public GameLogic createFromParcel(Parcel parcel) {
            return new GameLogic(parcel);
        }

        @Override
        public GameLogic[] newArray(int mida) {
            return new GameLogic[mida];
        }
    };

    public long temps;
    boolean tempsacabat = false;
    private int torn = 1;
    private int[][] tauler;
    private int mida;
    private List<Integer> casellesUsuari;
    private List<Integer> casellesPC;
    private List<Integer> casellesPossibles = new ArrayList<>();
    private HashMap<Integer, List<Integer>> casellesacanviar = new HashMap<>();
    private CountDown crono;

    public GameLogic(int mida) {
        this.mida = mida;
        this.tauler = new int[mida][mida];
    }

    private GameLogic(Parcel parcel) {
        torn = parcel.readInt();
        mida = parcel.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(tauler);
        parcel.writeInt(mida);
        parcel.writeInt(torn);
        parcel.writeList(casellesUsuari);
        parcel.writeList(casellesPC);
    }


    public void CrearTauler(boolean ambTemps, int temps) {
        casellesUsuari = new ArrayList<>();
        casellesPC = new ArrayList<>();
        for (int i = 0; i < this.mida; i++) {
            for (int j = 0; j < this.mida; j++) {
                this.tauler[i][j] = 0;
            }
        }
        movimentsInicials();
        getpossiblesPosicions();
        if (ambTemps) {
            crono = new CountDown(temps * opcions.SEGON, opcions.SEGON,this);
            crono.start();
        } else {
            this.temps = System.currentTimeMillis() / opcions.SEGON;
        }
    }

    private void movimentsInicials() {
        int meitatTauler = this.mida / 2;
        this.tauler[meitatTauler - 1][meitatTauler - 1] = 1;
        this.tauler[meitatTauler - 1][meitatTauler] = 2;
        this.tauler[meitatTauler][meitatTauler - 1] = 2;
        this.tauler[meitatTauler][meitatTauler] = 1;
        casellesUsuari.add((meitatTauler - 1) * mida + (meitatTauler - 1));
        casellesUsuari.add((meitatTauler * mida) + (meitatTauler));
        casellesPC.add((meitatTauler - 1) * mida + (meitatTauler));
        casellesPC.add((meitatTauler * mida) + (meitatTauler - 1));

    }

    void omplirCasella(int posicio){
        if(this.torn == 1){
            if(this.casellesPC.contains(posicio)){
                this.casellesPC.remove(casellesPC.indexOf(posicio));
            }
            if (!this.casellesUsuari.contains(posicio)){
                this.casellesUsuari.add(posicio);
                tauler[posicio / mida][posicio % mida] = opcions.jugador1;
            }
        }
        else{
            if(this.casellesUsuari.contains(posicio)){
                this.casellesUsuari.remove(casellesUsuari.indexOf(posicio));
            }
            if(!this.casellesPC.contains(posicio)){
                this.casellesPC.add(posicio);
                tauler[posicio / mida][posicio % mida] = opcions.jugador2;
            }
        }
    }

    void canviTorn() {
        if (torn == 1) this.torn = 2;
        else this.torn = 1;
    }

    private int caniJugador() {
        if (torn == 1) return 2;
        else return 1;
    }

    List<Integer> getCasellesUsuari(){
        return casellesUsuari;
    }

    List<Integer> getCasellesPC(){
        return casellesPC;
    }

    List<Integer> getCasellesAcanviar(int posicio){
        return casellesacanviar.get(posicio);
    }

    List<Integer> possiblesPosicionsCaselles(){
        return casellesPossibles;
    }

    int obtenirTemps() {
        return (int) crono.getTime();
    }

    void getpossiblesPosicions() {
        casellesPossibles.clear();
        casellesacanviar.clear();
        for (int i = 0; i < this.mida; i++) {
            for (int j = 0; j < this.mida; j++) {
                if (movimentValid(i, j, torn)) {
                    casellesPossibles.add((i * mida) + j);
                }
            }
        }
    }

    private boolean movimentValid(int fila, int columna, int jugador) {
        if (fila == 0 && columna == 0) {
            return celabuida(fila, columna) && (tiradaDreta(fila, columna, jugador) ||
                    tiradaBaixDreta(fila, columna, jugador) || tiradaBaix(fila, columna, jugador));
        } else if (fila == 0 && columna == mida - 1) {
            return celabuida(fila, columna) && (tiradaEsquerra(fila, columna, jugador) ||
                    tiradaBaixEsquerra(fila, columna, jugador) || tiradaBaix(fila, columna, jugador));
        } else if (fila == mida - 1 && columna == 0) {
            return celabuida(fila, columna) && (tiradaDalt(fila, columna, jugador) ||
                    tiradaDaltDreta(fila, columna, jugador) || tiradaDreta(fila, columna, jugador));
        } else if (fila == mida - 1 && columna == mida - 1) {
            return celabuida(fila, columna) && (tiradaDalt(fila, columna, jugador) ||
                    tiradaDaltEsquerra(fila, columna, jugador) || tiradaEsquerra(fila, columna, jugador));
        } else if (fila == 0) {
            return celabuida(fila, columna) && (tiradaEsquerra(fila, columna, jugador) ||
                    tiradaBaixEsquerra(fila, columna, jugador) || tiradaBaix(fila, columna, jugador) ||
                    tiradaBaixDreta(fila, columna, jugador) || tiradaDreta(fila, columna, jugador));
        } else if (fila == mida - 1) {
            return celabuida(fila, columna) && (tiradaEsquerra(fila, columna, jugador) ||
                    tiradaDaltEsquerra(fila, columna, jugador) || tiradaDalt(fila, columna, jugador) ||
                    tiradaDaltDreta(fila, columna, jugador) || tiradaDreta(fila, columna, jugador));
        } else if (columna == 0) {
            return celabuida(fila, columna) && (tiradaDalt(fila, columna, jugador) ||
                    tiradaDaltDreta(fila, columna, jugador) || tiradaDreta(fila, columna, jugador) ||
                    tiradaBaixDreta(fila, columna, jugador) || tiradaBaix(fila, columna, jugador));
        } else if (columna == mida - 1) {
            return celabuida(fila, columna) && (tiradaDalt(fila, columna, jugador) ||
                    tiradaDaltEsquerra(fila, columna, jugador) || tiradaEsquerra(fila, columna, jugador) ||
                    tiradaBaixEsquerra(fila, columna, jugador) || tiradaBaix(fila, columna, jugador));
        } else {
            return celabuida(fila, columna) && (tiradaDalt(fila, columna, jugador) ||
                    tiradaDaltDreta(fila, columna, jugador) || tiradaDreta(fila, columna, jugador) ||
                    tiradaBaixDreta(fila, columna, jugador) || tiradaBaix(fila, columna, jugador) ||
                    tiradaBaixEsquerra(fila, columna, jugador) || tiradaEsquerra(fila, columna, jugador) ||
                    tiradaDaltEsquerra(fila, columna, jugador));
        }
    }

    private boolean tiradaDaltEsquerra(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila][columna - 1] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int i = fila - 1, j = columna - 1; i >= 0 && j >= 0 && !trobat; i--, j--) {
            cami.add(i * mida + j);
            if (tauler[i][j] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[i][j] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }


    private boolean tiradaDaltDreta(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila - 1][columna + 1] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int i = fila - 1, j = columna + 1; i >= 0 && j < mida && !trobat; i--, j++) {
            cami.add(i * mida + j);
            if (tauler[i][j] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[i][j] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean tiradaDalt(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila - 1][columna] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int i = fila - 1; i >= 0 && !trobat; i--) {
            cami.add(i * mida + columna);
            if (tauler[i][columna] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[i][columna] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean tiradaBaixEsquerra(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila + 1][columna - 1] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int i = fila + 1, j = columna - 1; i < mida && j >= 0 && !trobat; i++, j--) {
            cami.add(i * mida + j);
            if (tauler[i][j] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[i][j] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean tiradaEsquerra(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila][columna - 1] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int j = columna - 1; j >= 0 && !trobat; j--) {
            cami.add(fila * mida + j);
            if (tauler[fila][j] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[fila][j] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean tiradaBaix(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila + 1][columna] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int i = fila + 1; i < mida && !trobat; i++) {
            cami.add(i * mida + columna);
            if (tauler[i][columna] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[i][columna] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean tiradaBaixDreta(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila + 1][columna + 1] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int i = fila + 1, j = columna + 1; i < mida && j > mida && !trobat; i++, j++) {
            cami.add(i * mida + j);
            if (tauler[i][j] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[i][j] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean tiradaDreta(int fila, int columna, int jugador) {
        boolean condicio1 = tauler[fila][columna + 1] == caniJugador();
        boolean condicio2 = false;
        List<Integer> cami = new ArrayList<>();
        boolean trobat = false;
        cami.add(fila * mida + columna);
        for (int j = columna + 1; j < mida && !trobat; j++) {
            cami.add(fila * mida + columna);
            if (tauler[fila][j] == jugador && condicio1) {
                condicio2 = true;
                ferCami(cami, fila * mida + columna);
                trobat = true;
            } else if (tauler[fila][j] == 0) {
                break;
            }
        }

        return condicio1 && condicio2;
    }

    private boolean celabuida(int fila, int columna) {
        return tauler[fila][columna] == 0;
    }

    private void ferCami(List<Integer> cami, int posicioInicial) {
        if (casellesacanviar.containsKey(posicioInicial)) {
            List<Integer> dades = casellesacanviar.get(posicioInicial);
            casellesacanviar.put(posicioInicial, ajuntarLlista(dades, cami));
        }
        else{
            casellesacanviar.put(posicioInicial,cami);
        }
    }

    private List<Integer> ajuntarLlista(List<Integer> dades, List<Integer> cami) {
        for (int i = 0; i < cami.size(); i++) {
            if (!dades.contains(i)) {
                dades.add(cami.get(i));
            }
        }
        return dades;
    }

    boolean finalitzat(){
        return mida * mida - getCasellesUsuari().size() - getCasellesPC().size() == 0;
    }

    public void wrtiteToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(tauler);
        parcel.writeInt(mida);
        parcel.writeInt(torn);
        parcel.writeList(casellesUsuari);
        parcel.writeList(casellesPC);
    }
}
