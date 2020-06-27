package dev.danielk0121.test.kakaopay.splash.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.kakaopay.splash.domain.Splash;

@Service
public class SplashDao {

	private static final String SQLID_insertSplash 
		= "insertSplash";
	private static final String SQLID_selectOneSplashByCreateUserIdAndTokenAndRoomId 
		= "selectOneSplashByCreateUserIdAndTokenAndRoomId";

	@Autowired private SqlSessionTemplate sqlSessionTemplate;
	
	public int insertSplash(Splash param) {
		return sqlSessionTemplate.insert(SQLID_insertSplash, param);
	}
	
	public Splash selectOneSplashByCreateUserIdAndTokenAndRoomId(Splash param) {
		return sqlSessionTemplate.selectOne(SQLID_selectOneSplashByCreateUserIdAndTokenAndRoomId, param);
	}
	
}
