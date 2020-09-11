import java.util.*;

public class Wall
{
	//Attributes of wall
	private ArrayList<Message> messageList;
	private ArrayList<Integer> likesToPost;
	
	// Constructor
	Wall()
	{
		messageList = new ArrayList<Message>();
		likesToPost = new ArrayList<Integer>();
	}
	
	// Method
	static void showTableOfMessages(User e)
	{
		System.out.println("Please select one of the above Messages to comment.");
		int i;
		for(i=0;i<e.getWall().getMessageList().size();++i)
		{
			Message appropriateMessage = e.getWall().getMessageList().get(i);
			boolean messageIsNotAReply=appropriateMessage.getFatherMessage()==null;
			if(messageIsNotAReply)
			{
				System.out.println(i+"."+appropriateMessage.getText());
			}
		}
	}
	
	//Setter and Getter Methods for creating posts
	ArrayList<Message> getMessageList()
	{
		return this.messageList;
	}
	Message getAppropriateMessage(int i)
	{
		return this.messageList.get(i);
	}
	ArrayList<Integer> getLikes()
	{
		return likesToPost;
	}
	Integer getAppropriateMessageLikes(int i)
	{
		return likesToPost.get(i);
	}
	public String toString() //Returns the message and all replies to this message
	{
		String unifiedMessage="";
		int i;
		for(i=0;i<this.getMessageList().size();++i) //This for is responsible for typing of all messages in the wall
		{
			boolean messageIsNotReply=this.getMessageList().get(i).getFatherMessage()==null;
			boolean doesNotHaveReply=this.getMessageList().get(i).getReply()==null;
			boolean messageHasAtLeastOneReply = this.getMessageList().get(i).getReply()!=null;
			Message thisMessage=this.getMessageList().get(i);
			if(messageIsNotReply && doesNotHaveReply)
				unifiedMessage=unifiedMessage + thisMessage.getText()+"   Likes:"+thisMessage.getLikes()+ System.lineSeparator();
			else if(messageIsNotReply && messageHasAtLeastOneReply)
			{
				unifiedMessage= unifiedMessage + thisMessage.getText() +"   Likes:"+thisMessage.getLikes() +System.lineSeparator();
				int j;
				for(j=0;j<this.getMessageList().size();++j)
				{
					Message possibleReply = this.getMessageList().get(j);
					if(possibleReply.getFatherMessage()==thisMessage) //If from the remaining message one or more is a reply to thisMessage
					{
						String replyText = possibleReply.getText();
						unifiedMessage = unifiedMessage +"   "+replyText +"   Likes:"+possibleReply.getLikes() +System.lineSeparator();
					}
				}
			}
		}
		return unifiedMessage;
	}
}
