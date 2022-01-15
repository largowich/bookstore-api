package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.UserBilling;
import com.devalgas.portofolio.bookstore.repository.UserBillingRepository;
import com.devalgas.portofolio.bookstore.service.UserBillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:17
 * <p>
 * Service Implementation for managing {@link UserBilling}.
 */
@Service
@Transactional
public class UserBillingServiceImpl implements UserBillingService {

    private final Logger log = LoggerFactory.getLogger(UserBillingServiceImpl.class);

    private final UserBillingRepository userBillingRepository;

    public UserBillingServiceImpl(UserBillingRepository userBillingRepository) {
        this.userBillingRepository = userBillingRepository;
    }

    @Override
    public UserBilling save(UserBilling userBilling) {
        log.debug("Request to save UserBilling : {}", userBilling);
        return userBillingRepository.save(userBilling);
    }

    @Override
    public Optional<UserBilling> partialUpdate(UserBilling userBilling) {
        log.debug("Request to partially update UserBilling : {}", userBilling);

        return userBillingRepository
                .findById(userBilling.getId())
                .map(
                        existingUserBilling -> {
                            if (userBilling.getUserBillingName() != null) {
                                existingUserBilling.setUserBillingName(userBilling.getUserBillingName());
                            }
                            if (userBilling.getUserBillingStreet1() != null) {
                                existingUserBilling.setUserBillingStreet1(userBilling.getUserBillingStreet1());
                            }
                            if (userBilling.getUserBillingStreet2() != null) {
                                existingUserBilling.setUserBillingStreet2(userBilling.getUserBillingStreet2());
                            }
                            if (userBilling.getUserBillingCity() != null) {
                                existingUserBilling.setUserBillingCity(userBilling.getUserBillingCity());
                            }
                            if (userBilling.getUserBillingState() != null) {
                                existingUserBilling.setUserBillingState(userBilling.getUserBillingState());
                            }
                            if (userBilling.getUserBillingCountry() != null) {
                                existingUserBilling.setUserBillingCountry(userBilling.getUserBillingCountry());
                            }
                            if (userBilling.getUserBillingZipcode() != null) {
                                existingUserBilling.setUserBillingZipcode(userBilling.getUserBillingZipcode());
                            }

                            return existingUserBilling;
                        }
                )
                .map(userBillingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserBilling> findAll(Pageable pageable) {
        log.debug("Request to get all UserBillings");
        return userBillingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserBilling> findOne(Long id) {
        log.debug("Request to get UserBilling : {}", id);
        return userBillingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserBilling : {}", id);
        userBillingRepository.deleteById(id);
    }
}
