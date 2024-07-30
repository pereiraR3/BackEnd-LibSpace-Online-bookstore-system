package com.websystem.libspace.domain.livro_ebook;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LivroEbookMapper {

    @Mapping(target = "tamanho_arquivo", source = "tamanho_arquivo")
    @Mapping(target = "formato_arquivo", source = "formato_arquivo")
    @Mapping(target = "narrador", source = "narrador")
    @Mapping(target = "url_download", source = "url_download")
    void updateLivroEbookDTO(LivroEbookUpdateDTO updateDTO, @MappingTarget  LivroEbook livroEbook);

}
