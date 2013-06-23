package com.qi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.provider.ContactsContract.RawContacts.Entity;
import android.util.Log;

public class Service {

	static String server = "http://duhan.dlinkddns.com.cn:20000";
	//static String server = "http://192.168.0.109:8080";
	static String token = "";

	static public void log(String msg) {
		Log.println(Log.ASSERT, "assert", msg);
	}

	static public String Uri2Url(String uri) {
		String url;
		if (uri.charAt(0) == '/') {
			url = server + uri;
		} else {
			url = server + "/" + uri;
		}
		return url;
	}

	static public class Result {
		public int errcode;
		public String errmsg;
		public Object data;
	}

	static public List<Map<String, Object>> getCatalogPaper(String catalogCode) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 创建request变量
			// HttpPost request = new
			// HttpPost("http://192.168.0.109:8080/qi/service/getCatalogPaper");
			String url = Uri2Url("/qi/service/getCatalogPaper");
			HttpPost request = new HttpPost(url);
			// 为request添加post参数
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("CatalogCode", catalogCode));
			// log("catalogCode=" + catalogCode);
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// 执行并获得返回结果
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(httpResponse.getEntity());
			JSONArray r1 = new JSONArray(ret);
			for (int i = 0; i < r1.length(); i++) {
				JSONObject paper = (JSONObject) r1.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", (String) paper.get("name"));
				map.put("description", (String) paper.get("description"));
				map.put("picture.url", Uri2Url((String) paper.get("picture.url")));
				// log(Uri2Url((String) paper.get("picture.url")));
				list.add(map);
			}
		} catch (Exception e) {
			log("error:" + e.toString());
		}
		return list;
	}

	static public Result userLogin(String phone, String password) {
		Result result = new Result();
		try {
			log("userLogin(\"" + phone + "\",\"" + password + "\")");
			String url = Uri2Url("/qi/service/userLogin");
			HttpPost request = new HttpPost(url);
			// 为request添加post参数
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("phone", phone));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// 执行并获得返回结果
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(httpResponse.getEntity());
			// log(ret);
			JSONObject jsonResult = new JSONObject(ret);
			result.errcode = (Integer) jsonResult.get("errcode");
			result.errmsg = (String) jsonResult.get("errmsg");
			if (result.errcode == 0) {
				// 如果成功读取并保存令牌
				JSONObject jsonData = (JSONObject) jsonResult.get("data");
				token = (String) jsonData.get("token");
			}
			// log(result.errmsg);
		} catch (Exception e) {
			result.errcode = -1;
			result.errmsg = e.toString();
			log("error:" + e.toString());
		}
		return result;
	}

	static public void isUserLogin() {
		Result result = new Result();
		try {
			log("userLogin()");
			String url = Uri2Url("/qi/service/isUserLogin");
			HttpPost request = new HttpPost(url);
			// 为request添加post参数
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("token", token));
			request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// 执行并获得返回结果
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
			String ret = EntityUtils.toString(httpResponse.getEntity());
			log(ret);
			JSONObject jsonResult = new JSONObject(ret);
			result.errcode = (Integer) jsonResult.get("errcode");
			result.errmsg = (String) jsonResult.get("errmsg");
			// result.data = (String) jsonResult.get("errmsg");
			log(result.errmsg);
		} catch (Exception e) {
			log("error:" + e.toString());
		}
	}

}
