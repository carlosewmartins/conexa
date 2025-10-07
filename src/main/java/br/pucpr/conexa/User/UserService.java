package br.pucpr.conexa.User;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Metodo de listagem de todos usuarios
    public List<UserModel> listarUsuarios(){
        return userRepository.findAll();
    }
}
