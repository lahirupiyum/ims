package com.lahiru.ims.common;

import com.lahiru.ims.common.dto.feature.BasicInfo;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.model.BasicInfoAudit;
import com.lahiru.ims.common.service.GenericBasicInfoService;
import com.lahiru.ims.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class GenericDao {
    public <Model,ID> Model getOne(ID id, JpaRepository<Model, ID> repo, String resourceName) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException(resourceName));
    }

    public <Model, ID> Boolean isExistsById(ID id, JpaRepository<Model, ID> repo) {
        return repo.existsById(id);
    }

    public <Model extends BasicInfoAudit, Dto extends BasicInfo, Service extends GenericBasicInfoService<Model>> Model checkAndCreate(AssetType assetType, Dto dto, Service service) throws Exception {
        if (dto.getId() == null) {
            return service.createOne(dto.getName(), assetType);
        }
        else return service.findOne(dto.getId());
    }
}
