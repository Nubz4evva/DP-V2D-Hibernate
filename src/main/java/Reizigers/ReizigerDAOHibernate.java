package Reizigers;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    public Session session;

    public ReizigerDAOHibernate(Session session) {
        this.session = session;
    }

    public boolean save(Reiziger reiziger) {
        try {
            this.session.save(reiziger);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean update(Reiziger reiziger) {
        try {
            this.session.update(reiziger);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean delete(Reiziger reiziger) {
        try {
            this.session.delete(reiziger);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Reiziger findById(int id) {
        try {
            Reiziger reiziger = this.session.byId(Reiziger.class).getReference(id);
            return reiziger;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Reiziger> findByGbdatum(String datum) {
        try {
            Date gbdatum = (new SimpleDateFormat("yyyy-MM-dd").parse(datum));
            String hql = "FROM Reiziger R WHERE R.geboortedatum = :gbdatum";
            Query query = this.session.createQuery(hql);
            query.setParameter("gbdatum", gbdatum);
            List<Reiziger> reizigers = query.list();
            return reizigers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Reiziger> findAll() {
        try {
            Query query = this.session.createQuery("from Reiziger");
            List<Reiziger> reizigers = query.list();
            return reizigers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
