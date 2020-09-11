public class HelperClass
{
	static void userAdderToNetwork()
	{
//--------------------------Creates 5 users for testing some of the methods that have been created-------------------------------
		
		Network.addUser("aris","aris13pat@gmail.com");
		Network.addUser("elen","elen13pat@gmail.com");
		Network.addUser("george","george13pat@gmail.com");
		Network.addUser("maria","maria13pat@gmail.com");
		Network.addUser("alex","alex13pat@gmail.com");
		
//-------Creation of reference objects to the arrayList of Network.registeredUsers that we can use and understand------------
		
		User aris = User.getUserFromString("aris");
		User elen = User.getUserFromString("elen");
		User george = User.getUserFromString("george");
		User maria = User.getUserFromString("maria");
		User alex = User.getUserFromString("alex");
		
//---------------------------------------------Creation of Friend Requests--------------------------------------------------- 
		
		
		aris.sendFriendRequest(alex); 
		aris.sendFriendRequest(elen);  
		aris.sendFriendRequest(george); 
		maria.sendFriendRequest(aris);
		elen.sendFriendRequest(maria); //Check for exception handling
		maria.sendFriendRequest(elen);
		elen.sendFriendRequest(elen);		
		maria.sendFriendRequest(george);
		System.out.println("\n");
//--------------------------------------------Friends creation--------------------------------------------------------------
		Network.setAsFriends(aris, elen); // An acceptance after a sent friend request
		Network.setAsFriends(aris, aris); // Trying to add as friend the same person.Exception
		Network.setAsFriends(alex, george); //Trying to add friends without friend request.Exception
		
//----------------------------------Creation of posts that the user will post to his wall------------------------------------
		aris.postToWall("Hello to all my future friends from Aris.");
		elen.postToWall("Hello to all my future friends from Elen.");
		george.postToWall("Hello to all my future friends from George.");
		maria.postToWall("Hello to all my future friends from Maria.");
	}
}
