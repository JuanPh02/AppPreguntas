package com.example.gamepreguntas;

public class Pregunta {
    private String enunc;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String resCorrecta;
    private int dificultad;
    private boolean utilizada = false;

    public Pregunta(String enunc, String res1, String res2, String res3, String res4, String resC, int dif) {
        this.enunc = enunc;
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
        this.res4 = res4;
        this.resCorrecta = resC;
        this.dificultad = dif;
    }

    public String getEnunc() {
        return enunc;
    }

    public String getRes1() {
        return res1;
    }

    public String getRes2() {
        return res2;
    }

    public String getRes3() {
        return res3;
    }

    public String getRes4() {
        return res4;
    }

    public String getResCorrecta() {
        return resCorrecta;
    }

    public int getDificultad() {
        return dificultad;
    }

    public boolean isUtilizada() {
        return utilizada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }
}
