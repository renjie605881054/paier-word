package com.paier.word.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

public class HttpUtil {
	/** 公用HttpClient */
	private static CloseableHttpClient HttpClient;

	/** HTTP请求参数 */
	public static final RequestConfig HttpRequestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();

	/** 默认字符集 */
	private static final String DEFAULT_CHAR_SET = "UTF-8";

	/** 日志记录器 */
	private static Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

	static {
		try {
			// 信任所有的HTTPS证书
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, new SecureRandom());
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);
			HttpClient = HttpClientBuilder.create().setSSLSocketFactory(sslSocketFactory).setMaxConnTotal(200)
					.setMaxConnPerRoute(200).build();
		} catch (Exception e) {
			LOG.error("http异常", e);
		}
	}

	/**
	 * 获取真实的请求地址
	 * @date 2015年4月22日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static String getUrl(String url, Map<String, Object> params) {
		return getUrl(url, params, DEFAULT_CHAR_SET);
	}

	/**
	 * 获取真实的请求地址
	 * @date 2015年4月22日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getUrl(String url, Map<String, Object> params, String charSet) {
		StringBuffer sb = new StringBuffer(url);
		try {
			if ((params != null) && (params.size() > 0)) {
				if (url.contains("?")) {
					sb.append("&");
				} else {
					sb.append("?");
				}	
				for (Entry<String, Object> entry : params.entrySet())
					sb.append(URLEncoder.encode(entry.getKey(), charSet)).append("=")
							.append(URLEncoder.encode(String.valueOf(entry.getValue()), charSet)).append("&");

				sb.deleteCharAt(sb.length() - 1);
			}
		} catch (UnsupportedEncodingException e) {
			LOG.error("getUrl异常", e);
		}
		return sb.toString();
	}

	/**
	 * GET方式发送HTTP数据
	 * @date 2015年2月28日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static HttpResult sendGet(String url, Map<String, Object> params) {
		return sendGet(url, params, DEFAULT_CHAR_SET);
	}

	/**
	 * GET方式发送HTTP数据
	 * @date 2015年2月28日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static HttpResult sendGet(String url, Map<String, Object> params, String charSet) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;
		try {
			HttpGet httpGet = new HttpGet(getUrl(url, params));
			httpGet.setConfig(HttpRequestConfig);
			remoteResponse = HttpClient.execute(httpGet);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), charSet);
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				LOG.info("\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}" + ",url" + url + ",statusCode"
						+ statusCode + ",result" + result);
			}
		} catch (Exception e) {
			LOG.error("sendGet异常", e);
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				LOG.error("IO异常", e);
			}
		}

		return sendResult;
	}

	/**
	 * POST方式发送HTTP数据
	 * @date 2015年4月22日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static HttpResult sendPost(String url, Map<String, Object> params) {
		return sendPost(url, params, DEFAULT_CHAR_SET);
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", "N4350667");
		map.put("password", "pYEhJ5NP793f51");
		map.put("msg", "【沃顿金服】您的验证码是：2550");
		map.put("phone", "15858147027");
		map.put("sendtime", "201706051433");
		map.put("report", "false");
		map.put("extend", "253");
		HttpResult result = sendPost("https://vsms.253.com/msg/send/json", JSONArray.toJSONString(map), "UTF-8");
		System.out.println(result.getContent() + ":" + result.getStatusCode());
		
	}
	
	/**
	 * POST方式发送HTTP数据
	 * @date 2015年4月22日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static HttpResult sendPost(String url, Map<String, Object> params, String charSet) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(HttpRequestConfig);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : params.entrySet())
				nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charSet));

			remoteResponse = HttpClient.execute(httpPost);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), charSet);
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				LOG.info("\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}" + ",url" + url + ",statusCode"
						+ statusCode + ",result" + result);
			}
		} catch (Exception e) {
			LOG.error("sendPost异常", e);
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				LOG.error("IO异常", e);
			}
		}

		return sendResult;
	}

	/**
	 * 直接POST字符串数据
	 * @date 2015年4月22日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static HttpResult sendPost(String url, String data) {
		return sendPost(url, data, DEFAULT_CHAR_SET);
	}

	/**
	 * 直接POST字符串数据
	 * @date 2015年4月22日
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static HttpResult sendPost(String url, String data, String charSet) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(HttpRequestConfig);
			httpPost.setEntity(new StringEntity(data, charSet));

			remoteResponse = HttpClient.execute(httpPost);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), charSet);
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				LOG.info("\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}" + ",url" + url + ",statusCode"
						+ statusCode + ",result" + result);
			}
		} catch (Exception e) {
			LOG.error("sendPost异常", e);
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				LOG.error("IO异常", e);
			}
		}
		return sendResult;
	}

}
