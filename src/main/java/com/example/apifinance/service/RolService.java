package com.example.apifinance.service;

import com.example.apifinance.model.Rol;
import com.example.apifinance.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<Rol> getAllRoles(){
        return rolRepository.findAll();
    }

    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol updateRol(Long id, Rol rolDetails) {
        return rolRepository.findById(id).map(existingRol -> {
            if (rolDetails.getDescription() != null && !rolDetails.getDescription().equals(existingRol.getDescription())) {
                existingRol.setDescription(rolDetails.getDescription());
            }
            return rolRepository.save(existingRol);
        }).orElse(null);
    }

    public void deleteRol(Long id) {
        rolRepository.deleteById(id);
    }
}
