package suw0n.simple_payment_service_with_synchronized.account.usecase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DeadLockTransferAdapter {

    private final TransferUseCase useCase;

    public DeadLockTransferAdapter(final TransferUseCase useCase) {
        this.useCase = useCase;
    }

    private final Map<String, Object> accountLocks = new ConcurrentHashMap<>();
    private Object getLock(final String id) {
        return accountLocks.computeIfAbsent(id, lock -> new Object());
    }

    public void execute(final Long amount, final String senderId, final String receiverId) {
        synchronized(getLock(senderId)) {
            synchronized(getLock(receiverId)) {
                useCase.execute(amount, senderId, receiverId);
            }
        }
    }

}
