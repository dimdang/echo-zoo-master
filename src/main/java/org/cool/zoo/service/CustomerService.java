package org.cool.zoo.service;

import org.cool.zoo.entities.customer.CusParam;
import org.cool.zoo.entities.customer.Customer;
import org.cool.zoo.entities.document.Document;
import org.cool.zoo.entities.product.Product;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.repositories.*;
import org.cool.zoo.util.AppConfigFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class CustomerService {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DealerRepo dealerRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private DocumentRepo documentRepo;

    private String documentTag = "";

    private String FILE_PATH = AppConfigFile.getInstance().getValue("base_paths");


    public CusParam saveNewCustomer(MultipartFile[] files, CusParam cusParam) throws IOException {

        CusParam cus = null;
        Customer customer = null;
        try {
            if (cusParam != null) {

                if (cusParam.getCustomerId() == 0 || cusParam.getCustomerId() == null) {
                    customer = new Customer();
                    documentTag = "NEW";
                } else {
                    documentTag = "UPDATE";
                    customer = customerRepo.getById(cusParam.getCustomerId());
                }

                Staff staff = staffRepo.findOne(cusParam.getStaffId());
                Product product = productRepo.findOne(cusParam.getProductId());
                Dealer dealer = dealerRepo.findOne(cusParam.getDealerId());

                if (staff != null) {
                    customer.setStaff(staff);
                    customer.setCreatedBy(staff.getId());
                } else customer.setCreatedBy(dealer.getDealerId());
                if (product != null)
                    customer.setProduct(product);

                customer.setDealer(dealer);
                if (cusParam.getTag() != null)
                    customer.setTag(cusParam.getTag());

                customer.setCreatedDate(new Date());
                customer.setApproved(false);

                customerRepo.save(customer);

//                String filePath = "customers" + File.separator + customer.getId();
//                File direction = new File(FILE_PATH + File.separator + filePath);
//
//                if (!direction.exists()) {
//                    direction.mkdirs();
//                }
//
//                Arrays.asList(files).stream().forEach(file -> {
//                    try {
//                        FileHelper.writeToFile(file.getInputStream(), direction + File.separator + file.getOriginalFilename());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println(e + "Write file error");
//                    }
//                });

                Document document = new Document();
                document.setCreatedDate(new Date());
                document.setCustomer(customer);
                document.setName("Application Photos & Other Photo");

                document.setPath("test location");
//                document.setPath(direction.toString());
                document.setDocumentTag(documentTag);
                documentRepo.save(document);

                cusParam.setCustomerId(customer.getId());
                cus = cusParam;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cus;
    }

}
