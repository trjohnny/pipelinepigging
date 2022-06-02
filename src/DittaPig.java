public class DittaPig {
    private String partitaIVA;
    private String ragioneSociale;
    private String formaGiuridica;
    private String telefono;
    private String sitoWeb;
    private String indirizzo;
    private String comune;
    private String provincia;

    public DittaPig(String partitaIVA, String ragioneSociale, String formaGiuridica, String telefono, String sitoWeb, String indirizzo, String comune, String provincia) {
        this.partitaIVA = partitaIVA;
        this.ragioneSociale = ragioneSociale;
        this.formaGiuridica = formaGiuridica;
        this.telefono = telefono;
        this.sitoWeb = sitoWeb;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.provincia = provincia;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public String getFormaGiuridica() {
        return formaGiuridica;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }
}
