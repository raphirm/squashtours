package pw.marques.squash.dao;

public interface DaoFactory {

    <T> BaseDaoImpl<T> getDao (Class<T> type);

}
