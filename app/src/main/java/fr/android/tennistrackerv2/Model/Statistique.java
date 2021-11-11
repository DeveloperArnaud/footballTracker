package fr.android.tennistrackerv2.Model;

import java.io.Serializable;

public class Statistique implements Serializable {

    private int id_stats;
    private int tir;
    private int tirCadre;
    private int score;
    private int fautes;
    private int cartonJaune;
    private int cartonRouge;
    private int passe;
    private int horsJeu;
    private int corner;
    private String serialId;

    public Statistique() {
    }

    public Statistique(int id_stats, int tir, int tirCadre, int score, int fautes, int cartonJaune, int cartonRouge, int passe, int horsJeu, int corner) {
        this.id_stats = id_stats;
        this.tir = tir;
        this.tirCadre = tirCadre;
        this.score = score;
        this.fautes = fautes;
        this.cartonJaune = cartonJaune;
        this.cartonRouge = cartonRouge;
        this.passe = passe;
        this.horsJeu = horsJeu;
        this.corner = 0;
    }

    public Statistique(int tir, int tirCadre, int score, int fautes, int cartonJaune, int cartonRouge, int passe, int horsJeu, int corner) {
        this.tir = tir;
        this.tirCadre = tirCadre;
        this.score = score;
        this.fautes = fautes;
        this.cartonJaune = cartonJaune;
        this.cartonRouge = cartonRouge;
        this.passe = passe;
        this.horsJeu = horsJeu;
        this.corner = corner;
    }

    public int getId_stats() {
        return id_stats;
    }


    public int getTir() {
        return tir;
    }

    public void setTir(int tir) {
        this.tir = tir;
    }

    public int getTirCadre() {
        return tirCadre;
    }

    public void setTirCadre(int tirCadre) {
        this.tirCadre = tirCadre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFautes() {
        return fautes;
    }

    public void setFautes(int fautes) {
        this.fautes = fautes;
    }

    public int getCartonJaune() {
        return cartonJaune;
    }

    public void setCartonJaune(int cartonJaune) {
        this.cartonJaune = cartonJaune;
    }

    public int getCartonRouge() {
        return cartonRouge;
    }

    public void setCartonRouge(int cartonRouge) {
        this.cartonRouge = cartonRouge;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public int getHorsJeu() {
        return horsJeu;
    }

    public void setHorsJeu(int horsJeu) {
        this.horsJeu = horsJeu;
    }

    public int getCorner() {
        return corner;
    }

    public void setCorner(int corner) {
        this.corner = corner;
    }

    @Override
    public String toString() {
        return "Statistique{" +
                "  tir=" + tir +
                ", tirCadre=" + tirCadre +
                ", score=" + score +
                ", fautes=" + fautes +
                ", cartonJaune=" + cartonJaune +
                ", cartonRouge=" + cartonRouge +
                ", passe=" + passe +
                ", horsJeu=" + horsJeu +
                ", corner=" + corner +
                '}';
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }
}
