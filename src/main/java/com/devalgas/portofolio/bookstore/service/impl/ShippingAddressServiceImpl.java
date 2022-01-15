package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.ShippingAddress;
import com.devalgas.portofolio.bookstore.repository.ShippingAddressRepository;
import com.devalgas.portofolio.bookstore.service.ShippingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:16
 * <p>
 * Service Implementation for managing {@link ShippingAddress}.
 */
@Service
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressServiceImpl.class);

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    @Override
    public ShippingAddress save(ShippingAddress shippingAddress) {
        log.debug("Request to save ShippingAddress : {}", shippingAddress);
        return shippingAddressRepository.save(shippingAddress);
    }

    @Override
    public Optional<ShippingAddress> partialUpdate(ShippingAddress shippingAddress) {
        log.debug("Request to partially update ShippingAddress : {}", shippingAddress);

        return shippingAddressRepository
                .findById(shippingAddress.getId())
                .map(
                        existingShippingAddress -> {
                            if (shippingAddress.getShippingAddressName() != null) {
                                existingShippingAddress.setShippingAddressName(shippingAddress.getShippingAddressName());
                            }
                            if (shippingAddress.getShippingAddressStreet1() != null) {
                                existingShippingAddress.setShippingAddressStreet1(shippingAddress.getShippingAddressStreet1());
                            }
                            if (shippingAddress.getShippingAddressStreet2() != null) {
                                existingShippingAddress.setShippingAddressStreet2(shippingAddress.getShippingAddressStreet2());
                            }
                            if (shippingAddress.getShippingAddressCity() != null) {
                                existingShippingAddress.setShippingAddressCity(shippingAddress.getShippingAddressCity());
                            }
                            if (shippingAddress.getShippingAddressState() != null) {
                                existingShippingAddress.setShippingAddressState(shippingAddress.getShippingAddressState());
                            }
                            if (shippingAddress.getShippingAddressCountry() != null) {
                                existingShippingAddress.setShippingAddressCountry(shippingAddress.getShippingAddressCountry());
                            }
                            if (shippingAddress.getShippingAddressZipcode() != null) {
                                existingShippingAddress.setShippingAddressZipcode(shippingAddress.getShippingAddressZipcode());
                            }

                            return existingShippingAddress;
                        }
                )
                .map(shippingAddressRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShippingAddress> findAll(Pageable pageable) {
        log.debug("Request to get all ShippingAddresses");
        return shippingAddressRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShippingAddress> findOne(Long id) {
        log.debug("Request to get ShippingAddress : {}", id);
        return shippingAddressRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShippingAddress : {}", id);
        shippingAddressRepository.deleteById(id);
    }
}
