package ru.tsystem.javaschool.ordinaalena.DAO;



import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsertDAOImpl implements UsertDAO{

  /* @PersistenceContext
    EntityManager entityManager;


    @Override
    public void create(UsertEntity usertEntity) {
        entityManager.persist(usertEntity);
    }

    @Override
    public UsertEntity read(Integer id) {
        return entityManager.find(UsertEntity.class,id);
    }

    @Override
    public void update(Integer id) {
        UsertEntity usertEntity=entityManager.find(UsertEntity.class,id);
        entityManager.detach(usertEntity);
        usertEntity.setUsername("ffff");
        entityManager.merge(usertEntity);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(UsertEntity.class,id));
    }

    @Override
    public List<UsertEntity> findAllProduct() {
        return entityManager.createQuery("from UsertEntity c").getResultList();
    }*/
}

