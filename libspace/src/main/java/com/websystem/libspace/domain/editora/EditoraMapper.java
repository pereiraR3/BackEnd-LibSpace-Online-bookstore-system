package com.websystem.libspace.domain.editora;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cep", source = "cep")
    @Mapping(target = "telefone", source = "telefone")
    @Mapping(target = "email", source = "email")
    void updateEditoraDTO(EditoraUpdateDTO updateDTO, @MappingTarget Editora editora);

}
