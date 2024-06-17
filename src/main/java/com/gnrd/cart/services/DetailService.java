package com.gnrd.cart.services;

import com.gnrd.cart.entities.Detail;
import com.gnrd.cart.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }//constructor.


    public void createDetail(Detail detail){
        this.detailRepository.save(detail);
    }
    public List<Detail> getDetailBySale(String saleId){
        return this.detailRepository.findBySale_Id(saleId);
    }
}
