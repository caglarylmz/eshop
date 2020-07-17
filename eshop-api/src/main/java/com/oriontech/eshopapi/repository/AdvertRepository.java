package com.oriontech.eshopapi.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.oriontech.eshopapi.models.Advert;

public interface AdvertRepository extends CrudRepository<Advert, UUID>{

}
