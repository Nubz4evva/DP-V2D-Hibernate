package OVChipkaart;

import Adres.Adres;
import Reizigers.Reiziger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    public Session session;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    public boolean save(OVChipkaart ovChipkaart) {
        try {
            this.session.save(ovChipkaart);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean update(OVChipkaart ovChipkaart) {
        try {
            this.session.update(ovChipkaart);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean delete(OVChipkaart ovChipaart) {
        try {
            this.session.delete(ovChipaart);
            this.session.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            String hql = "FROM OVChipkaart OV WHERE OV.reiziger = :reiziger";
            Query query = this.session.createQuery(hql);
            query.setParameter("reiziger", reiziger);
            List<OVChipkaart> ovChipkaart = query.list();
            return ovChipkaart;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
