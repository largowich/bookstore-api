package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.UserShipping;
import com.devalgas.portofolio.bookstore.repository.UserShippingRepository;
import com.devalgas.portofolio.bookstore.service.UserShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:19
 * <p>
 * Service Implementation for managing {@link UserShipping}.
 */
@Service
@Transactional
public class UserShippingServiceImpl implements UserShippingService {

    private final Logger log = LoggerFactory.getLogger(UserShippingServiceImpl.class);

    private final UserShippingRepository userShippingRepository;

    public UserShippingServiceImpl(UserShippingRepository userShippingRepository) {
        this.userShippingRepository = userShippingRepository;
    }

    @Override
    public UserShipping save(UserShipping userShipping) {
        log.debug("Request to save UserShipping : {}", userShipping);
        return userShippingRepository.save(userShipping);
    }

    @Override
    public Optional<UserShipping> partialUpdate(UserShipping userShipping) {
        log.debug("Request to partially update UserShipping : {}", userShipping);

        return userShippingRepository
                .findById(userShipping.getId())
                .map(
                        existingUserShipping -> {
                            if (userShipping.getUserShippingName() != null) {
                                existingUserShipping.setUserShippingName(userShipping.getUserShippingName());
                            }
                            if (userShipping.getUserShippingStreet1() != null) {
                                existingUserShipping.setUserShippingStreet1(userShipping.getUserShippingStreet1());
                            }
                            if (userShipping.getUserShippingStreet2() != null) {
                                existingUserShipping.setUserShippingStreet2(userShipping.getUserShippingStreet2());
                            }
                            if (userShipping.getUserShippingCity() != null) {
                                existingUserShipping.setUserShippingCity(userShipping.getUserShippingCity());
                            }
                            if (userShipping.getUserShippingState() != null) {
                                existingUserShipping.setUserShippingState(userShipping.getUserShippingState());
                            }
                            if (userShipping.getUserShippingCountry() != null) {
                                existingUserShipping.setUserShippingCountry(userShipping.getUserShippingCountry());
                            }
                            if (userShipping.getUserShippingZipcode() != null) {
                                existingUserShipping.setUserShippingZipcode(userShipping.getUserShippingZipcode());
                            }
                            if (userShipping.getUserShippingDefault() != null) {
                                existingUserShipping.setUserShippingDefault(userShipping.getUserShippingDefault());
                            }

                            return existingUserShipping;
                        }
                )
                .map(userShippingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserShipping> findAll(Pageable pageable) {
        log.debug("Request to get all UserShippings");
        return userShippingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserShipping> findOne(Long id) {
        log.debug("Request to get UserShipping : {}", id);
        return userShippingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserShipping : {}", id);
        userShippingRepository.deleteById(id);
    }
}
