package lib;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class EdicaoDAO {
    private EntityManager em;
    public void salva(Edicao e) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
    }
    public Edicao recupera(Long id) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Edicao e = em.find(Edicao.class, id);
        et.commit();
        em.close();
        return e;
    }

    public void atualiza (Edicao e){
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(e);
        et.commit();
    }

    public  void deleta(Long id) {
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Edicao e = em.find(Edicao.class, id);
        em.remove(e);
        et.commit();
        em.close();
    }

    public List<Edicao> buscaPorEvento(Long idEvento) {
        String jpqlQuery = "SELECT e FROM Edicao e WHERE e.evento.id = :id";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("id", idEvento);
        List<Edicao> e = query.getResultList();
        em.close();
        return e;
    }

    public List<Edicao> buscaPorData(Date data) {
        String jpqlQuery = "SELECT e FROM Edicao e WHERE e.dataInicial >= :data";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("data", data);
        List<Edicao> e = query.getResultList();
        em.close();
        return e;
    }

    public List<Edicao> buscaPorCidade(String cidade) {
        String jpqlQuery = "SELECT e FROM Edicao e WHERE e.cidadeSede = :cidade";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("cidade", cidade);
        List<Edicao> e = query.getResultList();
        em.close();
        return e;
    }

    public List<Edicao> buscaPorDataECidade(Date data, String cidade) {
        String jpqlQuery = "SELECT e FROM Edicao e WHERE e.dataInicial >= :data AND e.cidadeSede = :cidade";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("data", data);
        query.setParameter("cidade", cidade);
        List<Edicao> e = query.getResultList();
        em.close();
        return e;
    }
}
