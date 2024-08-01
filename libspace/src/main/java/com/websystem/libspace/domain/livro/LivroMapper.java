package com.websystem.libspace.domain.livro;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(target = "preco_unitario", source = "preco_unitario")
    @Mapping(target = "preco_acumulado", source = "preco_acumulado")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "autor_nome", source = "autor_nome")
    @Mapping(target = "ano_publicacao", source = "ano_publicacao")
    void updateLivroFromDTO(LivroUpdateDTO updateDTO, @MappingTarget Livro livro);

}
