package com.lahiru.ims.common.service;

public interface EntityFinderService<Model>{
    Model findOne(Integer id) throws Exception;

}
