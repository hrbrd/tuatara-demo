package pl.tuatara.demo.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.tuatara.demo.dao.CompanyRepository;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.entity.Company;
import pl.tuatara.demo.model.entity.User;

import java.util.Arrays;

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
        User[] users = new User[]{
                new User("jtracz", "Janusz", "Tracz"),
                new User("kkrawczyk", "Karol", "Krawczyk"),
                new User("tnorek", "Tadeusz", "Norek"),
                new User("cpazura", "Cezary", "Pazura"),
                new User("blinda", "Bogusław", "Linda")
        };

        for (User user : users)
            userRepository.save(user);


        Company[] companies = {
                new Company("IBM", "Krakowiaków 32", "Warsaw", "Poland", 52.127913, 20.946289),
                new Company("Tuatara", "al. Jerozolimskie 132", "Warsaw", "Poland", 52.221018, 20.973455),
                new Company("Google", "Emilii Plater 53", "Warsaw", "Poland", 52.232418, 21.001626),
        };
        companies[0].setUsers(Arrays.asList(users));
        companies[1].setUsers(Arrays.asList(Arrays.copyOfRange(users, 0, 3)));
        companies[2].setUsers(Arrays.asList(Arrays.copyOfRange(users, 2, 5)));

        for(Company company : companies)
            companyRepository.save(company);
    }

}