/*alert("Websocket js");*/
//direct websocket connection
//var exampleSocket = new WebSocket("ws://localhost:8080/test");

var sockWeb = new SockJS('/ws/test');
var stompClient = Stomp.over(sockWeb);

//connect();

function connect() {
	alert("Connecting through sock and stomp");
	stompClient.connect({}, onConnected);
}
function onConnected() {
    // Subscribe to the Public Channel
	var roomId = 3;
    stompClient.subscribe('/topic/greetings/'+roomId, onMessageReceived);
    
   // alert(roomId);
    // Tell your username to the server
  
}
/*function sendMessage(event) {
	alert(sendMessage);
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}*/
//my working code
/*stompClient.send("/app/chat.addUser/"+roomId,
        {},
        JSON.stringify({sender: "sam",type: 'JOIN',recipient: "raju"})
    )
*///

function letschat(){
	var messageInput = document.querySelector('#message');
	var messageContent = messageInput.value.trim();
	if(messageContent && stompClient) {
        var chatMessage = {
            sender: "sam",
            content: messageInput.value,
            type: 'CHAT'
        };
        var roomId = 3;
        stompClient.send("/app/chat.addUser/"+roomId, {}, JSON.stringify(chatMessage));
        //messageInput.value = '';
    }
	 
}

function onMessageReceived(payload) {
	//alert();
    var message = JSON.parse(payload.body);
    showGreeting(message);
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message.content + "</td></tr>");
}