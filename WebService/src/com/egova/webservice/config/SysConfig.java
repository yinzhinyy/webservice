package com.egova.webservice.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.egova.webservice.util.DAORowMapper;
import com.egova.webservice.util.log.LogUtils;

/**
 * 系统配置参数-初始化
 * @author yinzhinyy
 * @date 2016年9月14日 下午3:28:25
 */
@Component
public class SysConfig implements InitializingBean, DisposableBean{
	
	private static final Log logger = LogFactory.getLog(SysConfig.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static String  COMMON_SHARE_FILE_ROOT;      //共享文件目录
	public static String  COMMON_SHARE_FILE_USERNAME;  //共享文件服务器用户名
	public static String  COMMON_SHARE_FILE_PASSWORD;  //共享文件服务器密码
	
	public static String  COMMON_URL_VIRTUALDIR; //多媒体虚拟目录地址
	
	public static String   MIS_REC_DISPATCH_SERVER;           //webservice派遣接口地址	
	public static Integer MIS_REC_AUTOASSIGN_HUMANID;           //案卷到区级平台后指定办理人（虚拟节点）
	
	public void afterPropertiesSet() throws Exception {
		try {
			Field[] fields = SysConfig.class.getDeclaredFields();
			String itemGroup = "";
			if(null != fields && fields.length > 0) {
				for(Field field : fields) {
					if(Modifier.isPublic(field.getModifiers())) {
						itemGroup += "'" + field.getName()  + "',";
					}
				}
			}
			itemGroup = itemGroup.substring(0, itemGroup.length()-1);
			String sql = "select configitemname, itemvalue from dlsys.tcsysconfigitem where configitemname in (" + itemGroup + ")";
			List<ConfigItem> itemList = null;
			try {
				 itemList = jdbcTemplate.query(sql, new DAORowMapper<ConfigItem>(ConfigItem.class));
			} catch (Exception e) {
				logger.error("从数据库获取初始化参数出错！");
				e.printStackTrace();
			}
			if(null != itemList && !itemList.isEmpty()) {
				for(ConfigItem configItem : itemList) {
					Field field = SysConfig.class.getDeclaredField(configItem.getConfigItemName());
					if(field.getType().equals(Integer.class)) {
						field.set(null, Integer.valueOf(configItem.getItemValue()));
						continue;
					}
					field.set(null, configItem.getItemValue());
				}					
			}
		} catch (Exception e) {
			LogUtils.error("系统参数初始化出错！", logger, e);
		}
	}
		
	public static String getCOMMON_SHARE_FILE_ROOT() {
		return COMMON_SHARE_FILE_ROOT;
	}

	public static void setCOMMON_SHARE_FILE_ROOT(String cOMMON_SHARE_FILE_ROOT) {
		COMMON_SHARE_FILE_ROOT = cOMMON_SHARE_FILE_ROOT;
	}

	public static String getCOMMON_SHARE_FILE_USERNAME() {
		return COMMON_SHARE_FILE_USERNAME;
	}

	public static void setCOMMON_SHARE_FILE_USERNAME(String cOMMON_SHARE_FILE_USERNAME) {
		COMMON_SHARE_FILE_USERNAME = cOMMON_SHARE_FILE_USERNAME;
	}

	public static String getCOMMON_SHARE_FILE_PASSWORD() {
		return COMMON_SHARE_FILE_PASSWORD;
	}

	public static void setCOMMON_SHARE_FILE_PASSWORD(String cOMMON_SHARE_FILE_PASSWORD) {
		COMMON_SHARE_FILE_PASSWORD = cOMMON_SHARE_FILE_PASSWORD;
	}

	public static String getCOMMON_URL_VIRTUALDIR() {
		return COMMON_URL_VIRTUALDIR;
	}

	public static void setCOMMON_URL_VIRTUALDIR(String cOMMON_URL_VIRTUALDIR) {
		COMMON_URL_VIRTUALDIR = cOMMON_URL_VIRTUALDIR;
	}

	public static String getMIS_REC_DISPATCH_SERVER() {
		return MIS_REC_DISPATCH_SERVER;
	}

	public static void setMIS_REC_DISPATCH_SERVER(String mIS_REC_DISPATCH_SERVER) {
		MIS_REC_DISPATCH_SERVER = mIS_REC_DISPATCH_SERVER;
	}

	public static Integer getMIS_REC_AUTOASSIGN_HUMANID() {
		return MIS_REC_AUTOASSIGN_HUMANID;
	}

	public static void setMIS_REC_AUTOASSIGN_HUMANID(Integer mIS_REC_AUTOASSIGN_HUMANID) {
		MIS_REC_AUTOASSIGN_HUMANID = mIS_REC_AUTOASSIGN_HUMANID;
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
