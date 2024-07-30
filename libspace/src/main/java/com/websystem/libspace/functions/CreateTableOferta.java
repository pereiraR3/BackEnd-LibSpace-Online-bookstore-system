package com.websystem.libspace.functions;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.oferta.Oferta;
import com.websystem.libspace.repository.OfertaRepository;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTableOferta {

    @Autowired
    private OfertaRepository ofertaRepository;

    @PostPersist
    public void createTableOferta(Livro livro){

        Oferta oferta = new Oferta(livro, livro.getEditora());
        ofertaRepository.save(oferta);

    }

}
