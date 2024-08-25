package by.company.servetech.repository;

import by.company.servetech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);

    @Query(value = "select public.delete_users_in_range(:startId, :endId)", nativeQuery = true)
    void deleteUsersInRange(@Param("startId") Integer startId, @Param("endId") Integer endId);
}