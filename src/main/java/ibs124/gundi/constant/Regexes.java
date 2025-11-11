package ibs124.gundi.constant;

public abstract class Regexes {

    public static final String EMAIL = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";

    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{5,}$";

    public static final String USERNAME = "^(?:[a-z0-9_]{3,20})$";

}
