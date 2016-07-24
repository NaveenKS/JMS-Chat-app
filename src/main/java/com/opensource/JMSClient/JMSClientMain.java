package com.opensource.JMSClient;


public class JMSClientMain{
	
	public static void main(String[] args){
		if(args.length<2){
			System.out.println("Usage : java -jar *.jar <senderId> <receiverId>");
			System.exit(1);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

		    @Override
		    public void run() {
		        //TO DO : Update the database saying that the user went offline
		    	System.out.println("Shutdown");
		    }

		});
		
		String senderId = args[0];
		String receiverId = args[1];
		JMSClient client = new JMSClient();
		client.start(senderId,receiverId);
	}
}
