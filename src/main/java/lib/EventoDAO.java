package lib;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class EventoDAO {
    private EntityManager em;

    public void salva(Evento e) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
    }

    public Evento recupera(Long id) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Evento e = em.find(Evento.class, id);
        et.commit();
        em.close();
        return e;
    }

    public void atualiza(Evento e) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(e);
        et.commit();
    }

    public void deleta(Long id) {
        em = persistencia.JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Evento e = em.find(Evento.class, id);
        em.remove(e);
        et.commit();
        em.close();
    }

    public List<Evento> buscaTodos() {
        String jpqlQuery = "SELECT e FROM Evento e";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        List<Evento> e = query.getResultList();
        em.close();
        return e;
    }

}
