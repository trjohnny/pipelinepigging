import java.sql.Date;

public class Pig {
    private int idPig;
    private float lunghezza;
    private int diametro;
    private int tipo;
    private float prezzo;
    private int numeroCorse;
    private Date dataAcquisto;
    private String dittaPig;

    public int getIdPig() {
        return idPig;
    }

    public float getLunghezza() {
        return lunghezza;
    }

    public int getDiametro() {
        return diametro;
    }

    public int getTipo() {
        return tipo;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public int getNumeroCorse() {
        return numeroCorse;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public String getDittaPig() {
        return dittaPig;
    }

    public Pig(float lunghezza, int diametro, int tipo, float prezzo, Date dataAcquisto, String dittaPig) {
        this.lunghezza = lunghezza;
        this.diametro = diametro;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.numeroCorse = 0;
        this.dataAcquisto = dataAcquisto;
        this.dittaPig = dittaPig;
    }
}
