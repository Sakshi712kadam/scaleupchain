package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.entities.Partnership;
import com.reactproject.startupinvestor.repositories.PartnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnershipService {
    @Autowired
    private PartnershipRepository partnershipRepository;

    public Partnership createPartnership(Partnership partnership) {
        return partnershipRepository.save(partnership);
    }

    public List<Partnership> getAllPartnerships() {
        return partnershipRepository.findAll();
    }

    public Optional<Partnership> getPartnershipById(Long id) {
        return partnershipRepository.findById(id);
    }

    public Partnership updatePartnership(Long id, Partnership updatedPartnership) {
        return partnershipRepository.findById(id).map(partnership -> {
            partnership.setName(updatedPartnership.getName());
            partnership.setPartnerType(updatedPartnership.getPartnerType());
            partnership.setStartDate(updatedPartnership.getStartDate());
            return partnershipRepository.save(partnership);
        }).orElseThrow(() -> new RuntimeException("Partnership not found with id " + id));
    }

    public void deletePartnership(Long id) {
        partnershipRepository.deleteById(id);
    }
}
