package Packit.spring.service;

import Packit.spring.domain.Bookmark;
import Packit.spring.domain.Store;
import Packit.spring.domain.User;
import Packit.spring.repository.BookmarkRepository;
import Packit.spring.repository.StoreRepository;
import Packit.spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void addBookmark(Long userId, Long storeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("유저가 없습니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new IllegalArgumentException("가게가 없습니다."));

        bookmarkRepository.findByUserAndStore(user,store)
                .ifPresent(b->{throw new IllegalStateException("이미 북마크 됨");});

        Bookmark bookmark = Bookmark.builder().user(user).store(store).build();

        bookmarkRepository.save(bookmark);
    }
    @Transactional
    public void removeBookmark(Long userId, Long storeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 없음"));

        bookmarkRepository.deleteByUserAndStore(user, store);
    }
    public List<Bookmark> getUserBookmarks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        return bookmarkRepository.findAllByUser(user);
    }

}
