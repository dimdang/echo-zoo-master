package org.cool.zoo.service;

import org.cool.zoo.encode.DefaultPwd;
import org.cool.zoo.encode.Encode;
import org.cool.zoo.entities.Branch;
import org.cool.zoo.entities.City;
import org.cool.zoo.entities.param.DealerParam;
import org.cool.zoo.entities.product.Product;
import org.cool.zoo.entities.pwd.MailParam;
import org.cool.zoo.entities.pwd.ResetPwd;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DealerService {

    @Autowired
    private DealerRepo dealerRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private Encode passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuditService auditService;

    private Dealer dealer;
    private MailService mailService;
    private boolean action = false;
    private Branch branch = null;
    private List<Product> products;


    public Dealer newDealer(DealerParam deal) {

        branch = branchRepo.findOne(deal.getBranchId());
        City city = cityRepo.findOne(deal.getCityId());
        products = productRepo.getAllByProductIdIn(deal.getProductId());
        Set<Role> roles = roleRepository.getById(1L);

        dealer = new Dealer();
        try {
            dealer.setDealerName(deal.getDealerName());
            dealer.setDealerCode(deal.getDealerCode());
            if (branch != null)
                dealer.setBranch(branch);

            dealer.setMobile(deal.getMobile());
            if (deal.getEmail() != null)
                dealer.setEmail(deal.getEmail());
            dealer.setContactPerson(deal.getContractPerson());
            if (city != null)
                dealer.setCity(city);

            dealer.setPassword(passwordEncoder.passwordEncoder().encode(deal.getPassword()));
            if (products != null && !products.isEmpty())
                dealer.setProducts(products);

            dealer.setAuthorities(roles);
            dealer.setDeleted(false);
            dealer.setCreatedBy(deal.getStaffId());
            dealer.setCreatedDate(new Date());

            dealerRepo.save(dealer);

            return dealer;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean emailResetPassword(MailParam param) {

        dealer = dealerRepo.findByEmailAndMobileAndDealerCode(param.getEmail(), param.getDealerCode(), param.getMobile());

        if (dealer != null && !dealer.isDeleted()) {
            mailService = new MailService();
            dealer.setPassword(passwordEncoder.passwordEncoder().encode(DefaultPwd.defaultPassword));
            dealer.setUpdatedDate(new Date());
            mailService.sendDefaultEmailPwd(dealer.getEmail());

            if (dealerRepo.saveAndFlush(dealer) != null) {
                action = true;
            }
        }
        return action;
    }

    public boolean resetPassword(ResetPwd resetPwd) {

        if (resetPwd != null && resetPwd.getPassword().equals(resetPwd.getConfirmPassword())) {
            Dealer dealer = dealerRepo.findByEmail(resetPwd.getEmail());
            if (dealer != null) {
                dealer.setPassword(passwordEncoder.passwordEncoder().encode(resetPwd.getPassword()));
                dealer.setUpdatedDate(new Date());
                dealerRepo.save(dealer);
                action = true;
                auditService.saveLoging(dealer.getDealerCode(), "Dealer Reset Password", "rest-password");
            }
        }
        return action;
    }

    public boolean isReallyEmailInuse(String email) {
        return dealerRepo.findByEmail(email) != null;
    }
}
