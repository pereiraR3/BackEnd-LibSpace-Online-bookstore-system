package com.websystem.libspace.service;

import com.websystem.libspace.domain.carrinho.*;
import com.websystem.libspace.domain.users.User;
import com.websystem.libspace.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoMapper carrinhoMapper;

    @Autowired
    private UserService userService;

    public CarrinhoResponseDTO create(CarrinhoRequestDTO body){

        User user = userService.findById(body.id_user());

        Carrinho carrinho = new Carrinho(body, user);

        carrinhoRepository.save(carrinho);

        return new CarrinhoResponseDTO(carrinho);

    }

    public Carrinho findById(Long id){

        return carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado."));

    }

    public List<CarrinhoResponseDTO> findAll(){

        return carrinhoRepository.findAll().stream().map(CarrinhoResponseDTO::new).collect(Collectors.toList());

    }

    public void update(CarrinhoUpdateDTO updateDTO){

        Carrinho carrinho = findById(updateDTO.id());
        carrinhoMapper.updateCarrinhoDTO(updateDTO, carrinho);

    }

    public void deleteById(Long id){

        Carrinho carrinho = findById(id);
        carrinhoRepository.deleteById(carrinho.getId());

    }

}
