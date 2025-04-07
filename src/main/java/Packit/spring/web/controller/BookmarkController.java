package Packit.spring.web.controller;

import Packit.spring.apiPayload.ApiResponse;
import Packit.spring.apiPayload.code.status.SuccessStatus;
import Packit.spring.domain.Bookmark;
import Packit.spring.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{userId}/{storeId}")
    public ApiResponse<String> addBookmark(@PathVariable Long userId, @PathVariable Long storeId) {
        bookmarkService.addBookmark(userId, storeId);
        return ApiResponse.onSuccess(SuccessStatus._OK, "북마크 추가됨");
    }

    @DeleteMapping("/{userId}/{storeId}")
    public ApiResponse<String> removeBookmark(@PathVariable Long userId, @PathVariable Long storeId) {
        bookmarkService.removeBookmark(userId, storeId);
        return ApiResponse.onSuccess(SuccessStatus._OK, "북마크 삭제됨");
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<Bookmark>> getUserBookmarks(@PathVariable Long userId) {
        List<Bookmark> bookmarks = bookmarkService.getUserBookmarks(userId);
        return ApiResponse.onSuccess(SuccessStatus._OK, bookmarks);
    }
}
