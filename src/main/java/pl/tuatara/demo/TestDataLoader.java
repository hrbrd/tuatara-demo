package pl.tuatara.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.User;

@Component
public class TestDataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public TestDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new User("jtracz", "Janusz", "Tracz"));
        userRepository.save(new User("kkrawczyk", "Karol", "Krawczyk"));
        userRepository.save(new User("tnorek", "Tadeusz", "Norek"));
        userRepository.save(new User("cpazura", "Cezary", "Pazura"));
        userRepository.save(new User("blinda", "Bogusław", "Linda"));
    }

}