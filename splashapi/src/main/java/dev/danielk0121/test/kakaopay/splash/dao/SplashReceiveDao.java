package dev.danielk0121.test.kakaopay.splash.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danielk0121.test.kakaopay.splash.domain.SplashReceive;

@Service
public class SplashReceiveDao {

	private static final String SQLID_insertSplashReceiveList 
		= "insertSplashReceiveList" ;
	private static final String SQLID_selectListSplashReceiveBySplashSeqAndFinishYnOrderbySeq
		= "selectListSplashReceiveBySplashSeqAndFinishYnOrderbySeq" ;
	private static final String SQLID_updateSplashReceive
		= "updateSplashReceive" ;
	
	@Autowired private SqlSessionTemplate sqlSessionTemplate;
	
	public int insertSplashReceiveList(List<SplashReceive> paramList) {
		return sqlSessionTemplate.insert(SQLID_insertSplashReceiveList, paramList);
	}
	
	public List<SplashReceive> selectListSplashReceiveBySplashSeqAndFinishYnOrderbySeq(
			SplashReceive param
			) {
		return sqlSessionTemplate.selectList(SQLID_selectListSplashReceiveBySplashSeqAndFinishYnOrderbySeq, param);
	}
	
	public int updateSplashReceive(SplashReceive param) {
		return sqlSessionTemplate.update(SQLID_updateSplashReceive, param);
	}
}
