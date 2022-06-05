# pipelinepigging
Progetto DB per il Pipeline Pigging

Lo sviluppo del progetto completo è contenuto nel file Progetto BDD.pdf

Ci sono 2 file .sql per importare il database (MySQL), un file contiene solo le tabelle, le relazioni e i triggers, mentre l'altro file contiene anche una quantità di dati sufficiente (INSERT) a interagire con il DB.

Nella cartella /src/ c'è un progetto Java per testare le operazioni sul DB. Nella classe Database.java sono contenute tutti i metodi pubblici (richiamati dalla classe Main) per le interrogazioni sul DB. Le altre classi sono di supporto.

Il database è stato popolato con funzioni apposite situate nella classe Database.java.

Per la connessione al DB è stato usato il JDBC connector contenuto in questa cartella.
