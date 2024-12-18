package com.lahiru.ims.feature.inventory.model.impl;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.model.ModelRepo;
import com.lahiru.ims.feature.inventory.model.ModelService;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ModelRepo repository;

    @Override
    public ModelDto createOne(String name, AssetType assetType) throws Exception {
        try {
            AssetType validAssetType = AssetType.valueOf(assetType.toString().toUpperCase());
            Model saveModel = repository.save(new Model(name, validAssetType));
            return modelMapper.map(saveModel, ModelDto.class);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public ModelDto findOne(Integer id) throws Exception {
        Model model = repository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(model, ModelDto.class);
    }

    @Override
    public List<ModelDto> getAll(AssetType assetType) throws Exception {
        List<Model> modelList = repository.findAllByAssetType(assetType);
        return (!modelList.isEmpty()) ? modelMapper
                .map(modelList, new TypeToken<List<ModelDto>>() {
                }
                        .getType()) : Collections.emptyList();
    }
}
