package com.devalgas.portofolio.bookstore.service.impl;

import com.devalgas.portofolio.bookstore.domain.Payment;
import com.devalgas.portofolio.bookstore.repository.PaymentRepository;
import com.devalgas.portofolio.bookstore.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 00:15
 * <p>
 * Service Implementation for managing {@link Payment}.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        log.debug("Request to save Payment : {}", payment);
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> partialUpdate(Payment payment) {
        log.debug("Request to partially update Payment : {}", payment);

        return paymentRepository
                .findById(payment.getId())
                .map(
                        existingPayment -> {
                            if (payment.getType() != null) {
                                existingPayment.setType(payment.getType());
                            }
                            if (payment.getCardNumber() != null) {
                                existingPayment.setCardNumber(payment.getCardNumber());
                            }
                            if (payment.getExpiryMonth() != null) {
                                existingPayment.setExpiryMonth(payment.getExpiryMonth());
                            }
                            if (payment.getExpiryYear() != null) {
                                existingPayment.setExpiryYear(payment.getExpiryYear());
                            }
                            if (payment.getCvc() != null) {
                                existingPayment.setCvc(payment.getCvc());
                            }
                            if (payment.getHolderName() != null) {
                                existingPayment.setHolderName(payment.getHolderName());
                            }
                            if (payment.getDefaultPayment() != null) {
                                existingPayment.setDefaultPayment(payment.getDefaultPayment());
                            }

                            return existingPayment;
                        }
                )
                .map(paymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Payment> findAll(Pageable pageable) {
        log.debug("Request to get all Payments");
        return paymentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Payment> findOne(Long id) {
        log.debug("Request to get Payment : {}", id);
        return paymentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Payment : {}", id);
        paymentRepository.deleteById(id);
    }
}
