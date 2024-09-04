package io.github.becxagy.s3_file.application.port.entrypoint;

import java.util.Collection;

public interface FindPort<Dto> {
    Collection<Dto> getAll();
    Dto fetchById(Long id);
}
