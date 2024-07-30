package com.websystem.libspace.domain.carrinho;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "data_criacao", source = "data_criacao")
    void updateCarrinhoDTO(CarrinhoUpdateDTO carrinhoUpdateDTO, @MappingTarget Carrinho carrinho);

}
