import javax.swing.*;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        Cliente cliente= new Cliente();
        cliente.setNome(JOptionPane.showInputDialog(null, "Inserisci il nome",
                "nome", JOptionPane.QUESTION_MESSAGE));
        System.out.println("Nome: "+ cliente.getNome());
        cliente.setCognome(JOptionPane.showInputDialog(null, "Inserisci il cognome",
                "cognome", JOptionPane.QUESTION_MESSAGE));
        System.out.println("Cognome: "+ cliente.getCognome());
        String orarioInizio= JOptionPane.showInputDialog(null, "Inserisci l'orario di inizio in formato HH:MM",
                "orario inizio", JOptionPane.QUESTION_MESSAGE);
        String orarioFine= JOptionPane.showInputDialog(null, "Inserisci l'orario di fine in formato HH:MM",
                "orario di fine", JOptionPane.QUESTION_MESSAGE);
        if(stringCheck(orarioInizio) && stringCheck(orarioFine) && oraFineMaggioreDiOraInizio(splitOreDaMinuti(orarioInizio),splitOreDaMinuti(orarioFine))){
            cliente.setOrarioInizio(orarioInizio);
            cliente.setOrarioFine(orarioFine);
            int minutiUtilizzo = minutiUtilizzo(splitOreDaMinuti(cliente.getOrarioInizio()), splitOreDaMinuti(cliente.getOrarioFine()));
            int quartiDora = calcolaQuartiDora(minutiUtilizzo);
            cliente.setPrezzo(calcolaTariffa(quartiDora));
            JOptionPane.showMessageDialog(null, cliente + " per un tempo di utilizzo pari a: "+ convertMinutesToHours(minutiUtilizzo) + " ore.",  "Stampa dati cliente e prezzo", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showInputDialog(null, "errore, orario non valido oppure l'orario di inizio e' minore di quello di fine",  "Errore", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static boolean stringCheck(String orario){
        // Regex to check valid time in 24-hour format.
        String oraGiusta = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        // Compile the ReGex
        Pattern p = Pattern.compile(oraGiusta);
        // If the time is empty
        // return false
        if (oraGiusta == null) {
            return false;
        }
        // Pattern class contains matcher() method
        // to find matching between given time
        // and regular expression.
        Matcher m = p.matcher(orario);
        // Return if the time
        // matched the ReGex
        return m.matches();
    }

    public static OraFormattata splitOreDaMinuti(String orario){
        OraFormattata oraFormattata = new OraFormattata();
        String orarioDaSplittare = orario;
        String[] parts = orarioDaSplittare.split(":");
        oraFormattata.setOre(parts[0]);
        oraFormattata.setMinuti(parts[1]);
        return oraFormattata;
    }

    public static int minutiUtilizzo(OraFormattata orarioInizio, OraFormattata orarioFine){
        int differenzaOre = 0;
        int differenzaMinuti = (60 - Integer.parseInt(orarioInizio.getMinuti()))+ Integer.parseInt(orarioFine.getMinuti());
        if(differenzaMinuti > 0){
            differenzaOre = (Integer.parseInt(orarioFine.getOre()) - Integer.parseInt(orarioInizio.getOre())) - 1;
        }
        int minutiUtilizzo= (differenzaOre * 60) + differenzaMinuti;
        return minutiUtilizzo;
    }

    public static String convertMinutesToHours(Integer minutes) {
        String label = "";
        // il numero di ore
        int hourInt = minutes / 60;
        // i minuti restanti
        int minuteInt = minutes % 60;
        // formattiamo i minuti con due cifre intere
        NumberFormat formato = NumberFormat.getIntegerInstance();
        formato.setMinimumIntegerDigits(2);
        String minuteString = formato.format(minuteInt);
        // componiamo il tutto in una stringa
        label = hourInt + ":" + minuteString;
        return label;
    }

    public static boolean oraFineMaggioreDiOraInizio(OraFormattata orarioInizio, OraFormattata orarioFine){
        if(Integer.parseInt(orarioFine.getOre()) > Integer.parseInt(orarioInizio.getOre())){
           return true;
        }else if(Integer.parseInt(orarioFine.getOre()) == Integer.parseInt(orarioInizio.getOre()) && Integer.parseInt(orarioFine.getMinuti()) > Integer.parseInt(orarioInizio.getMinuti())){
            return true;
        }
        return false;
    }
    public static int calcolaQuartiDora(int minutiUtilizzo){
        int quantiQuartiDora = minutiUtilizzo / 15;
        if(minutiUtilizzo%15 != 0){
             quantiQuartiDora += 1;
        }
        return quantiQuartiDora;
    }

    public static double calcolaTariffa(int quantiQuartiDora){
        int ore= quantiQuartiDora / 4;
        int restoQuartiDora= quantiQuartiDora%4;
        int mezzOra = restoQuartiDora / 2;
        int quartiDora = restoQuartiDora%2;
        double tariffa =  (ore + (mezzOra * 0.5) + (quartiDora * 0.3));
        return tariffa;

    }
}
