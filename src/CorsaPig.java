import java.sql.Date;
import java.sql.Time;

public class CorsaPig {
    private int idCorsa;
    private Date dataPartenza;
    private Date dataArrivo;
    private Time oraPartenza;
    private Time oraArrivo;
    private int idPig;


    public int getIdCorsa() {
        return idCorsa;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public Date getDataArrivo() {
        return dataArrivo;
    }

    public Time getOraPartenza() {
        return oraPartenza;
    }

    public Time getOraArrivo() {
        return oraArrivo;
    }

    public int getIdPig() {
        return idPig;
    }

    public CorsaPig(Date dataPartenza, Date dataArrivo, Time oraPartenza, Time oraArrivo, int idPig) {
        this.dataPartenza = dataPartenza;
        this.dataArrivo = dataArrivo;
        this.oraPartenza = oraPartenza;
        this.oraArrivo = oraArrivo;
        this.idPig = idPig;
    }
}
