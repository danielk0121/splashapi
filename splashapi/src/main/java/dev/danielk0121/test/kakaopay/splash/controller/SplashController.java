package dev.danielk0121.test.kakaopay.splash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.danielk0121.test.kakaopay.splash.dto.RequestHeaderMeta;
import dev.danielk0121.test.kakaopay.splash.dto.receive.ReceiveRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.receive.ReceiveResponseDto;
import dev.danielk0121.test.kakaopay.splash.dto.search.SearchRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.search.SearchResponseDto;
import dev.danielk0121.test.kakaopay.splash.dto.splash.SplashRequestDto;
import dev.danielk0121.test.kakaopay.splash.dto.splash.SplashResponseDto;
import dev.danielk0121.test.kakaopay.splash.service.ReceiveService;
import dev.danielk0121.test.kakaopay.splash.service.SearchService;
import dev.danielk0121.test.kakaopay.splash.service.SplashService;

@RestController
@RequestMapping("/apis")
public class SplashController {

	@Autowired private SplashService splashService;
	@Autowired private ReceiveService receiveService;
	@Autowired private SearchService searchService;

	@PostMapping(path = "/splash", consumes = "application/json", produces = "application/json")
	public ResponseEntity<SplashResponseDto> splash(
			@RequestBody SplashRequestDto req, 
			@RequestHeader("X-USER-ID") long userId,
			@RequestHeader("X-ROOM-ID") String roomId
			) {
		
		req.setMeta(RequestHeaderMeta.builder()
				.userId(userId)
				.roomId(roomId)
				.build());
		SplashResponseDto res = splashService.process(req);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping(path = "/receive", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ReceiveResponseDto> receive(
			@RequestBody ReceiveRequestDto req, 
			@RequestHeader("X-USER-ID") long userId,
			@RequestHeader("X-ROOM-ID") String roomId
			) {
		
		req.setMeta(RequestHeaderMeta.builder()
				.userId(userId)
				.roomId(roomId)
				.build());
		ReceiveResponseDto res = receiveService.process(req);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(path = "/search/{token}", produces = "application/json")
	public ResponseEntity<SearchResponseDto> search(
//			@RequestBody SearchRequestDto req, 
			@RequestHeader("X-USER-ID") long userId,
			@RequestHeader("X-ROOM-ID") String roomId, 
			@PathVariable("token") String token
			) {
		
		SearchRequestDto req = SearchRequestDto.builder().build();
		req.setMeta(RequestHeaderMeta.builder()
				.userId(userId)
				.roomId(roomId)
				.build());
		req.setToken(token);
		SearchResponseDto res = searchService.process(req);
		return ResponseEntity.ok(res);
	}
}
