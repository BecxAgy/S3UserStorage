package io.github.becxagy.s3_file.shared;

public interface RestConverter<D, R> {

    default D mapToDomain(final R restObject) {
        throw new UnsupportedOperationException();
    }

    default R mapToRest(final D domainObject) {
        throw new UnsupportedOperationException();
    }
}
