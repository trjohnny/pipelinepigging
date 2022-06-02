import javax.swing.*;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        String partitaIva;
        String ragioneSociale;
        String formaGiuridica;
        String telefono;
        String sitoWeb;
        String indirizzo;
        String comune;
        String provincia;
        Sezione sezione;
        String descrizione;
        int idPig;
        try {
            DataBase db = new DataBase();
            String choice = "-1";
            Scanner scanner = new Scanner(System.in);
            System.out.println("PIPELINE PIGGING DB\n\nGiovanni Coronica\n\n");

            while (!choice.equals("0")) {
                System.out.println("<<<<<<<<<<<<<<<<<<    MENU    >>>>>>>>>>>>>>>>>>\n");
                System.out.println("1 -------- Visualizza il Pig con il massimo numero di interruzioni");
                System.out.println("2 -------- Visualizza il numero di volte che una ditta ha fornito i Pig");
                System.out.println("3 -------- Visualizza il numero di corse di un Pig");
                System.out.println("4 -------- Visualizza anomalie non ancora sottoposte a manutenzione");
                System.out.println("5 -------- Visualizza tutti dati della sezione di tubo in cui è stata rilevata un'anomalia");
                System.out.println("6 -------- Inserimento dati");
                System.out.println("0 -------- Esci");
                System.out.print(">>>   ");
                choice = scanner.next();

                switch (choice) {
                    case "1":
                        String[] intPig = db.maxNumInterruzioni();
                        int interruzioni = Integer.parseInt(intPig[0]);
                        idPig = Integer.parseInt(intPig[1]);
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("Il Pig con più interruzioni è IDPig="+idPig+" con interruzioni="+interruzioni+".");
                        System.out.println("Premere Invio per continuare...");
                        try{
                            System.in.read();
                        }
                        catch(Exception e){	e.printStackTrace();}
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case "2":
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.print("Inserisci la partita IVA della ditta >> ");
                        partitaIva = scanner.next();
                        int num = Integer.parseInt(db.numPigEsterni(partitaIva));
                        System.out.println("La ditta "+partitaIva+" ha fornito "+num+" Pig.");
                        System.out.println("Premere Invio per continuare...");
                        try{
                            System.in.read();
                        }
                        catch(Exception e){	e.printStackTrace();}
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case "3":
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.print("Inserisci l'ID del Pig >> ");
                        String ID = scanner.next();
                        int numCorse = db.numCorse(Integer.parseInt(ID));
                        System.out.println("Il Pig "+ID+" ha fatto "+numCorse+" corse.\n");
                        System.out.println("Premere Invio per continuare...");
                        try{
                            System.in.read();
                        }
                        catch(Exception e){	e.printStackTrace();}
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case "4":
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        List<Anomalia> anomalie = db.manutenzioniAnomalie();
                        for (Anomalia anomalia : anomalie) {
                            System.out.println(anomalia.toString());
                        }
                        System.out.println("\n\nPremere Invio per continuare...");
                        try{
                            System.in.read();
                        }
                        catch(Exception e){	e.printStackTrace();}
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case "5":
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.print("Inserisci IDAnomalia >> ");
                        int idAnomalia = scanner.nextInt();
                        System.out.println("\n\n");
                        sezione = db.datiSezioneAnomalia(idAnomalia);
                        System.out.println(sezione.toString());
                        System.out.println("\n\nPremere Invio per continuare...");
                        try{
                            System.in.read();
                        }
                        catch(Exception e){	e.printStackTrace();}
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case "6":
                        String choice2 = "";
                        while (!choice2.equals("0")) {
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("<<<<<<<<<<<<<<<<<<    MENU INSERIMENTO    >>>>>>>>>>>>>>>>>>\n");
                            System.out.println("1 -------- Aggiungi nuova corsa Pig");
                            System.out.println("2 -------- Aggiungi nuova anomalia");
                            System.out.println("3 -------- Aggiungi Ditta Pig");
                            System.out.println("4 -------- Aggiungi Fabbrica");
                            System.out.println("5 -------- Aggiungi nuova sezione");
                            System.out.println("6 -------- Aggiungi interruzione");
                            System.out.println("7 -------- Aggiungi nuovo Pig");
                            System.out.println("0 -------- Menu precedente");
                            System.out.print(">>>   ");
                            choice2 = scanner.next();
                            switch (choice2) {
                                case "1":
                                    CorsaPig corsaPig;
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    System.out.print("Inserisci data partenza (yyyy-MM-dd) >> ");
                                    java.sql.Date dataPartenza = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()).getTime());
                                    System.out.print("\nInserisci data arrivo (yyyy-MM-dd) >> ");
                                    java.sql.Date dataArrivo = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()).getTime());
                                    System.out.print("\nInserisci ora partenza (HH:mm:ss) >> ");
                                    java.sql.Time oraPartenza = new java.sql.Time(new SimpleDateFormat("HH:mm:ss").parse(scanner.next()).getTime());
                                    System.out.print("\nInserisci ora arrivo (HH:mm:ss) >> ");
                                    java.sql.Time oraArrivo = new java.sql.Time(new SimpleDateFormat("HH:mm:ss").parse(scanner.next()).getTime());
                                    System.out.print("\nInserisci IDPig >> ");
                                    idPig = Integer.parseInt(scanner.next());
                                    corsaPig = new CorsaPig(dataPartenza, dataArrivo, oraPartenza, oraArrivo, idPig);
                                    try {
                                        db.aggiungiCorsaPig(corsaPig);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "2":
                                    Anomalia anomalia;
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    System.out.print("Inserisci progressiva (precisione del metro) >> ");
                                    float progressiva = Float.parseFloat(scanner.next());
                                    System.out.print("\nInserisci gravità (1-10) >> ");
                                    int gravita = Integer.parseInt(scanner.next());
                                    System.out.print("\nInserisci temperatura (-20 - 150) >> ");
                                    int temperatura = Integer.parseInt(scanner.next());
                                    System.out.print("\nInserisci data manutenzione (yyyy-MM-dd) (per null inserire 0) >> ");
                                    String date = scanner.next();
                                    java.sql.Date dataManutenzione;
                                    if (!date.equals("0"))
                                        dataManutenzione = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
                                    else
                                        dataManutenzione = null;
                                    System.out.print("\nInserisci scansione (per null inserire 0) >> ");
                                    String scansione = scanner.next();
                                    scanner.nextLine();
                                    if (scansione.equals("0")) scansione = null;
                                    System.out.print("\nInserisci descrizione >> ");
                                    descrizione = scanner.nextLine();
                                    System.out.print("\nInserisci IDCorsaPig >> ");
                                    int idCorsaPig = Integer.parseInt(scanner.next());
                                    System.out.print("\nInserisci IDSezione >> ");
                                    int idSezione = Integer.parseInt(scanner.next());
                                    anomalia = new Anomalia(progressiva, gravita, temperatura, dataManutenzione, scansione, descrizione, idCorsaPig, idSezione);
                                    try {
                                        db.aggiungiAnomalia(anomalia);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "3":
                                    DittaPig dittaPig;
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    System.out.print("Inserisci Partita IVA >> ");
                                    partitaIva = scanner.next();
                                    System.out.print("\nInserisci ragione sociale >> ");
                                    ragioneSociale = scanner.nextLine();
                                    System.out.print("\nInserisci forma giuridica >> ");
                                    formaGiuridica = scanner.nextLine();
                                    System.out.print("\nInserisci telefono >> ");
                                    telefono = scanner.next();
                                    System.out.print("\nInserisci sito web (per null inserire 0) >> ");
                                    sitoWeb = scanner.next();
                                    if (sitoWeb.equals("0")) sitoWeb = null;
                                    System.out.print("\nInserisci indirizzo >> ");
                                    indirizzo = scanner.nextLine();
                                    System.out.print("\nInserisci comune >> ");
                                    comune = scanner.nextLine();
                                    System.out.print("\nInserisci provincia >> ");
                                    provincia = scanner.next();
                                    dittaPig = new DittaPig(partitaIva, ragioneSociale, telefono, telefono, sitoWeb, indirizzo, comune, provincia);
                                    try {
                                        db.aggiungiDittaPig(dittaPig);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "4":
                                    Fabbrica fabbrica;
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    System.out.print("Inserisci Partita IVA >> ");
                                    partitaIva = scanner.next();
                                    System.out.print("\nInserisci ragione sociale >> ");
                                    ragioneSociale = scanner.nextLine();
                                    System.out.print("\nInserisci forma giuridica >> ");
                                    formaGiuridica = scanner.nextLine();
                                    System.out.print("\nInserisci telefono >> ");
                                    telefono = scanner.next();
                                    System.out.print("\nInserisci sito web (per null inserire 0) >> ");
                                    sitoWeb = scanner.next();
                                    if (sitoWeb.equals("0")) sitoWeb = null;
                                    System.out.print("\nInserisci indirizzo >> ");
                                    indirizzo = scanner.nextLine();
                                    System.out.print("\nInserisci comune >> ");
                                    comune = scanner.nextLine();
                                    System.out.print("\nInserisci provincia >> ");
                                    provincia = scanner.next();
                                    fabbrica = new Fabbrica(partitaIva, ragioneSociale, telefono, telefono, sitoWeb, indirizzo, comune, provincia);
                                    try {
                                        db.aggiungiFabbrica(fabbrica);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "5":
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    System.out.print("Inserisci materiale >> ");
                                    String materiale = scanner.next();
                                    System.out.print("\nInserisci data installazione (yyyy-MM-dd) >> ");
                                    java.sql.Date dataInstallazione = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()).getTime());
                                    System.out.print("\nInserisci lunghezza (metri) >> ");
                                    float lunghezza = scanner.nextFloat();
                                    System.out.print("\nInserisci diametro (centimetri) >> ");
                                    int diametro = scanner.nextInt();
                                    System.out.print("\nInserisci Partita IVA della fabbrica >> ");
                                    partitaIva = scanner.next();
                                    sezione = new Sezione(materiale, dataInstallazione, lunghezza, diametro, partitaIva);
                                    try {
                                        db.aggiungiSezione(sezione);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "6":
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    Interruzione interruzione;
                                    System.out.print("Inserisci corsa Pig >> ");
                                    int idCorsa = scanner.nextInt();
                                    System.out.print("\nInserisci kilometro interruzione >> ");
                                    float kilometro = scanner.nextFloat();
                                    System.out.print("\nInserisci descrizione (per null inserire 0) >> ");
                                    descrizione = scanner.nextLine();
                                    System.out.print("\nInserisci data e ora interruzione (yyyy-MM-dd HH:mm:ss) >> ");
                                    java.sql.Date dataOraInterruzione = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.next()).getTime());
                                    interruzione = new Interruzione(idCorsa, kilometro, descrizione, dataOraInterruzione);
                                    try {
                                        db.aggiungiInterruzione(interruzione);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "7":
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    Pig pig;
                                    System.out.print("Inserisci lunghezza (metri) >> ");
                                    float lunghezzaPig = scanner.nextFloat();
                                    System.out.print("\nInserisci diametro (centimetri) >> ");
                                    int diametroPig = scanner.nextInt();
                                    int tipo = 0;
                                    do {
                                        System.out.print("\nInserisci tipo (1 - Utility, 2 - Smart) >> ");
                                        tipo = scanner.nextInt();
                                    } while (tipo != 1 && tipo != 2);
                                    System.out.print("\nInserisci prezzo >> ");
                                    float prezzo = scanner.nextFloat();
                                    System.out.print("\nInserisci data acquisto (yyyy-MM-dd) >> ");
                                    java.sql.Date dataAcquisto = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()).getTime());
                                    System.out.print("\nInserisci Partita IVA della ditta fornitrice >> ");
                                    partitaIva = scanner.next();
                                    pig = new Pig(lunghezzaPig, diametroPig, tipo, prezzo, dataAcquisto, partitaIva);
                                    try {
                                        db.aggiungiPig(pig);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                case "0":
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                    break;
                                default:
                                    System.out.print("-- Inserimento errato --");
                                    break;
                            }
                        }
                    case "0":
                        break;
                    default:
                        System.out.println("-- Inserimento errato --");
                        break;
                }
            }

        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }

    }
}