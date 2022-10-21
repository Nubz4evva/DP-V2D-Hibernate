package Product;

import Adres.Adres;
import OVChipkaart.OVChipkaart;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO{

    public Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    public boolean save(Product product) {
        try {
            this.session.save(product);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean update(Product product) {
        try {
            this.session.update(product);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean delete(Product product) {
        try {
            String sql = "DELETE FROM ov_chipkaart_product WHERE product_nummer = :product_nummer";
            Query query = this.session.createSQLQuery(sql);
            query.setParameter("product_nummer", product.getProductNummer());
            this.session.flush();
            this.session.delete(product);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            String hql = "SELECT P FROM Product P JOIN P.OVChipkaarten OV WHERE OV.kaartNummer = :kaart_nummer";
            Query query = this.session.createQuery(hql);
            query.setParameter("kaart_nummer", ovChipkaart.getKaartNummer());
            List<Product> producten = query.list();
            return producten;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
