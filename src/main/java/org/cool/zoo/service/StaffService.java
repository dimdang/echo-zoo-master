package org.cool.zoo.service;

import org.cool.zoo.encode.Encode;
import org.cool.zoo.entities.pwd.ResetPwd;
import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.repositories.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 2:05 PM
 * Email    : d.dim@gl-f.com
 */

@Service
public class StaffService implements BaseServiceUtil<Staff> {

    @Autowired
    private Encode passwordEncoder;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private AuditService auditService;

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepo.findAll(pageable);
    }

    @Override
    public Staff findById(Long id) {
        return staffRepo.findOne(id);
    }

    @Override
    public Staff saveOrUpdate(Staff staff) {
        staff.setPassword(passwordEncoder.passwordEncoder().encode(staff.getPassword()));
        return staffRepo.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepo.delete(id);
    }

    public boolean validatePassword(String pwd) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (pwd.matches(pattern))
            return true;
        return false;
    }

    public Staff findByUsername(String username) {
        return staffRepo.findByUsername(username);
    }

    public Staff findByEmail(String email) {
        return staffRepo.findByEmail(email);
    }

    public Page<Staff> findAllByAuthoritiesEquals(Role authority, PageRequest pageRequest) {
        return staffRepo.findAllByAuthoritiesEquals(authority, pageRequest);
    }

    public boolean existsByPassword(String encode) {
        return staffRepo.existsByPassword(passwordEncoder.passwordEncoder().encode(encode));
    }

    public boolean resetPassword(ResetPwd resetPwd) {
        boolean reset = false;
        if (resetPwd != null && resetPwd.getPassword().equals(resetPwd.getConfirmPassword())) {
            Staff staff = staffRepo.findByEmail(resetPwd.getEmail());
            if (staff != null) {
                staff.setPassword(passwordEncoder.passwordEncoder().encode(resetPwd.getPassword()));
                staffRepo.save(staff);
                auditService.saveLoging(staff.getLoginId(), "Staff Reset Password", "rest-password");
                reset = true;
            }
        }
        return reset;
    }
}
