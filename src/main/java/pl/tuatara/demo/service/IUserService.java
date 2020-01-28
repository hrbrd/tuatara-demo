package pl.tuatara.demo.service;

import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;
import pl.tuatara.demo.model.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    UserDto get(String username) throws UserNotFoundException;
    void create(UserDto userDto) throws UserAlreadyExistsException;
    List<UserDto> getAll();
}
