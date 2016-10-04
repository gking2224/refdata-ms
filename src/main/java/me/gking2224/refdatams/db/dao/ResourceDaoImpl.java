package me.gking2224.refdatams.db.dao;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.gking2224.common.db.AbstractDaoImpl;
import me.gking2224.refdatams.db.jpa.ResourceRepository;
import me.gking2224.refdatams.model.Resource;

@Component
@Transactional
public class ResourceDaoImpl extends AbstractDaoImpl<Resource> implements ResourceDao {

    @Autowired
    protected ResourceRepository repository;
    
    
    public ResourceDaoImpl() {
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = repository.findAll();
        return resources;
    }

    @Override
    @Transactional(propagation=REQUIRES_NEW)
    public Resource batchSaveOrUpdate(Resource r) {
        return save(r);
    }

    @Override
    public Resource save(Resource r) {
        return repository.save(r);
    }
}
