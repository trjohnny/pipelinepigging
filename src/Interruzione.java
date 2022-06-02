import java.sql.Date;

public class Interruzione {
    private int corsaPig;
    private float kilometro;
    private String descrizione;
    private Date dataOra;

    public int getCorsaPig() {
        return corsaPig;
    }

    public float getKilometro() {
        return kilometro;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Date getDataOra() {
        return dataOra;
    }

    public Interruzione(int corsaPig, float kilometro, String descrizione, Date dataOra) {
        this.corsaPig = corsaPig;
        this.kilometro = kilometro;
        this.descrizione = descrizione;
        this.dataOra = dataOra;
    }
}
