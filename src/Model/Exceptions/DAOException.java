package Model.Exceptions;

public class DAOException extends Exception{
    public DAOException(String company_dao) {
        super(company_dao);
    }
}
