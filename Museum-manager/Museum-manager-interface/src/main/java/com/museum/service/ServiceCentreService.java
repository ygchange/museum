package com.museum.service;

import com.museum.pojo.ServiceCentre;

public interface ServiceCentreService {
    ServiceCentre getServiceCentreInfoById();

    void updateServiceCentreInfoById(ServiceCentre serviceCentre);
}
