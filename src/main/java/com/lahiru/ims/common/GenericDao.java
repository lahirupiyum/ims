package com.lahiru.ims.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.lahiru.ims.exception.NotFoundException;

@Component
public class GenericDao {
    public <Model,ID> Model getOne(ID id, JpaRepository<Model, ID> repo, String resourceName) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException(resourceName));
    }

    public <Model, ID> Boolean isExistsById(ID id, JpaRepository<Model, ID> repo) {
        return repo.existsById(id);
    }
}
