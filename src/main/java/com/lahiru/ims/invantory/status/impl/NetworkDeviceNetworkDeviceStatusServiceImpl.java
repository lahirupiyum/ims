//package com.lahiru.ims.invantory.status.impl;
//
//
//import com.lahiru.ims.invantory.status.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class NetworkDeviceNetworkDeviceStatusServiceImpl implements NetworkDeviceStatusService {
//
//    private final NetworkDeviceStatusRepo statusRepo;
//
//    @Override
//    public List<NetworkDeviceStatusResponseDto> findAll() throws Exception {
//        List<Status> all = statusRepo.findAll();
//        return all.stream().map(NetworkDeviceStatusMapper::toDto).toList();
//    }
//}
