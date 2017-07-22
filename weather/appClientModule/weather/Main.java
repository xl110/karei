package weather;

import static weather.Constant.*;

public class Main {
	public static void main(String[] args) {
		
		//���������å�
		if(args.length != 2){
			System.out.println(USAGE); 
			return;
		}
		
		//���I��
		Boolean ret;
		Integer retryTimes = 0;
		Boolean isDoRetry;
		
		do{
			//������ȡ��
			ret = Weather.getWeatherInfoByCityCode(args[0],args[1]);
			
			//ȡ�ýY���ж�
			if(ret == true){
				System.out.println("������ȡ�óɹ�."); 
				isDoRetry = false;
			}else{
				System.out.println("������ȡ��ʧ��: " + Weather.statusLine); 
				//��ȥ饤Ҫ���ж�
				retryTimes++;
				isDoRetry = (retryTimes <= RETRY_TIMES);
				if(isDoRetry){
					System.out.println("��ȥ饤�_ʼ:"+ retryTimes + "��Ŀ"); 
				}else{
					System.out.println("�I��K��."); 
				}
			}
		}while(isDoRetry);
		
		return;
	}
  

}