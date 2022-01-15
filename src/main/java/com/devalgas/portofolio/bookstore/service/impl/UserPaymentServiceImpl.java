package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.UserPayment;
import com.devalgas.portofolio.bookstore.repository.UserPaymentRepository;
import com.devalgas.portofolio.bookstore.service.UserPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:18
 * <p>
 * Service Implementation for managing {@link UserPayment}.
 */
@Service
@Transactional
public class UserPaymentServiceImpl implements UserPaymentService {

    private final Logger log = LoggerFactory.getLogger(UserPaymentServiceImpl.class);

    private final UserPaymentRepository userPaymentRepository;

    public UserPaymentServiceImpl(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    public UserPayment save(UserPayment userPayment) {
        log.debug("Request to save UserPayment : {}", userPayment);
        return userPaymentRepository.save(userPayment);
    }

    @Override
    public Optional<UserPayment> partialUpdate(UserPayment userPayment) {
        log.debug("Request to partially update UserPayment : {}", userPayment);

        return userPaymentRepository
                .findById(userPayment.getId())
                .map(
                        existingUserPayment -> {
                            if (userPayment.getType() != null) {
                                existingUserPayment.setType(userPayment.getType());
                            }
                            if (userPayment.getCardName() != null) {
                                existingUserPayment.setCardName(userPayment.getCardName());
                            }
                            if (userPayment.getCardNumber() != null) {
                                existingUserPayment.setCardNumber(userPayment.getCardNumber());
                            }
                            if (userPayment.getExpiryMonth() != null) {
                                existingUserPayment.setExpiryMonth(userPayment.getExpiryMonth());
                            }
                            if (userPayment.getExpiryYear() != null) {
                                existingUserPayment.setExpiryYear(userPayment.getExpiryYear());
                            }
                            if (userPayment.getCvc() != null) {
                                existingUserPayment.setCvc(userPayment.getCvc());
                            }
                            if (userPayment.getHolderName() != null) {
                                existingUserPayment.setHolderName(userPayment.getHolderName());
                            }
                            if (userPayment.getDefaultPayment() != null) {
                                existingUserPayment.setDefaultPayment(userPayment.getDefaultPayment());
                            }

                            return existingUserPayment;
                        }
                )
                .map(userPaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserPayment> findAll(Pageable pageable) {
        log.debug("Request to get all UserPayments");
        return userPaymentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserPayment> findOne(Long id) {
        log.debug("Request to get UserPayment : {}", id);
        return userPaymentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPayment : {}", id);
        userPaymentRepository.deleteById(id);
    }
}
