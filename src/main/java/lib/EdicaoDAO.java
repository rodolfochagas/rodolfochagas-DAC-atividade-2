package lib;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EdicaoDAO {
    private EntityManager em;
    public void salva(Edicao e) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
    }
    Edicao recupera(Long id) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Edicao e = em.find(Edicao.class, id);
        et.commit();
        em.close();
        return e;
    }
}
