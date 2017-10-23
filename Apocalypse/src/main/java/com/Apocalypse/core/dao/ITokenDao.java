package com.Apocalypse.core.dao;

import java.util.List;
import java.util.Map;

import com.Apocalypse.core.bean.Token;

public interface ITokenDao {
	
	public void createToken(String userId, String token, String deviceId, String osType, String msgUid) throws Exception;
	
	public void delteToken(String userId) throws Exception;
	
	public boolean isValid(String token) throws Exception;
	
	public Token get(String userId) throws Exception;
	
	public Token getByToken(String token) throws Exception;
	
	public List<Token> getList(Map<String,Object> conditions) throws Exception;
	
	public String findByToken(String token) throws Exception;
	
	public void updateMsgUid(String msgUid, String token) throws Exception;
	
}
