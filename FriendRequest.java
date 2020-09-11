import java.util.Date;

public class FriendRequest
{
	private User receiver;
	private String sender;
//	private boolean directionStoE; //The direction of Friend Request.True if user with name s adds user e and false if the opposite happens
	private Date timestamp;
	private String status;
	//Constructor
	FriendRequest(User e,String s)
	{
//		this.directionStoE=true;
		this.sender=s;
		this.receiver=e;
		timestamp= new Date(); //A new object containing the Date and time 
		status="pending"; //The status of object is initialized as pending
	}
	//Setter and Getter Methods
	String getDateAsString()
	{
		return timestamp.toString();
	}
	String getSender()
	{
		return this.sender;
	}
	User getReceiver()
	{
		return this.receiver;
	}
/*	boolean getDirectionStoE()
	{
		return this.directionStoE;
	}*/
	void setStatus(String s)
	{
		if (s.equals("accepted")||(s.equals("rejected")))
		{
			this.status=s;
		}
		else
		{
			System.err.println("This couldn't happen.");
		}
	}
	String getStatus()
	{
		return this.status;
	}
}
