package weather;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

	
	@Test
	public void testArgsChk1() {
		
		String[] args ={};
		Main.main(args);
		fail("Not yet implemented");
	}

	@Test
	public void testArgsChk2() {
		
		String[] args ={"400040"};
		Main.main(args);
		fail("Not yet implemented");
	}
	
	@Test
	public void testALL() {
		
		String[] args ={"400040","D:\\weatherInfo.txt"};
		Main.main(args);
		fail("Not yet implemented");
	}
	
	@Test
	public void testRetry() {
		
		String[] args ={"abc","D:\\weatherInfo.txt"};
		Main.main(args);
		fail("Not yet implemented");
	}
	
}
