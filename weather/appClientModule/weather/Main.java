package weather;

import static weather.Constant.*;

public class Main {
	public static void main(String[] args) {
		
		//引数チェック
		if(args.length != 2){
			System.out.println(USAGE); 
			return;
		}
		
		//主処理
		Boolean ret;
		Integer retryTimes = 0;
		Boolean isDoRetry;
		
		do{
			//天気情報取得
			ret = Weather.getWeatherInfoByCityCode(args[0],args[1]);
			
			//取得結果判定
			if(ret == true){
				System.out.println("天気情報取得成功."); 
				isDoRetry = false;
			}else{
				System.out.println("天気情報取得失敗: " + Weather.statusLine); 
				//リトライ要否判定
				retryTimes++;
				isDoRetry = (retryTimes <= RETRY_TIMES);
				if(isDoRetry){
					System.out.println("リトライ開始:"+ retryTimes + "回目"); 
				}else{
					System.out.println("処理終了."); 
				}
			}
		}while(isDoRetry);
		
		return;
	}
  

}