package com.websystem.libspace.service;

import com.websystem.libspace.domain.carrinho.Carrinho;
import com.websystem.libspace.domain.item_carrinho.*;
import com.websystem.libspace.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemCarrinhoService {

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private ItemCarrinhoMapper itemCarrinhoMapper;

    @Autowired
    private CarrinhoService carrinhoService;

    public ItemCarrinhoResponseDTO create(ItemCarrinhoRequestDTO body){

        Carrinho carrinho = carrinhoService.findById(body.id_carrinho());

        ItemCarrinho itemCarrinho = new ItemCarrinho(body, carrinho);

        itemCarrinhoRepository.save(itemCarrinho);

        return new ItemCarrinhoResponseDTO(itemCarrinho);

    }

    public ItemCarrinho findById(Long id){

        return itemCarrinhoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<ItemCarrinhoResponseDTO> findAll(){

        return itemCarrinhoRepository.findAll().stream().map(ItemCarrinhoResponseDTO::new).toList();

    }

    public void update(ItemCarrinhoUpdateDTO updateDTO){

        ItemCarrinho itemCarrinho = findById(updateDTO.id());
        itemCarrinhoMapper.updateItemCarrinhoDTO(updateDTO, itemCarrinho);

    }

    public void deleteById(Long id){

        ItemCarrinho itemCarrinho = findById(id);
        itemCarrinhoRepository.deleteById(itemCarrinho.getId());

    }

}
