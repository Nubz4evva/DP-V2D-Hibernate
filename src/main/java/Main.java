import Adres.Adres;
import Adres.AdresDAOHibernate;
import OVChipkaart.OVChipkaart;
import OVChipkaart.OVChipkaartDAOHibernate;
import Product.Product;
import Product.ProductDAOHibernate;
import Reizigers.Reiziger;
import Reizigers.ReizigerDAOHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws Exception {
        testAdres();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */

    private static void testAdres() throws Exception {
        Session session = getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(session);
            System.out.println("[Test] Reiziger");
            // findAll
            List<Reiziger> reizigers = rdao.findAll();
            System.out.println("findAll geeft " + reizigers.size() + " reizigers");
            // Save
            Reiziger reiziger = new Reiziger("J", "de", "Bont", new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-23"));
            rdao.save(reiziger);
            reizigers = rdao.findAll();
            System.out.println("Na save geeft findAll " + reizigers.size() + " reizigers\n");
            // findById
            Reiziger reizigerResult = rdao.findById(reiziger.getReiziger_id());
            System.out.println("findById geeft: " + reizigerResult);
            System.out.println();
            // findByGbdatum
            List<Reiziger> reizigerResultList = rdao.findByGbdatum("2003-09-23");
            System.out.println("findByGbdatum geeft de volgende reiziger(s): ");
            for (Reiziger r : reizigerResultList) {
                System.out.println(r);
            }
            System.out.println();
            // Update
            reiziger.setVoorletters("JP");
            rdao.update(reiziger);
            reizigerResult = rdao.findById(reiziger.getReiziger_id());
            System.out.println("Update geeft: " + reizigerResult + "\n\n");



            AdresDAOHibernate adao = new AdresDAOHibernate(session);
            System.out.println("[Test] Adres");
            // findAll
            List<Adres> adressen = adao.findAll();
            System.out.println("findAll geeft " + adressen.size() + " adressen");
            // Save
            Adres adres = new Adres("3853SL", "24", "Leemkuul", "Ermelo", reiziger);
            reiziger.setAdres(adres);
            rdao.update(reiziger);
            adao.save(adres);

            adressen = adao.findAll();
            System.out.println("Na save geeft findAll " + adressen.size() + " adressen\n");
            // findById
            Adres adresResult = adao.findById(adres.getAdres_id());
            System.out.println("findById geeft: " + adresResult);
            System.out.println();
            // findByReiziger
            adresResult = adao.findByReiziger(reiziger);
            System.out.println("findByReiziger geeft: " + adresResult);
            System.out.println();
            // Update
            adres.setHuisnummer("25");
            adao.update(adres);
            adresResult = adao.findById(adres.getAdres_id());
            System.out.println("Update geeft: " + adresResult + "\n\n");


            OVChipkaartDAOHibernate ovdao = new OVChipkaartDAOHibernate(session);
            System.out.println("[TEST] OVChipkaart");
            // findByReiziger
            List<OVChipkaart> ovChipkaarten = ovdao.findByReiziger(reiziger);
            System.out.println("findByReiziger voor save geeft: " + ovChipkaarten);
            // Save
            OVChipkaart ovChipkaart = new OVChipkaart("2023-02-02", 2, 30.21, reiziger);
            ovdao.save(ovChipkaart);
            reiziger.addOVChipkaart(ovChipkaart);
            rdao.update(reiziger);

            ovChipkaarten = ovdao.findByReiziger(reiziger);
            System.out.println("findByReiziger na save geeft: " + ovChipkaarten);
            System.out.println();
            // Update
            ovChipkaart.setSaldo(35.21);
            ovdao.update(ovChipkaart);
            ovChipkaarten = ovdao.findByReiziger(reiziger);
            System.out.println("Update geeft: " + ovChipkaarten);
            System.out.println();


            ProductDAOHibernate pdao = new ProductDAOHibernate(session);
            System.out.println("[TEST] Product");
            // findByOVChipkaart
            List<Product> producten = pdao.findByOVChipkaart(ovChipkaart);
            System.out.println("findByOVChipkaart voor save geeft: " + producten);
            // Save
            Product product = new Product("Test", "Test 123", 2.50);
            ovChipkaart.addProduct(product);
            product.addOVChipkaart(ovChipkaart);
            pdao.save(product);
            ovdao.update(ovChipkaart);

            producten = pdao.findByOVChipkaart(ovChipkaart);
            System.out.println("findByOVChipkaart na save geeft: " + producten);
            System.out.println();
            // Update
            product.setPrijs(3.50);
            pdao.update(product);
            producten = pdao.findByOVChipkaart(ovChipkaart);
            System.out.println("Update geeft: " + producten);
            System.out.println();


            // Deletes
            System.out.println("[TEST] Deletes");
            // OVChipkaart
            ovdao.delete(ovChipkaart);
            ovChipkaarten = ovdao.findByReiziger(reiziger);
            System.out.println("Na ovChipkaart delete: " + ovChipkaarten);
            // Product
            pdao.delete(product);
            producten = pdao.findByOVChipkaart(ovChipkaart);
            System.out.println("Na product delete: " + producten);
            // Adres
            adao.delete(adres);
            adresResult = adao.findByReiziger(reiziger);
            System.out.println("Na adres delete: " + adresResult);
            // Reiziger
            rdao.delete(reiziger);
            System.out.println("Voor reiziger delete: " + reizigers.size() + " reizigers");
            reizigers = rdao.findAll();
            System.out.println("Na reiziger delete: " + reizigers.size() + " reizigers");

            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }
}