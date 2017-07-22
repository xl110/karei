package weather;
  

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.HttpEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import static weather.Constant.*;
  
public class Weather {  
	
	/*レスポンスエラー情報*/
	public static String statusLine;
  
	  /**

	   * 地域別に定義されたID番号

	   * @param cityCode　地域別に定義されたID番号
	   * @param filePath　天気情報保存ファイルパス
	   *  @return ture:Ok、false:NG

	   */

    public static Boolean getWeatherInfoByCityCode(String cityCode,String filePath) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            HttpGet httpget = new HttpGet(API_URL + cityCode);  
            System.out.println("executing request " + httpget.getURI());  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                HttpEntity entity = response.getEntity();  
                if(response.getStatusLine().getStatusCode() != 200){
                	statusLine = response.getStatusLine().toString();
                	return false;
                } else{
                    if (entity != null) { 
                    	String content = EntityUtils.toString(entity);
                    	JSONObject jsonObj = JSON.parseObject(content);
                    	String json =  JSON.toJSONString(jsonObj, true);
                    	System.out.println(json);  
                    	savaToFile(json,filePath);
                    }  
                }
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
		return true;  
    }  
    
    
    private static String savaToFile(String text, String filePath) {
	      try {
	  		File file = new File(filePath);
			//ファイル存在しない場合にファイル作成
			if (file.exists()) {
				if (file.isDirectory()) {
					return "ファイル保存失敗: 指定パスはディレクトリ.";
				}
				
			}else{
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}

			//情報をファイルへ書き込む
		    FileWriter fileWriter = new FileWriter(file);  
			fileWriter.write(text);
			fileWriter.close();
			return null;
		} catch (IOException e) {
			return "ファイル保存失敗:" + e.getMessage();
		}  

		
	}
  
   
   
}