package br.pucpr.conexa.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // API Health
    @GetMapping("/health")
    public String health(){
        return "Health: OK";
    }

    // --- CRUD ---

    // Create
    @PostMapping("/criar")
        public String criarUsuario(){
            return "Usuario criado!";
        }

    // Read
    @GetMapping("/todos")
        public List<UserModel> listarUsuarios(){
            return userService.listarUsuarios();
        }

    // Update
    @PutMapping("/atualizar")
        public String atualizarPorId(){
            return "Usuario atualizado";
    }

    // Delete
    @DeleteMapping("/deletar")
        public String deletarPorId(){
            return "Usuario deletado";
    }
}
