package test.playtech.PlayerDepositApp.repository;

import test.playtech.PlayerDepositApp.instances.DepositEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<DepositEvent, Long> {
    List<DepositEvent> findByPlayerId(long playerId);
}
