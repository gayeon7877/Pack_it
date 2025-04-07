package Packit.spring.web.controller;

import Packit.spring.apiPayload.ApiResponse;
import Packit.spring.apiPayload.code.status.SuccessStatus;
import Packit.spring.domain.Bookmark;
import Packit.spring.service.BookmarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookmarkControllerTest {

    @Mock
    private BookmarkService bookmarkService; // BookmarkService를 Mock 객체로 생성

    @InjectMocks
    private BookmarkController bookmarkController; // 테스트할 대상 컨트롤러에 Mock 주입

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // @Mock, @InjectMocks 어노테이션을 초기화
    }

    @Test
    void addBookmark_shouldReturnSuccessMessage() {
        // given
        Long userId = 1L;
        Long storeId = 10L;

        // when
        ApiResponse<String> response = bookmarkController.addBookmark(userId, storeId);

        // then
        verify(bookmarkService).addBookmark(userId, storeId); // addBookmark가 호출되었는지 검증
        assertTrue(response.isSuccess()); // 성공 응답인지 확인
        assertEquals("북마크 추가됨", response.getData()); // 응답 메시지 확인
    }

    @Test
    void removeBookmark_shouldReturnSuccessMessage() {
        // given
        Long userId = 1L;
        Long storeId = 10L;

        // when
        ApiResponse<String> response = bookmarkController.removeBookmark(userId, storeId);

        // then
        verify(bookmarkService).removeBookmark(userId, storeId); // removeBookmark가 호출되었는지 확인
        assertTrue(response.isSuccess());
        assertEquals("북마크 삭제됨", response.getData());
    }

    @Test
    void getUserBookmarks_shouldReturnListOfBookmarks() {
        // given
        Long userId = 1L;
        Bookmark b1 = Bookmark.builder().id(1L).build();
        Bookmark b2 = Bookmark.builder().id(2L).build();
        List<Bookmark> mockBookmarks = Arrays.asList(b1, b2);
        when(bookmarkService.getUserBookmarks(userId)).thenReturn(mockBookmarks); // mock 동작 설정

        // when
        ApiResponse<List<Bookmark>> response = bookmarkController.getUserBookmarks(userId);

        // then
        verify(bookmarkService).getUserBookmarks(userId); // 호출 여부 확인
        assertTrue(response.isSuccess()); // 상태 확인
        assertEquals(2, response.getData().size()); // 데이터 사이즈 확인
        assertEquals(1L, response.getData().get(0).getId()); // 첫 북마크 ID 확인
    }
}