package Packit.spring.repository;

import Packit.spring.domain.Bookmark;
import Packit.spring.domain.Store;
import Packit.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndStore(User user, Store store);
    List<Bookmark> findAllByUser(User user);
    void deleteByUserAndStore(User user, Store store);
}
