package com.websystem.libspace.domain.item_carrinho;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemCarrinhoMapper {

    @Mapping(target = "quantidade", source = "quantidade")
    void updateItemCarrinhoDTO(ItemCarrinhoUpdateDTO updateDTO, @MappingTarget ItemCarrinho itemCarrinho);

}
