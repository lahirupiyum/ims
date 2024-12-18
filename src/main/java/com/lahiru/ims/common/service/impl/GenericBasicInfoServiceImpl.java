//package com.lahiru.ims.common.service.impl;
//
//import com.lahiru.ims.common.dto.feature.BasicInfo;
//import com.lahiru.ims.common.enums.AssetType;
//import com.lahiru.ims.common.service.GenericBasicInfoService;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//public class GenericBasicInfoServiceImpl<Response extends BasicInfo, T, R extends JpaRepository<T, Integer>> implements GenericBasicInfoService<Response> {
//    private final ModelMapper modelMapper;
//    private final R repository;
//
//    public GenericBasicInfoServiceImpl(ModelMapper modelMapper, R repository) {
//        this.modelMapper = modelMapper;
//        this.repository = repository;
//    }
//
//
//    @Override
//    public Response createOne(String name, AssetType assetType) throws Exception {
//        try {
//
//            return null;
//        } catch (Exception e) {
//            throw new Exception("Error creating entity", e);
//        }
//    }
//
//    @Override
//    public Response findOne(Integer id) throws Exception {
//        T type = repository.findById(id).orElseThrow(RuntimeException::new);
//        return mapToResponse(type);
//    }
//
//    @Override
//    public List<Response> getAll(AssetType assetType) throws Exception {
//        List<T> types = repository.findAll(); // Add filtering if needed
//        return types.stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//    private Response mapToResponse(T entity) {
//        return modelMapper.map(entity, (Class<Response>) BasicInfo.class); // Ensure Response type is passed
//    }
//
//}
