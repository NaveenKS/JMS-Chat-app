# JMS-Chat-app
It's a command-line chat application built using ActiveMQ JMS provider. 

##ActiveMQ
It is a Java Messaging Servcie implementation built by Apache. JMS is a messaging framework used for communication between components of an application. It provides asynchronous - receiver need not request for the message, it will be delivered as it arrives, relaible - message delivered only once, loosely coupled - Both sender and receiver need not know about each other and need not be up at the same time.

##How does it work?
The applciation acts as both a sender and a receiver. As a receiver, it registers a listener with the JMS provider to the queue 'senderId'. As a sender it will send a message to the queue 'receiverId'. Hence, as it is listening to it's queue(senderId), messaging addressed to it will be received.

##How to build the code?
Export the code as runnable jar and select the main class as 'com.opensource.JMSClient.JMSClientMain'

##How to run the application?
<code>java -jar JMSClient.jar</code>

##Following are the commands and their usage :
1. <code>mp -help</code> : To know about all the commands.
2. <code>mp -login</code> : Login with username and password.
3. <code>mp -chat <receiverId></code> : Chat with <receiverId>.
4. <code>mp -logout</code> : Logout from the applcation.
5. <code>mp -exit</code> : Exit from the application.

###Contributors
1. Application could be exposed via rest service and UI could be built atop of this application for good user experience.
