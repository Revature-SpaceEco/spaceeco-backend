package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.RegisterUserDTO;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserService userService;

  // TODO this is not a proper test
  @Test
  public void getAllUsersTest_positive() {
    List<User> userList = userService.getAllUsers();
    assertThat(userList).isNotNull();
  }


  @Test
  void createUser_positive() throws NotFound {
    UserRole role = new UserRole(1, "admin");
    User user = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, null, null, null, true, "secret");
    ModelMapper modelMapper = new ModelMapper();
    RegisterUserDTO registerUserDTO = modelMapper.map(user, RegisterUserDTO.class);
    User expected = modelMapper.map(registerUserDTO, User.class);
    expected.setActive(true);
    expected.setSecret("secret");
    UserDTO expectedDTO = modelMapper.map(expected, UserDTO.class);

    when(userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())).thenReturn(null);
    when(userRepository.save(expected)).thenReturn(expected);

    UserDTO actual = userService.createUser(user);
    Assertions.assertEquals(expectedDTO, actual);

  }

}
