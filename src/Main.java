import javax.swing.*;
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
        if(stringCheck(orarioInizio) && stringCheck(orarioFine)){
            cliente.setOrarioInizio(orarioInizio);
            cliente.setOrarioFine(orarioFine);
            int minutiUtilizzo = minutiUtilizzo(splitOreDaMinuti(cliente.getOrarioInizio()), splitOreDaMinuti(cliente.getOrarioFine()));
            System.out.println(minutiUtilizzo);
            int quartiDora = calcolaQuartiDora(minutiUtilizzo);
            cliente.setPrezzo(calcolaTariffa(quartiDora));
            System.out.println(cliente);
        }else{
            JOptionPane.showInputDialog(null, "errore, orario non valido",  "Errore", JOptionPane.INFORMATION_MESSAGE);
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
