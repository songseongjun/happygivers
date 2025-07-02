package util;

import java.lang.reflect.Field;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParamUtil {
	public  static <T> T get(HttpServletRequest req,Class<T> clazz) {
		try {
			T t = clazz.getDeclaredConstructor().newInstance();
//			Method[] methods = clazz.getDeclaredMethods();
//			for(Method m : methods) {
//				log.info(m.getName());
//				if(m.getName().equals("setBno")) {
//					m.invoke(t, 100L);
//				}
//			}
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) { 
				String param = req.getParameter(f.getName());
				if(param != null && !param.trim().isEmpty()) {
					
					// keyword일 경우에만 utf-8 디코딩
					if (f.getName().equals("keyword")) {
						try {
							param = URLDecoder.decode(param, "UTF-8");
						} catch (Exception e) {
							log.warn("디코딩 실패: {}", param, e);
						}
					}
					
					f.setAccessible(true);
					Object o = convert(param, f.getType());
					f.set(t, o);
					}
				}
			return t;	
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private static Object convert(String param, Class<?> type) {
		//int
		if(type == int.class || type == Integer.class) return Integer.parseInt(param);
		//long
		if(type == long.class || type == Long.class) return Long.parseLong(param);
		//boolean
		if(type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(param);
		//enum
		if(type.isEnum()) return Enum.valueOf(type.asSubclass(Enum.class), param.toUpperCase());
		//Stirng
		return param;
	}
}
