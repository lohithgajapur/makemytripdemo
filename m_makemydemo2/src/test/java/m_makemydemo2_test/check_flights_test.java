package m_makemydemo2_test;

import org.testng.annotations.Test;

import m_makemydemo2.Book_MakeMyTrip;

public class check_flights_test {

	@Test
	public void search_flights() throws Exception
	{
		Book_MakeMyTrip b2=new Book_MakeMyTrip();
		b2.retrive_flights_info();
	}
}