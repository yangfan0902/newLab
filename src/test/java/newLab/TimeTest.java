package newLab;

import org.junit.Test;

import utils.TimeUtil;

public class TimeTest {
	
	@Test
	public void timeTest(){
		
		String time=TimeUtil.getStartTime();
		String endTime=TimeUtil.getEndTime();
		System.out.println(time);
		System.out.print(endTime);
	}
}
