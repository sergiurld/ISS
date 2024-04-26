package ro.mpp2024.carrenting.repository;

import ro.mpp2024.carrenting.domain.Entity;

import java.util.Optional;

public interface Repository<ID, E extends Entity<ID>> {
    Optional<E> findOne(ID id);
    Iterable<E> findAll();
    Optional<E> save(E entity);
    Optional<E> delete(E entity);
    Optional<E> update(E entity);
}