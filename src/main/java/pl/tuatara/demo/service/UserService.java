package pl.tuatara.demo.service;

import org.springframework.stereotype.Service;
import pl.tuatara.demo.converter.UserConverter;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public UserDto get(String username) {
        return userConverter.convertToDto(userRepository.findById(username).get());
    }

    public void create(UserDto userDto) throws UserAlreadyExistsException {
        if(userRepository.existsById(userDto.getUsername()))
            throw new UserAlreadyExistsException();
        userRepository.save(userConverter.convertToUser(userDto));
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream().map(user -> {
                    UserDto userDto = userConverter.convertToDto(user);
                    userDto.setCompanies(null);
                    return userDto;
                })
                .collect(Collectors.toList());
    }

}