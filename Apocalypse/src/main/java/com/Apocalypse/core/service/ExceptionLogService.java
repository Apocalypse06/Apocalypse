////package com.Apocalypse.core.service;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.UUID;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.Apocalypse.core.bean.ExceptionLog;
//import com.Apocalypse.core.bean.Token;
//
//@Service
//public class ExceptionLogService {
//
//	@Autowired
//	private com.Apocalypse.core.dao.IExceptionLogDao exceptionLogDao;
//	@Autowired
//	private com.Apocalypse.core.dao.ITokenDao tokenDao;
//	
//	private Logger logger =  Logger.getLogger(ExceptionLogService.class);
//	
//	public void writeExceptionLog(String token, String userId, String param, String action, Throwable cause, String errCodeMsg){
//		try {
//			StringBuilder errMsg = new StringBuilder();
//			for (String element : ExceptionUtils.getRootCauseStackTrace(cause)) {
//				errMsg.append(element.trim());
//				errMsg.append("\n");
//			}
//			ExceptionLog exceptionLog = new ExceptionLog();
//			exceptionLog.setExceptionLogOid(UUID.randomUUID().toString().replace("-", StringUtils.EMPTY).trim());
//			Token entity = null;
//			if(StringUtils.isEmpty(userId)){
//				entity = tokenDao.getByToken(token);
//			}
//			else{
//				entity = tokenDao.get(userId);
//			}
//			exceptionLog.setUserId(entity == null ? "Empty User" : entity.getUserId());
//			exceptionLog.setOsType(entity == null ? "Empty osType" : entity.getOsType());
//			exceptionLog.setErrRootMsg(errCodeMsg+" "+ExceptionUtils.getRootCauseMessage(cause));
//			exceptionLog.setErrMsg(errMsg.toString());
//			exceptionLog.setErrDatetime(new Timestamp(System.currentTimeMillis()));
//			exceptionLog.setErrDate(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
//			exceptionLog.setParam(param);
//			exceptionLog.setErrAction(action);
//			exceptionLogDao.save(exceptionLog);
//		} catch (Exception e) {
//			logger.error(e);
//			e.printStackTrace();
//		}
//	}
//}
