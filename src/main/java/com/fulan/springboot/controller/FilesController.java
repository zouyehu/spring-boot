package com.fulan.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.fulan.springboot.utils.FileUtils;
import com.fulan.springboot.utils.StrUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class FilesController {

	private static Log log = LogFactory.getLog(FilesController.class);
//	private static final String path_window = "E:\\app\\nasfile\\send\\";
//	private static final String path_linux = "/home/dcos/test/";


	@Value("${spring.path.window}")
	private String path_window;
	
	@Value("${spring.path.linux}")
	private String path_linux;
	
//	@Autowired
//	private RedisService redisService;

	@Autowired
	private FileUtils fileUtils;

	@PostMapping(value = "/api/authuser", produces = "application/json;charset=utf-8")
	public Map<String, Object> authuser(@RequestBody String json, HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		log.info("登录json:" + json.replace("=", "") + ",sessionId:" + sessionId);
		Map<String, Object> param = new HashMap<String, Object>();

		json = json.replace("=", "");

		//redis 取值
		/*String userInfo = (String) redisService.get("userInfo");
		log.info("redis取值:" + userInfo);*/

		//文件取值
		String userInfo = fileUtils.getUserByFile();
		String[] infos = userInfo.split("###");
		if (infos.length>1){
			userInfo = infos[0];
		}
		String[] user = userInfo.split("_");

		JSONObject object = JSONObject.parseObject(StrUtils.decode(json));

		if (user[0].equals(object.getString("name")) && user[1].equals(object.getString("pwd"))) {

//			boolean set = redisService.set(StrUtils.pattern + sessionId, StrUtils.getRandomNumber(32));
			fileUtils.setFileSession(StrUtils.getRandomNumber(32));
			log.info("登录成功，设置缓存成功" );
			param.put("code", 1);
			param.put("message", "success");
			return param;
		}

		param.put("code", 0);
		param.put("message", "fail");
		return param;
	}

	@PostMapping(value = "/api/files", produces = "application/json;charset=utf-8")
	public Map<String, Object> files(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> fileNames = multiRequest.getFileNames();
			String property = System.getProperty("os.name");
			String path = "";

			if (property.startsWith("Linux")) {
				path = path_linux;
			} else if (property.startsWith("Windows")) {
				path = path_window;
			}
			File pathFile = new File(path);

			if (!pathFile.exists() && !pathFile.isDirectory()) {
				pathFile.mkdirs();
			}

			while (fileNames.hasNext()) {
				MultipartFile file = multiRequest.getFile(fileNames.next());
				log.info("上传文件名:" + file.getOriginalFilename());
				if (!StrUtils.isEmpty(file.getOriginalFilename())) {
					File newFile = new File(pathFile + "/" + file.getOriginalFilename());
					newFile.delete();
					newFile.createNewFile();
					file.transferTo(newFile);
				}
			}
			param.put("code", 1);
			param.put("message", "success");
		} catch (Exception e) {
			log.info("上传文件出错:" + e.getMessage());
			param.put("code", 0);
			param.put("message", "fail");
		}
		return param;
	}

	@GetMapping(value = "/api/getfiles", produces = "application/json;charset=utf-8")
	public List<String> getfiles() {
		List<String> list = new ArrayList<>();
		String property = System.getProperty("os.name");
		String path = "";

		if (property.startsWith("Linux")) {
			path = path_linux;
		} else if (property.startsWith("Windows")) {
			path = path_window;
		}
		File pathFile = new File(path);
		File[] listFiles = pathFile.listFiles();
		for (File file : listFiles) {
			list.add("文件名:" + file.getName() + ",大小:" + file.length());
		}
		return list;
	}

	@GetMapping(value = "/api/download", produces = "application/json;charset=utf-8")
	public Map<String, Object> download(String name, HttpServletResponse response) {
		log.info("下载文件参数：" + name);
		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject object = JSONObject.parseObject(name);
		name = object.getString("name");
		if (StrUtils.isEmpty(name)) {
			param.put("code", 0);
			param.put("message", "文件不存在");
			return param;
		}

		String property = System.getProperty("os.name");
		String path = "";

		if (property.startsWith("Linux")) {
			path = path_linux;
		} else if (property.startsWith("Windows")) {
			path = path_window;
		}
		File file = new File(path + name);

		if (file.exists() && file.isFile()) {
			try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis);) {
				response.setHeader("content-type", "application/octet-stream");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
				byte[] buffer = new byte[1024];
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				log.info("下载文件异常：" + e.getMessage());
			}
		}
		file.delete();
		param.put("code", 0);
		param.put("message", "文件不存在");
		return param;
	}

	@GetMapping(value = "/api/getuser", produces = "application/json;charset=utf-8")
	public Map<String, Object> getuser() {
		Map<String, Object> param = new HashMap<String, Object>();
		String userInfo = fileUtils.getUserByFile();
		String[] infos = userInfo.split("###");
		if (infos.length>1){
			userInfo = infos[0];
		}
		param.put("code", 1);
		param.put("message", StrUtils.getRandomNumber(3).concat(userInfo).concat(StrUtils.getRandomNumber(3)));
		return param;
	}

}
