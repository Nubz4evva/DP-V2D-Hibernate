package OVChipkaart;

import Product.Product;
import Reizigers.Reiziger;

import javax.persistence.*;
import java.sql.Array;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @GeneratedValue
    @Column(name = "kaart_nummer")
    private int kaartNummer;
    @Column(name = "geldig_tot")
    private Date geldigTot;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = { @JoinColumn(name = "kaart_nummer") },
            inverseJoinColumns = { @JoinColumn( name = "product_nummer")}
    )
    private List<Product> producten = new ArrayList<Product>();

    public OVChipkaart() {}

    public OVChipkaart(String geldigTot, int klasse, double saldo, Reiziger reiziger) throws ParseException {
        Date geldigTotDate = new SimpleDateFormat("yyyy-MM-dd").parse(geldigTot);
        this.geldigTot = geldigTotDate;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }

    public void addProduct(Product product) {
        product.addOVChipkaart(this);
        this.producten.add(product);
    }

    public void removeProduct(Product product) {
        this.producten.remove(product);
        List<OVChipkaart> pOVChipkaarten = product.getOVChipkaarten();
        pOVChipkaarten.remove(this);
    }

    public String toString() {
        return "Kaartnummer " + this.kaartNummer + " (Klasse " + this.klasse + "), saldo: " + this.saldo;
    }
}
