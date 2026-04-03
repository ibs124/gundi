package ibs124.gundi.service.auth.user;

public interface ValidationService {

    boolean isUsernameUnique(String username);

    boolean isEmailUnique(String name);

}
