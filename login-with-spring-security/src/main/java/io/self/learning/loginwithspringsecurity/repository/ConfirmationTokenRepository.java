package io.self.learning.loginwithspringsecurity.repository;

import io.self.learning.loginwithspringsecurity.dao.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true) public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
        ConfirmationToken findByConfirmationToken(String confirmationToken);

}
