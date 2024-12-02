package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

}
