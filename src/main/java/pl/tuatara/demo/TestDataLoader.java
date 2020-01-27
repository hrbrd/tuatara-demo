package pl.tuatara.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.tuatara.demo.dao.CompanyRepository;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.model.entity.User;

@Component
public class TestDataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private CompanyRepository companyRepository;

    public TestDataLoader(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new User("jtracz", "Janusz", "Tracz"));
        userRepository.save(new User("kkrawczyk", "Karol", "Krawczyk"));
        userRepository.save(new User("tnorek", "Tadeusz", "Norek"));
        userRepository.save(new User("cpazura", "Cezary", "Pazura"));
        userRepository.save(new User("blinda", "Bogusław", "Linda"));

        companyRepository.save(new Company("IBM", 10.0, 10.0));
        companyRepository.save(new Company("Tuatara", 20.0, 20.0));
        companyRepository.save(new Company("Google", 30.0, 30.0));
        companyRepository.save(new Company("Microsoft", 40.0, 40.0));
        companyRepository.save(new Company("Yahoo", 50.0, 50.0));
    }

}