package org.cool.zoo.service;

import org.cool.zoo.entities.Audit;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.repositories.AuditRepo;
import org.cool.zoo.repositories.DealerRepo;
import org.cool.zoo.repositories.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuditService {

    @Autowired
    private AuditRepo auditRepo;

    @Autowired
    private DealerRepo dealerRepo;

    @Autowired
    private StaffRepo staffRepo;

    private Dealer dealer = null;
    private Staff staff;

    public void saveLoging(String accessId, String process, String processFunction) {

        try {
            if (accessId != null && !accessId.isEmpty()) {
                Audit audit = new Audit();

                dealer = dealerRepo.findByDealerCode(accessId);
                staff = staffRepo.findByLoginId(accessId);

                if (dealer != null) {
                    audit.setUserId(dealer.getDealerId());
                    audit.setUserName(dealer.getDealerName());

                } else if (staff != null) {
                    audit.setUserId(staff.getId());
                    audit.setUserName(staff.getUsername());
                }

                audit.setPlatform("Mobile App");
                audit.setProcess(process);
                audit.setProcessFunction(processFunction);
                audit.setCreateDtm(new Date());

                auditRepo.save(audit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
