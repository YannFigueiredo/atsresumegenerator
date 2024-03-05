package com.yannfigueiredo.atsresumegenerator.services;

import com.yannfigueiredo.atsresumegenerator.models.User;
import com.yannfigueiredo.atsresumegenerator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado!"
        ));
    }

    @Transactional
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        try {
            User newUser = this.userRepository.getReferenceById(id);

            newUser.setEmail(user.getEmail() != null ? user.getEmail() : newUser.getEmail());
            newUser.setUsername(user.getUsername() != null ? user.getUsername() : newUser.getUsername());
            newUser.setPassword(user.getPassword() != null ? user.getPassword() : newUser.getPassword());

            return this.userRepository.save(newUser);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível atualizar o usuário! Erro: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possível deletar o usuário! Erro: " + e.getMessage());
        }
    }
}
