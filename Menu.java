
import java.util.Scanner;

public class Menu
{
	static String identification(Scanner s)
	{
		//Identification process
		String input="";
		boolean notFound=true;
		do
		{
			System.out.println("Enter your Username");
			input = s.next();
			int i=0;
			while(notFound && i<Network.registeredUsers.size())
			{	
				if(Network.registeredUsers.get(i).getName().equals(input)) notFound=false;
				++i;
			}
			if(notFound) System.err.println("User has not been found.Try again");
		} while(notFound);
		return input;
	}
	static void optionMenu()
	{
		System.out.println("User entered successfully.");
		System.out.println("1-See Your Wall");
		System.out.println("2-See Friend's Wall");
		System.out.println("3-Send Friend Request");
		System.out.println("4-Accept/Reject friend Request");
		System.out.println("5-See my Friends");
		System.out.println("6-Back");
		System.out.println("7-Logout");
		System.out.println("8-Exit\n");
	}
	static int navigation(String name,Scanner s)
	{
		User loginUser = User.getUserFromString(name);
		int option=-1; 
		do 
		{
			String usersChoice = s.nextLine();
			try
			{
				option=Integer.parseInt(usersChoice);
				switch (option)
				{
//--------------------------------------------------------------A Or B Or C-----------------------------------------------------------
				case 1:
					User.showMessagesInWall(loginUser);
					String layer=s.nextLine();
					switch (layer)
					{
						case "a":
							loginUser.postToWall(s);
							break;
						case "b":
							Wall.showTableOfMessages(loginUser);
							loginUser.commentToWall(s);//If selection is not a number an exception will be created			
							break;
						case "c":
							User.showMessagesInWall(loginUser.getWall());
							Wall userWall = loginUser.getWall();
							loginUser.likePost(userWall,s);
							break;
						default:
							System.err.println("You gave a wrong argument.Nothing happened.");
					}
					break;
//------------------------------------------------------Same as before A Or B Or C------------------------------------------------------
				case 2:
					User selectedUser=loginUser.userSelector(s);
					if(selectedUser!=null)
					{
						User.showMessagesInWall(selectedUser);
						String decision=s.nextLine();
						switch(decision)
						{
						case "a":
							loginUser.postToFriendsWall(s,selectedUser);
							break;
						case "b":
							Wall.showTableOfMessages(selectedUser);
							loginUser.commentToFriendsWall(s,selectedUser);//If selection is not a number an exception will be created			
							break;
						case "c":
							User.showMessagesInWall(selectedUser.getWall());
							Wall userWall = selectedUser.getWall();
							loginUser.likePost(userWall,s);
							break;
						default:
							System.err.println("You gave a wrong argument.Nothing happened.");
						}
					}
					else System.out.println("You printed a non number or a number that is not fitting...");
					break;
					
					
//--------------------------------------------------------Friend Request Sender----------------------------------------------------					
				case 3:
					System.out.println("Type a number to send a friend request to the appropriate user.");
					loginUser.showNoFriends();
					try 
					{
						int userThatWillReceiveFriendRequest =s.nextInt();
						loginUser.sendFriendRequest(Network.registeredUsers.get(userThatWillReceiveFriendRequest));
					}
					catch(IndexOutOfBoundsException e)
					{
						System.err.println("You gave a non existent option.Returning to start menu to print a new option...");
					}
					break;
//--------------------------------------------------------Friend Request Replier--------------------------------------------
				case 4:
					System.out.println("Type a number to select a friend requests that have been sent to you");
					loginUser.showFriendRequests();
					try
					{
						String selection=s.nextLine();
						int friendRequestPositionThatWillBeReplied = Integer.parseInt(selection);
						boolean loginUserIsTheReceiver=loginUser.getFriendRequestList().get(friendRequestPositionThatWillBeReplied).getReceiver().getName().equals(loginUser.getName());
						boolean statusIsPending=loginUser.getFriendRequestList().get(friendRequestPositionThatWillBeReplied).getStatus().equals("pending");
						if(loginUserIsTheReceiver && statusIsPending)
						loginUser.friendRequestReply(friendRequestPositionThatWillBeReplied,s);
						else 
						System.out.println("It seems that "+loginUser.getName()+" is not the receiver or friend request has already being answered.");
					}
					catch(IndexOutOfBoundsException e)
					{
						System.err.println("You gave a non existent option.Returning to start menu to print a new option...");
					}
					break;
//---------------------------------------------------------Show all Friends for user-----------------------------------------------
				case 5:
					loginUser.showFriends();
					break;
					
//--------------------------------------------------------------Back Button---------------------------------------------------------
				case 6:
					System.out.println("I don't know what to do.");
					break;
					
//------------------------------------------------------------------Log out-----------------------------------------------------------
				case 7:
					System.out.println("Type yes if you want to login again else press any button to exit the system.");
					String answer=s.nextLine();
					if(answer.equalsIgnoreCase("yes"))
					{
						System.out.println("You successfully loged out from your account.");
						System.out.println("Next user is going to login now.");
					}
					else
					{
						System.out.println("You successfully loged out from your account.");
						System.out.println("You will exit the social Network");
						option=8;
					}
					break;
//-------------------------------------------------------------------Exit from the system---------------------------------------------
				case 8:
					System.out.println("Exiting the system now...");
					break;
//----------------------------------------------------Appropriate message for inappropriate input--------------------------------------
				default:
					System.out.println("The option you entered is not a valid option.Try again.");
					break;
				}
			}
			catch(NumberFormatException e) {}
			finally
			{
				if((option!=8)&&(option!=7)) System.out.println("Print the option you want.");
			}
		} while((option!=8)&&(option!=7));
		return option;
	}
}

