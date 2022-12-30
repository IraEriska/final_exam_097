/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.a.uaspra;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pws.a.uaspra.exceptions.NonexistentEntityException;
import pws.a.uaspra.exceptions.PreexistingEntityException;

/**
 *
 * @author Asus
 */
public class ProdukJpaController implements Serializable {

    public ProdukJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pws.a_uaspra_jar_0.0.1-SNAPSHOTPU");

    ProdukJpaController() {
    
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produk produk) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produk);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProduk(produk.getId()) != null) {
                throw new PreexistingEntityException("Produk " + produk + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produk produk) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            produk = em.merge(produk);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produk.getId();
                if (findProduk(id) == null) {
                    throw new NonexistentEntityException("The produk with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produk produk;
            try {
                produk = em.getReference(Produk.class, id);
                produk.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produk with id " + id + " no longer exists.", enfe);
            }
            em.remove(produk);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produk> findProdukEntities() {
        return findProdukEntities(true, -1, -1);
    }

    public List<Produk> findProdukEntities(int maxResults, int firstResult) {
        return findProdukEntities(false, maxResults, firstResult);
    }

    private List<Produk> findProdukEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produk.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Produk findProduk(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produk.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdukCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produk> rt = cq.from(Produk.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
