package rs.ac.uns.ftn.isa.pharmacy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PharmacyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyApplication.class, args);

        // Used for testing
//        ApplicationContext ctx = SpringApplication.run(PharmacyApplication.class, args);
//
//        PharmacyService service = (PharmacyService) ctx.getBean("pharmacyService");
//        service.getAllPharmacies().forEach(pharmacy -> {
//            System.out.println(pharmacy.toString());
//        });

//        UserRepository repository = (UserRepository) ctx.getBean("userRepository");
//
//        repository.save(new Patient("djuro@gmail.com", "djuro", "Djuro", "Djuri", "Djurina ulica 34", "Novi Sad", "Srbija", "065123123"));
//        repository.save(new Dermatologist("dermatologist4@gmail.com", "derm4"));
//        repository.save(new Pharmacist("pharmacist4@gmail.com", "pharm4"));
//
//        repository.findAll().forEach(user -> {
//            System.out.println(user.toString());
//        });
    }
}