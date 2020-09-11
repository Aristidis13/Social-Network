import java.util.Date;

public class Message
{
	private Date timestamp;
	private String text;
	private Message nextReply;
	private int likes;
	private User creator;
	private Message fatherMessage;
	
	//Constructor
	Message(String creatorName,String words) //The constructor will receive creator Name and the text and will create a post in a wall
	{
		this.timestamp = new Date();
		this.text=words;
		this.likes=0;
		this.creator=User.getUserFromString(creatorName);
	}
	// Constructor For Message that have a message as reply
	Message(String creatorName,String words,Message e) //This constructor will post message that reply to message e
	{
		this.timestamp = new Date();
		this.text=words;
		this.likes=0;
		this.creator=User.getUserFromString(creatorName);
		this.fatherMessage=e;
	}
	
	//Getter and Setter Methods
	public String toString()
	{
		return this.text+ " from "+this.creator+" that created at "+this.getDateAsString();
	}
	int getLikes()
	{
		return this.likes;
	}
	String getDateAsString()
	{
		return this.timestamp.toString();
	}
	Message getReply()
	{
		return this.nextReply;
	}
	String getText()
	{
		return this.text;
	}
	void setLikes() //A like is added to the Message
	{
		this.likes=this.likes + 1;
	}
	Message getFatherMessage()
	{
		return  this.fatherMessage;
	}
	static void setReply(Message e,Message a)
	{
		a.nextReply=e;
	}
}
