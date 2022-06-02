import java.sql.Date;

public class Anomalia {
    private int idAnomalia;
    private float progressiva;
    private int gravita;
    private int temperatura;
    private Date dataManutenzione;
    private String scansione;
    private String descrizione;
    private int corsaPig;
    private int sezione;

    public float getProgressiva() {
        return progressiva;
    }

    public int getGravita() {
        return gravita;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public Date getDataManutenzione() {
        return dataManutenzione;
    }

    public String getScansione() {
        return scansione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getCorsaPig() {
        return corsaPig;
    }

    public int getSezione() {
        return sezione;
    }

    public Anomalia(float progressiva, int gravita, int temperatura, Date dataManutenzione, String scansione, String descrizione, int corsaPig, int sezione) {
        this.progressiva = progressiva;
        this.gravita = gravita;
        this.dataManutenzione = dataManutenzione;
        this.scansione = scansione;
        this.descrizione = descrizione;
        this.corsaPig = corsaPig;
        this.sezione = sezione;
        this.temperatura = temperatura;
    }

    public Anomalia(int idAnomalia, float progressiva, int gravita, int temperatura, Date dataManutenzione, String scansione, String descrizione, int corsaPig, int sezione) {
        this.idAnomalia = idAnomalia;
        this.progressiva = progressiva;
        this.gravita = gravita;
        this.dataManutenzione = dataManutenzione;
        this.scansione = scansione;
        this.descrizione = descrizione;
        this.corsaPig = corsaPig;
        this.sezione = sezione;
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Anomalia{" +
                "idAnomalia=" + idAnomalia +
                ", progressiva=" + progressiva +
                ", gravita=" + gravita +
                ", dataManutenzione=" + dataManutenzione +
                ", scansione='" + scansione + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", corsaPig=" + corsaPig +
                ", sezione=" + sezione +
                '}';
    }
}
