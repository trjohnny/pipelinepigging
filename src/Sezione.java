import java.sql.Date;

public class Sezione {
    private int idSezione;
    private String materiale;
    private Date dataManutenzione;
    private Date dataInstallazione;
    private float lunghezza;
    private int diametro;
    private String fabbrica;

    public Sezione(int idSezione, String materiale, Date dataManutenzione, Date dataInstallazione, float lunghezza, int diametro, String fabbrica) {
        this.idSezione = idSezione;
        this.materiale = materiale;
        this.dataManutenzione = dataManutenzione;
        this.dataInstallazione = dataInstallazione;
        this.lunghezza = lunghezza;
        this.diametro = diametro;
        this.fabbrica = fabbrica;
    }

    public Sezione(String materiale, Date dataInstallazione, float lunghezza, int diametro, String fabbrica) {
        this.materiale = materiale;
        this.dataManutenzione = null;
        this.dataInstallazione = dataInstallazione;
        this.lunghezza = lunghezza;
        this.diametro = diametro;
        this.fabbrica = fabbrica;
    }

    public int getIdSezione() {
        return idSezione;
    }

    public String getMateriale() {
        return materiale;
    }

    public Date getDataManutenzione() {
        return dataManutenzione;
    }

    public Date getDataInstallazione() {
        return dataInstallazione;
    }

    public float getLunghezza() {
        return lunghezza;
    }

    public int getDiametro() {
        return diametro;
    }

    public String getFabbrica() {
        return fabbrica;
    }

    @Override
    public String toString() {
        return "Sezione{" +
                "idSezione=" + idSezione +
                ", materiale='" + materiale + '\'' +
                ", dataManutenzione=" + dataManutenzione +
                ", dataInstallazione=" + dataInstallazione +
                ", lunghezza=" + lunghezza +
                ", diametro=" + diametro +
                ", fabbrica='" + fabbrica + '\'' +
                '}';
    }
}
