import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Network
{
	//A List that will contains all users of this Network
	static final ArrayList<User> registeredUsers = new ArrayList<User>();
	//A Map that will contains users as key and a list of all their friends
	static final Map<User,ArrayList<User>> showfriendships = new HashMap<User,ArrayList<User>>();
	
	//Methods
	static void setAsFriends(User e,User f)
	{
		
		if (e.getName().equals(f.getName()))
		{
			System.out.println("This is exact the same person.Nothing happened.");
		}
		else
		{
			boolean friendship = Network.friendshipChecker(e,f); //True if users are friends,False if they are not
			boolean friendRequestExistence=false; //
			int i=0;
			
			//This loop checks if there has been a friend Request from one user to another 
			while ((!friendship) &&(!friendRequestExistence)&&i<e.getFriendRequestList().size()) 
			{
				if (f.getName().equals(e.getFriendRequestList().get(i).getSender()))
				{
					e.getFriendRequestList().get(i).setStatus("accepted");
					e.getFriendsList().add(f); //User e adds user f to the list of his friends
					f.getFriendsList().add(e); //The exact opposite
					friendRequestExistence=true;
				}
				if (f.getName().equals(e.getFriendRequestList().get(i).getReceiver().getName()))
				{
					e.getFriendRequestList().get(i).setStatus("accepted");
					e.getFriendsList().add(f); //User e adds user f to the list of his friends
					f.getFriendsList().add(e); //The exact opposite
					friendRequestExistence=true;
					System.out.println(e.getName()+" and "+f.getName()+" have become friends.");
				}
				++i;
			}
			if(!friendRequestExistence) System.out.println("There is no friend request from "+f.getName() + " to "+e.getName()+" or the opposite.");
		}
		
	}
	static boolean friendshipChecker(User a,User b)
	{
		int i=0;
		boolean notFriendship=true;
		if(!(a.getName().equals(b.getName()))) //If name of User A is not equal to the name of User B
		{
			while(notFriendship&&(b.getFriendsList().size()>i))
			{
				if(a.getName().contentEquals(b.getFriendsList().get(i).getName()))
				//If name of user A is equal to a name of a user in list of friends of person B
				{
					notFriendship=false;
					i++;
				}
				else i++;
			}
		}
		else notFriendship=true; //If the name of A is equal to the name of B then I consider them as not friends
		notFriendship=!notFriendship;//I should inverse this because I want to know about the friendship of 2 users not the opposite
		return notFriendship;
	}
	static void addUser(String name,String mail)
	{
		boolean userNonExistence=true;
		int i=0;
		//Loop that checks if user already exists
		while (userNonExistence && i<Network.registeredUsers.size())
		{
			if (Network.registeredUsers.get(i).getName().equals(name)) userNonExistence=false;
			++i;
		}
		if (userNonExistence==true)
		{
			User newUser=new User(name,mail);
			Network.registeredUsers.add(newUser);
			Network.showfriendships.put(newUser, newUser.getFriendsList());
			//A relation will be added to the Network's Map every time a new user is created
		}
		else System.out.println("User "+name+ " already exists");
	}
	static void removeUser(User e)
	{
		boolean userExistence=false;
		int i=0;
		while ((!userExistence)&&i<Network.registeredUsers.size())
		{
			if((e.getName()).equals(Network.registeredUsers.get(i).getName()))
			{
				Network.registeredUsers.remove(i); //Remove object from registered Users list
				Network.showfriendships.remove(e, e.getFriendRequestList()); //Remove object from friendships
				e=null;
				userExistence=true;
				if (userExistence) System.out.println("User removed (I believe).");
			}
			++i;
		}

	}
	static ArrayList<User> getFriends(User e)
	{
		//probably a try-catch block will be used
		int i=0;
		boolean userNotFound=true;
		while(userNotFound&&i<Network.registeredUsers.size())
		{
			if(e.getName().equals(Network.registeredUsers.get(i).getName())) return e.getFriendsList();
			userNotFound=false;
			++i;
		}
		return null;
	}
	static ArrayList<User> showMutualFriends(User a,User b)
	{
		ArrayList<User> commonFriends = new ArrayList<User>(a.getFriendsList().size());
		User mutualFriend;
		boolean isMutualFriend=false;
		int i=0,j=0; 
		for(i=0;i<a.getFriendsList().size();++i)
		{
			mutualFriend=a.getFriendsList().get(i);
			while ((!isMutualFriend)&&(i<b.getFriendsList().size()))
			{
				if(mutualFriend.getName().equals(b.getFriendsList().get(i).getName()))
				{
					isMutualFriend=true; //The while loop will stop
					commonFriends.add(b.getFriendsList().get(j)); //The user will be added to common Friends List of the 2 users
				}
				++j;
			}
		}
		return commonFriends;
	}
}