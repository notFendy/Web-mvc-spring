package uz.pdp.dao;

public interface CrudDao<T, I> {
    T save(T t);
    T getById(I d);
    T update(T t);
    boolean delete(I i);
}