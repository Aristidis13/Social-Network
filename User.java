import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class User
{
	//declaration for every object variable
	private String name,email;
	private ArrayList<FriendRequest> friendRequestList;
	private Wall myWall; 
	private ArrayList<User> friendsList; //A list that contains all friends for the user
	private ArrayList<Message> likedPosts; //All liked posts for the user will exist here

	//Constructor
	User(String s,String mail)
	{
		this.name=s;
		this.email=mail;
		this.myWall=new Wall();
		this.friendRequestList = new ArrayList<FriendRequest>(); //A list of friend requests that is null
		this.friendsList=new ArrayList<User>();  // A null list of friends is created
		this.likedPosts=new ArrayList<Message>();
	}
	
	// Methods for Posting
	User userSelector(Scanner s)
	{
		User returnedUser=null;
		int i;
		System.out.println("Pick one of your friends to post to his wall.");
		for(i=0;i<this.getFriendsList().size();++i)
		{
			System.out.println(i+"."+this.getFriendsList().get(i).getName());
		}
		String input =s.nextLine();
		try
		{
			int choice=Integer.parseInt(input);
			returnedUser=this.getFriendsList().get(choice);
			return returnedUser;
		}
		catch(NumberFormatException e)
		{
			return returnedUser;
		}
		catch(IndexOutOfBoundsException e)
		{
			return returnedUser;
		}
		
	}
	static void showMessagesInWall(Wall a) //Method that will print all posts in the wall to be liked
	{
		System.out.println("Select one the above messages to like:");
		int i;
		for(i=0;i<a.getMessageList().size();i++)
		{
			System.out.println(i+"."+a.getMessageList().get(i).toString());
		}
	}
	static void showMessagesInWall(User e)
	{
		String allPosts="";
		if (e.getWall().getMessageList().size()==0)
		{
			System.out.println("No messages found.Wall is empty.");
		}
		else
		{
			allPosts =e.getWall().toString();
		}
		System.out.println("Activity to this wall:\n");
		System.out.println(allPosts);
		System.out.println("\nPrint a to post a message.");
		System.out.println("Print b to comment to a message");
		System.out.println("Print c to like a post.");
	}
	void commentToFriendsWall(Scanner s,User e)
	{
		System.out.println("Type the number of message you want to comment.");
		String choice=s.nextLine();
		try
		{
			int selection=Integer.parseInt(choice);
			Message selectedMessage=e.getWall().getMessageList().get(selection);
			if (selectedMessage.getFatherMessage()==null)
			{
				System.out.println("Type your reply to your friends wall.");
				String replyText = s.nextLine();
				Message aReply=new Message(this.getName(),replyText,selectedMessage);
				Message.setReply(aReply,selectedMessage);
				e.getWall().getMessageList().add(aReply); //Added to ArrayList of Messages of the user's Wall
				e.getWall().getLikes().add(0); // 0 is added to Likes of the user's Wall at the appropriate position
				System.out.println("Comment posted successfully.");
			}
			else System.err.println("You can't reply to a comment only to a post.Comment will not be posted.");
		}
		catch(NumberFormatException n)
		{
			System.err.println("No number is given.Process is interrupted.Returning to option Menu.");
		}
		catch(IndexOutOfBoundsException n)
		{
			System.err.println("You haven't given a fitting number.Returning to option menu.");
		}
	}
	void commentToWall(Scanner s) //By calling this function the user can enter text
	{
		System.out.println("Type the number of message you want to comment.");
		String choice=s.nextLine();
		try
		{
			int selection=Integer.parseInt(choice);
			Message selectedMessage=this.getWall().getMessageList().get(selection);
			if (selectedMessage.getFatherMessage()==null)
			{
				System.out.println("Type your reply.");
				String replyText = s.nextLine();
				Message aReply=new Message(this.getName(),replyText,selectedMessage);
				Message.setReply(aReply,selectedMessage);
				this.getWall().getMessageList().add(aReply); //Added to ArrayList of Messages of the user's Wall
				this.getWall().getLikes().add(0); // 0 is added to Likes of the user's Wall at the appropriate position
				System.out.println("Comment posted successfully.");
			}
			else System.err.println("You can't reply to a comment only to a post.Comment will not be posted.");
		}
		catch(NumberFormatException e)
		{
			System.err.println("No number is given.Process is interrupted.Returning to option Menu.");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.err.println("You haven't given a fitting number.Returning to option menu.");
		}
	}
	void postToWall(String text) //The programmer can post directly messages by calling this function.For easy creation of messages.
	{
		String userWhoPosts = this.getName();
		Message newMessage= new Message(userWhoPosts,text);
		this.getWall().getMessageList().add(newMessage); 
		this.getWall().getLikes().add(0); // This list takes an integer representing the number of likes
		//We assume likesToPostsList size equals to message list size
		System.out.println("Message posted successfully");
	}
	void postToWall(Scanner scan) //This function is used in menu so a registered user can post messages to his wall.
	{
		System.out.println("Type the message you want to post.");
		String text = scan.nextLine();
		String userWhoPosts = this.getName();
		Message newMessage= new Message(userWhoPosts,text);
		this.getWall().getMessageList().add(newMessage); 
		this.getWall().getLikes().add(0); // This list takes an integer representing the number of likes
		//We assume likesToPostsList size equals to message list size
		System.out.println("Message posted successfully");
	}
	void postToFriendsWall(Scanner s,User e)
	{
		User a = User.getUserFromString(this.getName());
		boolean isAFriend=Network.friendshipChecker(e, a);
		if(isAFriend)
		{
			System.out.println("Type the text you want to post to your friends wall.");
			String text=s.nextLine();
			Message newMessage = new Message(this.getName(),text); //A new Message is created,name of user is inserted automatically
			e.getWall().getMessageList().add(newMessage); // We add the message we created in the message list of our friend
			e.getWall().getLikes().add(0); // 0 is added in the likes list of our friend
		}
	}
	void friendRequestReply(int position,Scanner s)
	{
		FriendRequest theFriendRequestToBeReplied = this.getFriendRequestList().get(position);
		String sender=theFriendRequestToBeReplied.getSender();
		User senderAsUser =User.getUserFromString(sender);
		User receiverAsUser = theFriendRequestToBeReplied.getReceiver();
		System.out.println("Please type your preference for this Friend Request by typing accepted or rejected.");
		String decision=s.nextLine();
		if(decision.equals("accepted"))
		{
			theFriendRequestToBeReplied.setStatus("accepted");
			Network.setAsFriends(senderAsUser,receiverAsUser);
			System.out.println(receiverAsUser.getName()+" accepted the friend request from "+senderAsUser.getName());
		}
		else if(decision.equals("rejected"))
		{
			theFriendRequestToBeReplied.setStatus("rejected");
			System.out.println(receiverAsUser.getName()+" rejected the friend request from "+senderAsUser.getName());
		}
		else
		{ // This fooking line is always printed
			System.out.println("It appears you haven't typed correctly accepted or rejected");
		}
	}
	void sendFriendRequest(User a)
	{
		User thisUser = User.getUserFromString(this.getName());
		boolean isFriendOrPotentialFriend=Network.friendshipChecker(thisUser,a);
		boolean hasTheSameName=a.getName().equals(thisUser.getName());
		int i=0;
		
		if ((!isFriendOrPotentialFriend) && hasTheSameName)
			System.out.println(a.getName() +" can't send friend request to himself/herself.Nothing happened.");
		
		while( (!isFriendOrPotentialFriend) && (i<a.getFriendRequestList().size()) )
		{
			
			// Condition that checks if this user has sent a friend request to user A
			if(this.getName().equals(a.getFriendRequestList().get(i).getSender()) &&(!hasTheSameName))
			{
				isFriendOrPotentialFriend=true;
				System.out.println("A friend request is pending and sent by "+this.getName()+".It cannot be send again.");
			}
			
			// Condition that checks if this user has received a friend request from user A
			if(this.getName().equals(a.getFriendRequestList().get(i).getReceiver().getName())&&(!hasTheSameName))
			{
				isFriendOrPotentialFriend=true;
				System.out.println(a.getName()+" has already sent to "+ thisUser.getName() +" a friend request.");
			}
			++i;
		}
		
// If boolean is false then we can send a friend request 
		if(!isFriendOrPotentialFriend && (!hasTheSameName))
		{
			String userName = this.getName(); //The name of the user
			FriendRequest newFriendRequest= new FriendRequest(a,userName);
			a.friendRequestList.add(newFriendRequest); //Friend Request is added to a Friend Request List of the receiver
			this.friendRequestList.add(newFriendRequest); //Friend Request is added to Friend Request List of the sender
			System.out.println(this.getName()+" sent friend request to "+ a.getName());
		}
	}
	void deleteFriend(User a)
	{
		String userName=this.getName();
		boolean areFriends = Network.friendshipChecker(a, getUserFromString(userName));
		if(areFriends)
		{
			int i=0;
			boolean isStillFriend=true; //This variable limits the delay caused by the use of 2 loops
			while (isStillFriend && i<a.getFriendsList().size()) //Loop to delete this user from the friends list of user a 
			{
				if(a.getFriendsList().get(i).getName().equals(this.getName()))
				{
					isStillFriend=false;
					a.getFriendsList().remove(i); //This user is removed from the friend list of user A
					System.out.println("This user removed successfully from the Friend List of User A.");
				}
				++i;
			}
			i=0;
			while(!isStillFriend && i<this.getFriendsList().size())
			{
				if(this.getFriendsList().get(i).getName().equals(a.getName()))
				{
					isStillFriend=true;
					this.getFriendsList().remove(i);
					System.out.println("User A removed successfully from the Friend List of this user.");
				}
				++i;
			}
		}
		else System.out.println("These two users are not friends.");
	}
	void likePost(Wall theWall,Scanner s)
	{
		int i=0;
		boolean messageNotFound=true;
		System.out.print("\nLike a Post by typing the appropriate number: ");
		String input=s.nextLine();
		try
		{
			Integer choice=Integer.parseInt(input);
			Message a = theWall.getMessageList().get(choice);
			while(messageNotFound&&i<likedPosts.size())
			{
				if(a==this.likedPosts.get(i))
				{
					messageNotFound=false;
					System.out.println("You have already liked this post.");
				}
				++i;
			}
			if(messageNotFound)
			{
				this.likedPosts.add(a); //The liked Message A is added to the posts that the user liked for future reference
				a.setLikes(); //Increase by 1 the likes of this post
				System.out.println(this.getName()+" liked post "+a.toString());
			}
		}
		catch(NumberFormatException e)
		{
			System.err.println("You typed something that is not number.");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.err.println("You typed a non compatible number.");
		}
	}
	void showFriendRequests()
	{
		String thisName=this.getName();
		User e=getUserFromString(thisName);
		int i;
		for(i=0;i<e.getFriendRequestList().size();i++)
		{
			boolean thisUserIsTheReceiver= e.getName().equals(e.getFriendRequestList().get(i).getReceiver().getName());
			boolean FriendRequestIsPending = e.getFriendRequestList().get(i).getStatus().equals("pending");
			
			if(thisUserIsTheReceiver && FriendRequestIsPending )
			System.out.println(i+"."+e.getFriendRequestList().get(i).getSender());
		}
	}
	void showNoFriends()
	{
		int i;
		System.out.println("Not Friends are the people above:");
		User thisUser = getUserFromString(this.getName());
		for(i=0;i<Network.registeredUsers.size();++i)
		{
			User userToBeChecked = Network.registeredUsers.get(i);
			boolean friendshipExists = Network.friendshipChecker(thisUser,userToBeChecked);
			if(!friendshipExists)  System.out.println(i + "."+userToBeChecked.getName());
		}
			
	}
	void showFriends()
	{
		Iterator<User> i = friendsList.iterator();
		System.out.println("The friends of "+this.getName()+" are:\n");
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		System.out.print("\n");
	}
	//Getter and Setter Methods for name,email,friendsLists,myWall,friendsRequestsLists and toString

	static User getUserFromString(String name)
	{
		User thisUser=null;
		int i=0;
		while(i<Network.registeredUsers.size())
		{
			if (Network.registeredUsers.get(i).getName().equals(name))
			{
				thisUser=Network.registeredUsers.get(i); //The user that his name is matched is returned
			}
			++i;
		}
		return thisUser;
	}
	String getName()
	{
		return this.name;
	}
	String getEmail()
	{
		return this.email;
	}	
	Wall getWall()
	{
		return this.myWall;
	}
	ArrayList<FriendRequest> getFriendRequestList()
	{
		return this.friendRequestList;
	}
	ArrayList<User> getFriendsList()
	{
		return this.friendsList;
	}
	public String toString()
	{
		//This method overrides the toString method in java.lang and returns an exit with name and mail of the user
		return this.getName()+" with email "+this.getEmail();
	}
	ArrayList<Message> getLikedPosts()
	{
		return this.likedPosts;
	}
}
