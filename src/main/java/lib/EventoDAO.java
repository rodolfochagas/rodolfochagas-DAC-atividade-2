package lib;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
