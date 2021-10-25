package fr.android.tennistrackerv2.Model;

import java.io.Serializable;

public class Statistique implements Serializable {

    private Match match;
    private Club club;
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

    public Statistique(String serialId,Match match, Club club, int tir, int tirCadre, int score, int fautes, int cartonJaune, int cartonRouge, int passe, int horsJeu, int corner) {
        this.serialId = serialId;
        this.match = match;
        this.club = club;
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


    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
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
                "match=" + match +
                ", club=" + club +
                ", tir=" + tir +
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
