package com.lahiru.ims.branch.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lahiru.ims.branch.Branch;
import com.lahiru.ims.branch.BranchMapper;
import com.lahiru.ims.branch.BranchRepo;
import com.lahiru.ims.branch.BranchSerivce;
import com.lahiru.ims.branch.dto.BranchRequestDto;
import com.lahiru.ims.branch.dto.BranchResponseDto;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchSerivce {

    public static final String BRANCH = "Branch";
    private final BranchRepo branchRepo;

    @Override
    public PaginationResponse<BranchResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Branch> allActive = branchRepo.findAllActive(pageable);

        List<BranchResponseDto> branchList = allActive.map(BranchMapper::toDto).toList();
        int totalElements = (int) allActive.getTotalElements();
    
        return new PaginationResponse<>(branchList, totalElements);
    }

    @Override
    public List<BranchResponseDto> findAll() throws Exception {
        List<Branch> allActive = branchRepo.findAllActive();
        return allActive.stream().map(BranchMapper::toDto).toList();
    }

    @Override
    public BranchResponseDto createOne(BranchRequestDto requestDto) throws Exception {
        Branch branch = BranchMapper.toModel(requestDto);
        Branch savedBranch = branchRepo.save(branch);
        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchResponseDto updateOne(int id, BranchRequestDto requestDto) throws Exception {
        if(!branchRepo.existsByIdAndStatus(id, true))
            throw new NotFoundException(BRANCH);
        Branch branch = BranchMapper.toModel(requestDto);
        branch.setId(id);
        Branch savedBranch = branchRepo.save(branch);
        return BranchMapper.toDto(savedBranch);
    }

    @Override
    public BranchResponseDto deleteOne(int id) throws Exception {
        Branch branch = branchRepo.findActiveById(id)
                            .orElseThrow(() -> new NotFoundException(BRANCH));
        branch.setStatus(false);
        branchRepo.save(branch);
        return BranchMapper.toDto(branch);
    }
    
}
