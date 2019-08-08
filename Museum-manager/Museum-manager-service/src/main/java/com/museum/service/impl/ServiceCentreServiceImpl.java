package com.museum.service.impl;

import com.museum.mapper.ServiceCentreMapper;
import com.museum.pojo.ServiceCentre;
import com.museum.service.ServiceCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCentreServiceImpl implements ServiceCentreService {
    @Autowired
    private ServiceCentreMapper serviceCentreMapper;
    //查询
    @Override
    public ServiceCentre getServiceCentreInfoById() {
        ServiceCentre serviceCentre = serviceCentreMapper.selectByPrimaryKey(1);
        return serviceCentre;
    }

    @Override
    public void updateServiceCentreInfoById(ServiceCentre serviceCentre) {
        serviceCentreMapper.updateByPrimaryKey(serviceCentre);
    }
}
