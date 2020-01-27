package pl.tuatara.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.entity.User;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserDto userDto) throws UserAlreadyExistsException {
        if(userRepository.existsById(userDto.getUsername()))
            throw new UserAlreadyExistsException();
        userRepository.save(convertToUser(userDto));
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream().map(u -> convertToDto(u))
                .collect(Collectors.toList());
    }

    private User convertToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }

}
