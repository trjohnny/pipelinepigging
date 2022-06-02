import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Calendar;

public class DataBase {
    private static final String URL = "jdbc:mysql://MySQLContainer:3307/pipelinepigging";
    private static final String USER = "root";

    private Connection conn;
    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, "root");
    }

    public void aggiungiPig(Pig pig) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO pig (Lunghezza, Diametro, Tipo, Prezzo, NumeroCorse, DataAcquisto, DittaPig) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setFloat(1, pig.getLunghezza());
        preparedStatement.setInt(2, pig.getDiametro());
        preparedStatement.setInt(3, pig.getTipo());
        preparedStatement.setFloat(4, pig.getPrezzo());
        preparedStatement.setInt(5, pig.getNumeroCorse());
        preparedStatement.setDate(6, pig.getDataAcquisto());
        preparedStatement.setString(7, pig.getDittaPig());
        preparedStatement.execute();
    }

    public void aggiungiInterruzione(Interruzione interruzione) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO interruzione (CorsaPig, Kilometro, Descrizione, DataOra) VALUES (?, ?, ?, ?)");
        preparedStatement.setInt(1, interruzione.getCorsaPig());
        preparedStatement.setFloat(2, interruzione.getKilometro());
        preparedStatement.setString(3, interruzione.getDescrizione());
        preparedStatement.setDate(4, interruzione.getDataOra());
        preparedStatement.execute();
    }

    public void aggiungiCorsaPig(CorsaPig corsaPig) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO corsapig (DataPartenza, DataArrivo, OraPartenza, OraArrivo, Pig) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setDate(1, corsaPig.getDataPartenza());
        preparedStatement.setDate(2, corsaPig.getDataArrivo());
        preparedStatement.setTime(3, corsaPig.getOraPartenza());
        preparedStatement.setTime(4, corsaPig.getOraArrivo());
        preparedStatement.setInt(5, corsaPig.getIdPig());
        preparedStatement.execute();
    }

    public  void aggiungiAnomalia(Anomalia anomalia) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO anomalia (Progressiva, Gravita, Temperatura, DataManutenzione, Scansione, Descrizione, CorsaPig, Sezione) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setFloat(1, anomalia.getProgressiva());
        preparedStatement.setInt(2, anomalia.getGravita());
        preparedStatement.setInt(3, anomalia.getTemperatura());
        preparedStatement.setDate(4, anomalia.getDataManutenzione());
        preparedStatement.setString(5, anomalia.getScansione());
        preparedStatement.setString(6, anomalia.getDescrizione());
        preparedStatement.setInt(7, anomalia.getCorsaPig());
        preparedStatement.setInt(8, anomalia.getSezione());
        preparedStatement.execute();
    }

    public void aggiungiDittaPig(DittaPig dittaPig) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO dittapig (PartitaIVA, RagioneSociale, FormaGiuridica, Telefono, SitoWeb, Indirizzo, Comune, Provincia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, dittaPig.getPartitaIVA());
        preparedStatement.setString(2, dittaPig.getRagioneSociale());
        preparedStatement.setString(3, dittaPig.getFormaGiuridica());
        preparedStatement.setString(4, dittaPig.getTelefono());
        preparedStatement.setString(5, dittaPig.getSitoWeb());
        preparedStatement.setString(6, dittaPig.getIndirizzo());
        preparedStatement.setString(7, dittaPig.getComune());
        preparedStatement.setString(8, dittaPig.getProvincia());
        preparedStatement.execute();
    }

    public void aggiungiSezione(Sezione sezione) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO sezione (Materiale, DataManutenzione, DataInstallazione, Lunghezza, Diametro, Fabbrica) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, sezione.getMateriale());
        preparedStatement.setDate(2, sezione.getDataManutenzione());
        preparedStatement.setDate(3, sezione.getDataInstallazione());
        preparedStatement.setFloat(4, sezione.getLunghezza());
        preparedStatement.setInt(5, sezione.getDiametro());
        preparedStatement.setString(6, sezione.getFabbrica());
        preparedStatement.execute();
    }

    public void aggiungiFabbrica(Fabbrica fabbrica) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO fabbricasezione (PartitaIVA, RagioneSociale, FormaGiuridica, Telefono, SitoWeb, Indirizzo, Comune, Provincia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, fabbrica.getPartitaIVA());
        preparedStatement.setString(2, fabbrica.getRagioneSociale());
        preparedStatement.setString(3, fabbrica.getFormaGiuridica());
        preparedStatement.setString(4, fabbrica.getTelefono());
        preparedStatement.setString(5, fabbrica.getSitoWeb());
        preparedStatement.setString(6, fabbrica.getIndirizzo());
        preparedStatement.setString(7, fabbrica.getComune());
        preparedStatement.setString(8, fabbrica.getProvincia());
        preparedStatement.execute();
    }

    public int numCorse(int idPig) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(Pig) FROM corsapig WHERE Pig= ? GROUP BY Pig;");
        preparedStatement.setInt(1, idPig);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        resultSet.next();
        return resultSet.getInt(1);
    }

    public String[] maxNumInterruzioni() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("SELECT Interruzioni, IDPig\n" +
                "FROM (SELECT COUNT(IDPig) as Interruzioni, IDPig\n" +
                "    FROM Pig INNER JOIN CorsaPig CP on Pig.IDPig = CP.Pig\n" +
                "        INNER JOIN Interruzione I on CP.IDCorsa = I.CorsaPig\n" +
                "        GROUP BY IDPig) as IIP\n" +
                "GROUP BY IDPig\n" +
                "ORDER BY IIP.Interruzioni DESC LIMIT 1;");

        result.next();
        int interruzioni = result.getInt("Interruzioni");
        int idPig = result.getInt("IDPig");

        return new String[] {Integer.toString(interruzioni), Integer.toString(idPig)};
    }

    public String numPigEsterni(String partitaIva) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(PartitaIVA) AS num\n" +
                "FROM DittaPig\n" +
                "INNER JOIN Pig P on DittaPig.PartitaIVA = P.DittaPig\n" +
                "WHERE DittaPig = ?;");
        preparedStatement.setString(1, partitaIva);
        ResultSet result = preparedStatement.executeQuery();

        result.next();
        return result.getString("num");
    }

    public List<Anomalia> manutenzioniAnomalie() throws SQLException {
        List<Anomalia> anomalie = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT *\n" +
                "FROM anomalia\n" +
                "WHERE DataManutenzione IS NULL;");

        int i = 0;
        while (resultSet.next()) {
            anomalie.add(new Anomalia(resultSet.getInt(1),
                    resultSet.getFloat(2), resultSet.getInt(3),
                    resultSet.getInt(4), resultSet.getDate(5),
                    resultSet.getString(6), resultSet.getString(7),
                    resultSet.getInt(8), resultSet.getInt(9)));
        }

        return anomalie;
    }

    public Sezione datiSezioneAnomalia(int idAnomalia) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT s.* FROM Sezione s\n" +
                "INNER JOIN Anomalia A on s.IDSezione = A.Sezione\n" +
                " WHERE IDAnomalia = ?;");
        preparedStatement.setInt(1, idAnomalia);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        return new Sezione(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getDate(3), resultSet.getDate(4),
                resultSet.getFloat(5), resultSet.getInt(6),
                resultSet.getString(7));
    }

    private Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    public void popolaCorse(int numCorse) throws SQLException {
        CorsaPig corsa;
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Random rand = new Random();
        int millisInDay = 24*60*60*1000;
        Time time;
        Date date = new Date(now - aDay * 362 * 21);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < numCorse; i++) {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO CorsaPig (DataPartenza, DataArrivo, OraPartenza, OraArrivo, Pig) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setDate(1, new java.sql.Date(calendar.getTime().getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            preparedStatement.setDate(2, new java.sql.Date(calendar.getTime().getTime()));
            preparedStatement.setTime(3, time = new Time((long)rand.nextInt(millisInDay)));
            preparedStatement.setTime(4, time = new Time((long)rand.nextInt(millisInDay)));
            preparedStatement.setInt(5, rand.nextInt(10) + 6);
            preparedStatement.execute();
            calendar.add(Calendar.DAY_OF_MONTH, rand.nextInt(11) + 25);
        }
    }


    public void PopolaInterruzioni(int num) throws SQLException {
        Random rand = new Random();
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Time time;
        int millisInDay = 24*60*60*1000;
        for (int i = 0; i < num; i++) {
            Date hundredYearsAgo = new Date(now - aDay * 365 * 20);
            Date tenDaysAgo = new Date(now - aDay * 11);
            Date random = between(hundredYearsAgo, tenDaysAgo);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO interruzione (CorsaPig, Kilometro, DataOra) VALUES (?, ?, ?)");
            int corsa = rand.nextInt(240)+1;
            preparedStatement.setInt(1, corsa);
            preparedStatement.setFloat(2, (float) rand.nextInt(735999) / 1000);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DataPartenza, OraPartenza FROM corsapig WHERE IDCorsa="+corsa);
            resultSet.next();
            String dateTime = format.format(new Date(resultSet.getDate("DataPartenza").getTime() + (long) rand.nextInt((int) resultSet.getTime("OraPartenza").getTime()) + (long) rand.nextInt(millisInDay)).getTime());
            preparedStatement.setString(3, dateTime);
            preparedStatement.execute();
        }

    }

    public void popolaSezioni(int num) throws SQLException {
        Random rand = new Random();
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Time time;
        int millisInDay = 24*60*60*1000;
        String[] materiali = new String[] {"Ferro", "Acciaio", "Acciaio inox", "Acciaio 316L", "Acciaio zincato"};
        String[] partiteIva = new String[] {"11459982415", "12516278491", "17451628901", "24154719456", "34255164789", "44471627482", "48116275911", "77152616479", "84516275162", "84617284567"};
        for (int i = 0; i < num; i++) {
            Date hundredYearsAgo = new Date(now - aDay * 365 * 2);
            Date tenDaysAgo = new Date(now);
            Date random = between(hundredYearsAgo, tenDaysAgo);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO sezione (Materiale, DataManutenzione, DataInstallazione, Lunghezza, Diametro, Fabbrica) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, materiali[rand.nextInt(5)]);
            preparedStatement.setDate(2, new java.sql.Date(random.getTime()));
            hundredYearsAgo = new Date(now - aDay * 365 * 50);
            tenDaysAgo = new Date(now - aDay * 365 * 10);
            random = between(hundredYearsAgo, tenDaysAgo);
            preparedStatement.setDate(3, new java.sql.Date(random.getTime()));
            preparedStatement.setFloat(4, (float) rand.nextInt(299) / 100 + 16 );
            preparedStatement.setInt(5, rand.nextInt(130) + 60);
            preparedStatement.setString(6, partiteIva[rand.nextInt(10)]);
            preparedStatement.execute();
        }
    }

    public void popolaAnomalia(int num) throws SQLException {
        Random rand = new Random();
        Calendar calendar = Calendar.getInstance();
        long aDay = TimeUnit.DAYS.toMillis(1);
        long now = new Date().getTime();
        Time time;
        int millisInDay = 24*60*60*1000;
        String[] descrizioni = new String[] {"Corrosione interna 3mm con superficie di 10cm2", "Corrosione interna da 5mm con superficie da 3cm2", "Corrosione esterna da 2mm", "Corrosione interna da 4mm in prossimita' di una cricca nel tubo", "Corrosione interna da 4mm in prossimita' di una giuntura elettrosaldata", "Corrosione interna da 5mm in prossimita' di saldatura weldolet stacco flangia"};
        List<Integer> corse = new ArrayList<>();
        List<Integer> sezioni;
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT IDCorsa FROM corsapig INNER JOIN pig ON corsapig.Pig = pig.IDPig WHERE Tipo=2");
        while (resultSet.next()) {
            corse.add(resultSet.getInt(1));
        }
        for (int i = 0; i < num; i++) {
            int idCorsa = corse.get(rand.nextInt(corse.size()));
            resultSet = statement.executeQuery("SELECT DataArrivo FROM corsapig WHERE IDCorsa="+idCorsa);
            resultSet.next();
            Date date1 = resultSet.getDate("DataArrivo");
            Date dataArrivo = date1;
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            date1 = calendar.getTime();
            calendar.add(Calendar.MONTH, 3);
            Date date2 = calendar.getTime();
            Date random = between(date1, date2);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO anomalia (Progressiva, Gravita, Temperatura, DataManutenzione, Scansione, Descrizione, CorsaPig, Sezione) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setFloat(1, (float) rand.nextInt(735999) / 1000);
            preparedStatement.setInt(2, rand.nextInt(10) + 1);
            preparedStatement.setInt(3, rand.nextInt(171) - 20);
            preparedStatement.setDate(4, new java.sql.Date(random.getTime()));
            preparedStatement.setString(5, scansione());
            preparedStatement.setString(6, descrizioni[rand.nextInt(6)]);
            preparedStatement.setInt(7, idCorsa);

            resultSet = statement.executeQuery("SELECT Diametro FROM Pig INNER JOIN corsapig c on pig.IDPig = c.Pig WHERE IDCorsa=" + idCorsa);
            resultSet.next();

            int diametro = resultSet.getInt("Diametro");
            sezioni = new ArrayList<>();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            resultSet = statement.executeQuery("SELECT IDSezione FROM sezione WHERE Diametro > " + diametro + " AND DataInstallazione < '" + format.format(dataArrivo) + "'");
            while(resultSet.next()) {
                sezioni.add(resultSet.getInt(1));
            }

            preparedStatement.setInt(8, sezioni.get(rand.nextInt(sezioni.size())));

            preparedStatement.execute();
        }

    }

    private String scansione() {
        Random rand = new Random();
        int num = rand.nextInt(6) + 1;
        StringBuilder scan = new StringBuilder();

        for (int i = 0; i < num; i++) {
            scan.append("(").append(rand.nextInt(12999) / 1000).append(",").append(rand.nextInt(12999) / 1000).append(")").append(";");
        }

        return scan.toString();
    }

}
