package m_makemydemo2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Book_MakeMyTrip {
	
	public void retrive_flights_info() throws Exception
	{

		String date1="14-January-2019";
		String[] dateparts=date1.split("-");
		String exdate1=dateparts[0];
		String exmonth1=dateparts[1];
		String exyear1=dateparts[2];
	 
	 System.out.println("month: "+exmonth1+" day: "+exdate1);	
		
	 System.out.println("hi there JEnkins and Maven are integrated");	
	
	 System.setProperty("webdriver.chrome.driver","G:\\Java_Software_Latest_version\\2.4 chrome driver\\chromedriver.exe");
	 ChromeDriver driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.get("https://www.makemytrip.com/");
	 String s=driver.getTitle();
	 System.out.println(s);
	 driver.findElement(By.id("hp-widget__sfrom")).clear();
	 driver.findElement(By.xpath("//ul[@id='ui-id-1']//child::li[@aria-label='Top Cities : Mumbai, India ']")).click();
	 driver.findElement(By.id("hp-widget__sTo")).clear();
	
	 driver.findElement(By.xpath("//ul[@id='ui-id-2']//child::li[@aria-label='Top Cities : Goa, India ']")).click();
	 Thread.sleep(200);
	 driver.findElement(By.id("hp-widget__depart")).click();
	 Thread.sleep(200);
	 //driver.findElement(By.xpath("//div[@class='dateFilter hasDatepicker']/div[1]/div[1]/table/tbody/tr[2]/td[7]/a")).click();
	 String adate1;
	 int flag=0;
	 int flag2=0;
	 int flag3=0;
	 int i;
	 for( i=1;i<=2;i++)
	 {
		adate1=driver.findElement(By.xpath("//div[@class='dateFilter hasDatepicker']/div/div["+i+"]/div/div/span[1]")).getText();
		if(exmonth1.equalsIgnoreCase(adate1))
		{
			flag=1;
			break;
		}
	 }
	 String aday1;
	 String xp="";
	 System.out.println("flag:"+flag);
	 if(flag==1)
	 {
		
		
		for(int j=1;j<=5;j++)
		{
			for(int k=1;k<=7;k++)
			{
				System.out.println("inside k");
				if(flag2==0)
				{
				xp="//div[@class='dateFilter hasDatepicker']/div/div["+i+"]/table/tbody/tr["+j+"]/td["+k+"]/span[1]";
				}
				else
				{
					xp="//div[@class='dateFilter hasDatepicker']/div/div["+i+"]/table/tbody/tr["+j+"]/td["+k+"]/a[1]";
				}
				Thread.sleep(200);
				aday1=driver.findElement(By.xpath(xp)).getText();
				System.out.println(aday1);
				Thread.sleep(200);
				if(!aday1.isEmpty())
				{
					if(Integer.parseInt(aday1)>31)
					{
					xp="//div[@class='dateFilter hasDatepicker']/div/div["+i+"]/table/tbody/tr["+j+"]/td["+k+"]/a[1]";
					flag2=1;
					Thread.sleep(200);
					aday1=driver.findElement(By.xpath(xp)).getText();
					}
				}
				
				System.out.println(aday1);
				if(exdate1.equalsIgnoreCase(aday1))
				{
					System.out.println("inside");
					Thread.sleep(200);
					driver.findElement(By.xpath(xp)).click();
					flag3=1;
					break;
				}
				
				if(flag3==1)
				{
					break;
				}
			}
		}
	 }
	 System.out.println("lohith end");
	 
	 
	 driver.findElement(By.id("searchBtn")).click();
	 Thread.sleep(1000);
	 
	 String xpath_nameofflight="//div[@class='clearfix listing_row append_bottom8 sponsored_list_wrapper shadow_genrator_1 c-listing_row c-listing_row--versionI ng-scope']/div/div[2]/div[1]/span/span[2]/span[1]";
	
	 String xpath_flightno="//div[@class='clearfix listing_row append_bottom8 sponsored_list_wrapper shadow_genrator_1 c-listing_row c-listing_row--versionI ng-scope']/div/div[2]/div[1]/span/span[2]/span[2]";
	 
	 List<WebElement> res_flightsnames=driver.findElements(By.xpath(xpath_nameofflight));
	 System.out.println("res_flightsnames: "+res_flightsnames.size());
	 
	 List<WebElement> res_flightnos=driver.findElements(By.xpath(xpath_flightno));
	 System.out.println("res_flightnos: "+res_flightnos.size());
	 
	 List<String> f_flightnames_String=new ArrayList<String>();
	 List<String> f_flightnos_String=new ArrayList<String>();
	 for(int a1=1;a1<res_flightsnames.size();a1++)
	 {
		 System.out.println(res_flightsnames.size());
		 System.out.println(res_flightsnames.get(a1).getText());
		 f_flightnames_String.add(res_flightsnames.get(a1).getText());
		 
		 if(a1<res_flightnos.size())
		 {
		 f_flightnos_String.add(res_flightnos.get(a1).getText());
		 }
		 else
		 {
			 f_flightnos_String.add("multiple");
		 }
	 }
	 
	System.out.println(f_flightnames_String);
	System.out.println(f_flightnos_String);
	
	FileOutputStream fo=new FileOutputStream("G:\\Java_Software_Latest_version\\New_programs_saving\\MakeMyTrip\\Lohit_Output.xlsx");
	
	Workbook wb1=new XSSFWorkbook();
	org.apache.poi.ss.usermodel.Sheet ws1=wb1.createSheet("Sheet1");
	Row row1=ws1.createRow(0);
	row1.createCell(0).setCellValue("Flightname");
	row1.createCell(1).setCellValue("FlightNumber");
	
	for(int z=1;z<=f_flightnos_String.size();z++)
	{
		Row rown=ws1.createRow(z);
		rown.createCell(0).setCellValue(f_flightnames_String.get(z-1));
		rown.createCell(1).setCellValue(f_flightnos_String.get(z-1));
	}
	
	wb1.write(fo);
	wb1.close();
	fo.close();
	driver.close();
	}
	}
	


