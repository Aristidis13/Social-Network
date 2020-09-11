import java.util.Scanner;

public class MAIN_CLASS
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		HelperClass.userAdderToNetwork();
		int option;
		do 
		{
			String name = Menu.identification(scan);
			Menu.optionMenu();
			option = Menu.navigation(name,scan);
		} while (option==7);
		scan.close();
	}

}
