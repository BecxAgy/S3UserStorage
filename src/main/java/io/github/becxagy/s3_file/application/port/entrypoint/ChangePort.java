package io.github.becxagy.s3_file.application.port.entrypoint;

public interface ChangePort <Dto> {
    Dto save(Dto t);

    Dto updateById(Long id);

    void delete(Long id);

}