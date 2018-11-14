package cn.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	
	 /** 
     * 处理get请求. 
     * @param url  请求路径 
     * @return  json 
     */  
    public static String get(String url){  
        //实例化httpclient  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        //实例化get方法  
        HttpGet httpget = new HttpGet(url);   
        //请求结果  
        CloseableHttpResponse response = null;  
        String content ="";  
        try {  
            //执行get方法  
            response = httpclient.execute(httpget);  
            if(response.getStatusLine().getStatusCode()==200){  
                content = EntityUtils.toString(response.getEntity(),"utf-8");  
            }  
        } catch (ClientProtocolException e) {  
        	logger.error(e.getMessage(),e);
        } catch (IOException e1) {  
        	logger.error(e1.getMessage(),e1);
        }finally{
        	try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
        }
        
        return content;  
    }  
    /** 
     * 处理post请求. 
     * @param url  请求路径 
     * @param params  参数 
     * @return  json 
     */  
    public static String post(String url,Map<String, String> params){  
        //实例化httpClient  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        //实例化post方法  
        HttpPost httpPost = new HttpPost(url);   
        //处理参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();    
        Set<String> keySet = params.keySet();    
        for(String key : keySet) {    
            nvps.add(new BasicNameValuePair(key, params.get(key)));    
        }    
        //结果  
        CloseableHttpResponse response = null;  
        String content="";  
        try {  
            //提交的参数  
            UrlEncodedFormEntity uefEntity  = new UrlEncodedFormEntity(nvps, "UTF-8");  
            //将参数给post方法  
            httpPost.setEntity(uefEntity);  
            //执行post方法  
            response = httpclient.execute(httpPost);  
            if(response.getStatusLine().getStatusCode()==200){  
                content = EntityUtils.toString(response.getEntity(),"utf-8");  
            }  
        } catch (ClientProtocolException e) {  
        	logger.error(e.getMessage(),e);
        } catch (IOException e1) {  
        	logger.error(e1.getMessage(),e1);
        }finally{
        	try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
        }   
        return content;  
    }
    
    
    
   /* public static void main(String[] args) {
    	
    }*/
    
}
