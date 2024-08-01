package com.websystem.libspace.domain.impostos;

public record ImpostosResponseDTO(

        Long id,

        Integer codigo,

        double percentual

) {
    public ImpostosResponseDTO(Impostos impostos) {
        this(
                impostos.getId(),
                impostos.getCodigo(),
                impostos.getPercentual()
        );
    }
}
