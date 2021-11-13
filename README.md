# Social-Network

This is a social Network that was created for the Java course in my college.

It is a solid long backend project written in Java

The classes are:
- Menu contains the choices that a user can type to navigate to the social network.
- FriendRequest is a class that the user can add other users as friends.
- HelperClass is a class that creates users and different objects for execution.
- Main_Class contains the running of the program.
- Message that is a class that allows users to exchange messages.
Each message can have one or more replies and the replies, even more replies that are distinguished by 4 blanks at the start of every message like a normal conversation in Facebook.
- Network that contains methods for doing specific things in this Social Network.
- User that contains methods and attributes for the user
- Wall is an object that is created for every user only once and contains a few methods there that help in other classes too.

The code is redundant and can be reduced to less code, but I tried to encapsulate each level of methods to an upper level of methods hiding information that way.
That's the reason I also created getter and setter methods for each attribute in the project.

In the UML you will find everything I created.
For every method I tried to implement as strict backend checking as possible. For example someone that is a friend with another user cannot send a friend request to that user again.
