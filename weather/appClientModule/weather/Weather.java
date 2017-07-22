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
	
	/*�쥹�ݥ󥹥���`���*/
	public static String statusLine;
  
	  /**

	   * ����e�˶��x���줿ID����

	   * @param cityCode������e�˶��x���줿ID����
	   * @param filePath�������󱣴�ե�����ѥ�
	   *  @return ture:Ok��false:NG

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
			//�ե�������ڤ��ʤ����Ϥ˥ե���������
			if (file.exists()) {
				if (file.isDirectory()) {
					return "�ե����뱣��ʧ��: ָ���ѥ��ϥǥ��쥯�ȥ�.";
				}
				
			}else{
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}

			//����ե�����ؕ����z��
		    FileWriter fileWriter = new FileWriter(file);  
			fileWriter.write(text);
			fileWriter.close();
			return null;
		} catch (IOException e) {
			return "�ե����뱣��ʧ��:" + e.getMessage();
		}  

		
	}
  
   
   
}