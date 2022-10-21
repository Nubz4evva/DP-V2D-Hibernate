package Reizigers;

import Adres.Adres;
import OVChipkaart.OVChipkaart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Reiziger {
    @Id
    @GeneratedValue
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    @OneToOne(mappedBy = "reiziger")
    private Adres adres;
    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> OVChipkaarten = new ArrayList<OVChipkaart>();

    public Reiziger() {}

    public Reiziger(String vl, String tv, String an, Date gbdtm) {
        this.voorletters = vl;
        this.tussenvoegsel = tv;
        this.achternaam = an;
        this.geboortedatum = gbdtm;
    }

    public int getReiziger_id() {
        return this.reiziger_id;
    }
    public void setReiziger_id(int id) {
        this.reiziger_id = id;
    }

    public String getVoorletters() {
        return this.voorletters;
    }
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsels() {
        return this.tussenvoegsel;
    }
    public void setTussenvoegsels(String tussenvoegsels) {
        this.tussenvoegsel = tussenvoegsels;
    }

    public String getAchternaam() {
        return this.achternaam;
    }
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOVChipkaarten() {
        return OVChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        this.OVChipkaarten.add(ovChipkaart);
    }

    public String getNaam() {
        String voorl = this.voorletters;
        String tussenv = this.tussenvoegsel;
        String achtern = this.achternaam;
        String text = voorl + ". ";
        if (tussenv != null) {
            text += tussenv + " ";
        }
        text += achtern;
        return text;
    }

    public String toString() {
        String reizigerString = "Reiziger #" + this.reiziger_id + " " + getNaam() + ", geb. " + this.geboortedatum;
        if (this.adres != null) {
            String adresString = ", " + this.adres.getStraat() + " " + this.adres.getHuisnummer() + " (" + this.adres.getPostcode() + ")";
            reizigerString += adresString;
        }
        return reizigerString;
    }
}
