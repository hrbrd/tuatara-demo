package pl.tuatara.demo.service;

import pl.tuatara.demo.model.dto.UserDto;
import pl.tuatara.demo.model.exception.UserAlreadyExistsException;

import java.util.List;

public interface IUserService {
    UserDto get(String username);
    void create(UserDto userDto) throws UserAlreadyExistsException;
    List<UserDto> getAll();
}
