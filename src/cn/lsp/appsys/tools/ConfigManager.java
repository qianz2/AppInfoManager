package cn.lsp.appsys.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的工具类-单例模式（懒汉模式）
 * 懒汉模式：类加载的时候不创建实例，运行调用时创建。ConfigManager.getInstance() 调用方法获取实例
 * @author xieguihua
 * 单例模式实现步骤：
 * 1，编写私有构造函数
 * 2，自己创建实例
 * 3，提供公开的访问方法
 *
 */
public class ConfigManager {
	
	private static ConfigManager configManager;
	private static Properties properties;
	//1，私有构造器-读取数据库配置文件
	private ConfigManager(){
		String configFile = "database.properties";
		properties = new Properties();
		InputStream is = 
				ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//2，全局访问点,自己创建实例
	//注意；这种方式可能导致在多线程情况下无法工作，所以确保线程安全，可以用synchronized上锁
	public static ConfigManager getInstance(){
		//判断是否为空，如果为空就创建，不为空就直接使用，确保只有一个实例
		if(configManager == null){
			configManager = new ConfigManager();
		}
		return configManager;
	}
	
	/**
	 * 提供对外的访问方法
	 * @param key
	 * @return
	 * 通过key值获取对应的vlaue值
	 */
	public String getValue(String key){
		return properties.getProperty(key);
	}

}
