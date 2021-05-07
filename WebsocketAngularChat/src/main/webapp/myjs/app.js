var app = angular.module("webapp", [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "myview/login.html"
	}).when("/chat", {
		templateUrl : "myview/chat.html"
	});
});

app.controller("web_controller", function($scope, $http, $location,
		loginService) {

	$scope.username;
	$scope.password;
	$scope.loggedinUser;

	$scope.login = function() {
		var data = new FormData();
		data.append('username', $scope.username);
		data.append('password', $scope.password);
		$http.post('login', data, {
			withCredentials : false,
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(function(resp) {
			// console.log(resp.data);
			// alert(resp.data.userName);
			$scope.loggedinUser = resp.data;
			console.log($scope.loggedinUser);
			// alert($scope.loggedinUser.userId);
			loginService.setLoginUser($scope.loggedinUser);
			$location.path("/chat");
		});
	}
});

app.service('loginService', function() {
	var loggedinUser;

	this.getLoginUser = function() {
		// alert("getter called");
		return loggedinUser;
	}

	this.setLoginUser = function(user) {
		// alert("setter called " + user.userId);
		return loggedinUser = user;
	}
});

app
		.controller(
				"chat_controller",
				function($scope, $http, $location, loginService) {
					$('#chatid').hide();
					$scope.roomId;
					$scope.newChatMsg = '';
					$scope.connectedUser = '';
					$scope.connectedUserName = '';
					$scope.stompClient = '';
					$scope.loggedInUser = loginService.getLoginUser();

					$scope.logout = function() {
						var data = new FormData();
						data.append('loggedinuserid',
								$scope.loggedInUser.userId);
						$http.post('logout', data, {
							withCredentials : false,
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).then(function(resp) {
							console.log(resp.data);
							$location.path("/");
						});
					}
					$scope.letschat = function() {

						if ($scope.newChatMsg && stompClient) {
							var chatMessage = {
								sender : $scope.loggedInUser.userName,
								content : $scope.newChatMsg,
								type : 'CHAT',
								recipient : $scope.connectedUserName
							};
							stompClient.send("/app/chat.addUser/"
									+ $scope.roomId, {}, JSON
									.stringify(chatMessage));
							// messageInput.value = '';
						}
					}

					$scope.closeChat = function() {
						console.log('close');
						$scope.stompClient.disconnect();
						$('#chatid').hide();
						$('#userlistdiv').show();

					}
					function openChat() {
						$('#chatid').show();
						$('#userlistdiv').hide();
					}

					$scope.fetchMembers = function() {

						$http
								.get(
										'chat',
										{
											params : {
												'userId' : loginService
														.getLoginUser().userId
											}
										})
								.then(
										function(response) {
											console.log(response);
											if (response.data != '') {
												$scope.userList = response.data;

											} else
												$scope.searchMessage = 'Please refine your search or Choose the categories to browse';
										});
					}
					$scope.chatMessages = [];

					$scope.formatChat = function(icon, username, text, origDt) {
						var chat = {};
						chat.icon = icon;
						chat.username = username;
						chat.text = text;
						chat.origDt = origDt;
						return chat;
					}

					$scope.fetchUser = function(connectedUserName) {
						// alert("in fetch "+connectedUserName);
						var data = new FormData();
						data.append('username', connectedUserName);
						$http.post('fetchuser', data, {
							withCredentials : false,
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).then(function(resp) {
							// console.log(resp.data);
							// alert(resp.data.userName);
							$scope.connectedUser = resp.data;
						});
					}

					$scope.chatUser = function(user) {
						// clear chat messages
						$scope.chatMessages = [];

						// show chat window
						// hide userlist
						$scope.connectedUserName = user.userName;
						// alert(user.userName);

						// lets see if stomp works
						var sockWeb = new SockJS('/ws/test');
						var stompClient = Stomp.over(sockWeb);

						$scope.stompClient = stompClient;
						stompClient
								.connect(
										{},
										function() {
											// alert(user.userId);
											if (user.userId > loginService
													.getLoginUser().userId) {
												$scope.roomId = loginService
														.getLoginUser().userId
														+ '' + user.userId;
											} else if (user.userId < loginService
													.getLoginUser().userId) {
												$scope.roomId = user.userId
														+ ''
														+ loginService
																.getLoginUser().userId;
											}

											stompClient
													.subscribe(
															"/topic/greetings/"
																	+ $scope.roomId,
															function(payload) {

																var message = JSON
																		.parse(payload.body);

																if (message.content != "") {
																	/*
																	 * var chat =
																	 * $scope
																	 * .formatChat(
																	 * "http://placehold.it/16x16",
																	 * message.sender,
																	 * message.content,
																	 * new
																	 * Date());
																	 * 
																	 * $scope.chatMessages
																	 * .push(chat);
																	 */
																	// alert("pushed");
																	/*
																	 * $scope.newChatMsg =
																	 * "";
																	 */
																	// console.log(message);
																	// $scope.fetchUser($scope.connectedUserName);
																	var chat = $scope
																			.formatChat(
																					"http://placehold.it/16x16",
																					message.sender,
																					message.content,
																					new Date());

																	$scope.chatMessages
																			.push(chat);
																	$scope
																			.$apply();
																	openChat();
																	// alert(message.content);
																	/*
																	 * $(
																	 * "#content")
																	 * .append( "<tr><td>" +
																	 * message.content + "</td></tr>");
																	 */
																	$scope.newChatMsg = '';
																	// alert($scope.newChatMsg);

																} else {

																}
															});
										});
						$('#chatid').show();
						$('#userlistdiv').hide();
					}
				});

/*
 * app.factory("DataModel", function() { var Service = {};
 * 
 * return Service; });
 * 
 * app.filter('reverse', function() { return function(items) { return
 * items.slice().reverse(); }; });
 */

