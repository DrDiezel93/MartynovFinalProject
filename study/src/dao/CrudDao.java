package dao;

public interface CrudDao<Long, T> extends Dao<T> {

    void create(T obj);

    T read(Long id);

    void update(T obj);

    void delete(Long id);
}
