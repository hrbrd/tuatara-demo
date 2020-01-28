package pl.tuatara.demo.service.impl;

import org.springframework.stereotype.Service;
import pl.tuatara.demo.converter.UserConverter;
import pl.tuatara.demo.dao.UserRepository;
import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;
import pl.tuatara.demo.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto get(String username) {
        return userConverter.convertToDto(userRepository.findById(username).get());
    }

    @Override
    public void create(UserDto userDto) throws UserAlreadyExistsException {
        if(userRepository.existsById(userDto.getUsername()))
            throw new UserAlreadyExistsException();
        userRepository.save(userConverter.convertToUser(userDto));
    }

    @Override
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