package Adres;

import javax.persistence.*;

@Entity
public class Adres {
    @Id
    @GeneratedValue()
    private int adres_id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Reizigers.Reiziger reiziger;

    public Adres() {}

    public Adres(String postcode, String huisnummer, String straat, String woonplaats, Reizigers.Reiziger reiziger) {
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;
    }

    public int getAdres_id() {
        return adres_id;
    }
    public void setAdres_id(int id) {
        this.adres_id = id;
    }

    public String getHuisnummer() {
        return huisnummer;
    }
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStraat() {
        return straat;
    }
    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Reizigers.Reiziger getReizigerId() {
        return reiziger;
    }

    public void setReizigerId(Reizigers.Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String toString() {
        return straat + " " + huisnummer + ", " + postcode + ", " + woonplaats;
    }
}

