package io.github.becxagy.s3_file.application.port.persistence;

import java.util.List;
import java.util.Optional;

public interface ReadPort <D>{
    Optional<D> findById(Long id);
    List<D> findAll();

}
