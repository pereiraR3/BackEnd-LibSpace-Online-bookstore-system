package com.websystem.libspace.domain.livro;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "autor_nome", source = "autor_nome")
    @Mapping(target = "ano_publicacao", source = "ano_publicacao")
    void updateLivroFromDTO(LivroUpdateDTO updateDTO, @MappingTarget Livro livro);

}
