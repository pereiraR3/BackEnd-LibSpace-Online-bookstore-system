package com.websystem.libspace.domain.impostos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImpostosMapper {

    @Mapping(target = "codigo", source = "codigo")
    @Mapping(target = "percentual", source = "percentual")
    void updateImpostosDTO(ImpostosUpdateDTO updateDTO, @MappingTarget Impostos impostos);

}
