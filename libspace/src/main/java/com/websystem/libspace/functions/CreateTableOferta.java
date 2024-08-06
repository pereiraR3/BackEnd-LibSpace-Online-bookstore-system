package com.websystem.libspace.functions;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.oferta.Oferta;
import com.websystem.libspace.repository.OfertaRepository;
import com.websystem.libspace.utils.SpringContext;
import jakarta.persistence.PostPersist;
import org.springframework.stereotype.Component;

@Component
public class CreateTableOferta {


    @PostPersist
    public void createTableOferta(Livro livro){

        OfertaRepository ofertaRepository = SpringContext.getBean(OfertaRepository.class);

        Oferta oferta = new Oferta(livro, livro.getEditora());
        ofertaRepository.save(oferta);

    }

}
