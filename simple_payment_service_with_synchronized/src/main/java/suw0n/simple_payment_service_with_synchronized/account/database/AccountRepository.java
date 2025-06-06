package suw0n.simple_payment_service_with_synchronized.account.database;

import suw0n.simple_payment_service_with_synchronized.account.entity.Account;

public interface AccountRepository {

    Account findById(String id);

    void save(String id, Long balance);

}
