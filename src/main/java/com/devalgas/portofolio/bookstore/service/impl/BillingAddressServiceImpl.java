package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.BillingAddress;
import com.devalgas.portofolio.bookstore.repository.BillingAddressRepository;
import com.devalgas.portofolio.bookstore.service.BillingAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:10
 * <p>
 * Service Implementation for managing {@link BillingAddress}.
 */
@Service
@Transactional
public class BillingAddressServiceImpl implements BillingAddressService {

    private final Logger log = LoggerFactory.getLogger(BillingAddressServiceImpl.class);

    private final BillingAddressRepository billingAddressRepository;

    public BillingAddressServiceImpl(BillingAddressRepository billingAddressRepository) {
        this.billingAddressRepository = billingAddressRepository;
    }

    @Override
    public BillingAddress save(BillingAddress billingAddress) {
        log.debug("Request to save BillingAddress : {}", billingAddress);
        return billingAddressRepository.save(billingAddress);
    }

    @Override
    public Optional<BillingAddress> partialUpdate(BillingAddress billingAddress) {
        log.debug("Request to partially update BillingAddress : {}", billingAddress);

        return billingAddressRepository
                .findById(billingAddress.getId())
                .map(
                        existingBillingAddress -> {
                            if (billingAddress.getBillingAddressName() != null) {
                                existingBillingAddress.setBillingAddressName(billingAddress.getBillingAddressName());
                            }
                            if (billingAddress.getBillingAddressStreet1() != null) {
                                existingBillingAddress.setBillingAddressStreet1(billingAddress.getBillingAddressStreet1());
                            }
                            if (billingAddress.getBillingAddressStreet2() != null) {
                                existingBillingAddress.setBillingAddressStreet2(billingAddress.getBillingAddressStreet2());
                            }
                            if (billingAddress.getBillingAddressCity() != null) {
                                existingBillingAddress.setBillingAddressCity(billingAddress.getBillingAddressCity());
                            }
                            if (billingAddress.getBillingAddressState() != null) {
                                existingBillingAddress.setBillingAddressState(billingAddress.getBillingAddressState());
                            }
                            if (billingAddress.getBillingAddressCountry() != null) {
                                existingBillingAddress.setBillingAddressCountry(billingAddress.getBillingAddressCountry());
                            }
                            if (billingAddress.getBillingAddressZipcode() != null) {
                                existingBillingAddress.setBillingAddressZipcode(billingAddress.getBillingAddressZipcode());
                            }

                            return existingBillingAddress;
                        }
                )
                .map(billingAddressRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillingAddress> findAll(Pageable pageable) {
        log.debug("Request to get all BillingAddresses");
        return billingAddressRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BillingAddress> findOne(Long id) {
        log.debug("Request to get BillingAddress : {}", id);
        return billingAddressRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillingAddress : {}", id);
        billingAddressRepository.deleteById(id);
    }
}
