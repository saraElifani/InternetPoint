public class Cliente {
    private String nome;
    private String cognome;
    private String orarioInizio;
    private String orarioFine;
    private double prezzo;


    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public String getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(String orarioFine) {
        this.orarioFine = orarioFine;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", orarioInizio='" + orarioInizio + '\'' +
                ", orarioFine='" + orarioFine + '\'' +
                ", prezzo da pagare=" + prezzo +
                '}';
    }
}
