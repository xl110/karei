package weather;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeatherTest {

	
	@Test
	public void testArgsChk1() {
		Boolean ret = Weather.getWeatherInfoByCityCode("400040","D:\\weatherInfo.txt");
		 assertEquals(true, ret);
	}

	@Test
	public void testArgsChk2() {
		Boolean ret = Weather.getWeatherInfoByCityCode("400040","adadadasdas");
		assertEquals(false, ret);
	}

	
}
