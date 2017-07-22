package weather;

public class Constant {

/**リトライ回数*/
static public String API_URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";	

/**リトライ回数*/
static public Integer RETRY_TIMES = 3;	


/**main引数説明*/
static public String USAGE = "引数 [地域別に定義されたID番号] [出力先のファイルのパス]\n" + 
 "例；main 400040 D:\\weatherInfo.txt\n" + 
		"全国の地点定義表: http://weather.livedoor.com/forecast/rss/primary_area.xml";	

}
