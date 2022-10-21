package Adres;

import Reizigers.Reiziger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    public Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    public boolean save(Adres adres) {
        try {
            this.session.save(adres);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean update(Adres adres) {
        try {
            this.session.update(adres);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean delete(Adres adres) {
        try {
            this.session.delete(adres);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Adres findById(int id) {
        try {
            Adres adres = this.session.byId(Adres.class).getReference(id);
            return adres;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Adres findByReiziger(Reizigers.Reiziger reiziger) {
        try {
            String hql = "FROM Adres A WHERE A.reiziger = :reiziger";
            Query query = this.session.createQuery(hql);
            query.setParameter("reiziger", reiziger);
            Adres adres = (Adres) query.getSingleResult();
            return adres;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Adres> findAll() {
        try {
            Query query = this.session.createQuery("FROM Adres");
            List<Adres> adressen = query.list();
            return adressen;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
