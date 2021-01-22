console.log('in helper js file');

window.onload = function() {
	getUser();
}



function getUser() {
	console.log('in getUser function');
	fetch(
			'http://localhost:8080/ProjectTwo/earthlings/api/getAllUsers')
			.then(function(daResponse) {
				const convertedResponse = daResponse.json();
				console.log(convertedResponse);
				return convertedResponse;
			}).then(function(daSecondResponse) {
				console.log(daSecondResponse);
				myUserInformation=daSecondResponse;
				ourDOMManipulation(daSecondResponse);
			});
}


function ourDOMManipulation(ourJSON) {
		
		console.log('in dom manipulation function');
		
		let idText = document.createTextNode(ourJSON[0].userId);
		let usernameText = document.createTextNode(ourJSON[0].username);
		let pwText = document.createTextNode(ourJSON[0].password);	
		let nameText = document.createTextNode(ourJSON[0].fullName);
		let emailText = document.createTextNode(ourJSON[0].email);
		let timeText = document.createTextNode(new Date(ourJSON[0].created).toLocaleString());
		let ulikesText = document.createTextNode(totalLikes(ourJSON[0].likes));
		document.getElementById('user-img').src = ourJSON[0].imageUrl;
		
		
		document.getElementById('user-id').appendChild(idText);
		document.getElementById('username').appendChild(usernameText);
		document.getElementById('password').appendChild(pwText);
		document.getElementById('fullname').appendChild(nameText);
		document.getElementById('email').appendChild(emailText);
		document.getElementById('created').appendChild(timeText);
		document.getElementById('user-likes').appendChild(ulikesText);
		
		
		let pidText = document.createTextNode(ourJSON[0].posts[0].postId);
		let titleText = document.createTextNode(ourJSON[0].posts[0].title);
		let bodyText = document.createTextNode(ourJSON[0].posts[0].body);	
		let linkText = document.createTextNode(ourJSON[0].posts[0].link);
		let ptimeText = document.createTextNode(new Date(ourJSON[0].posts[0].createdAt).toLocaleString());
		let plikesText = document.createTextNode(totalLikes(ourJSON[0].posts[0].likes));
			
		document.getElementById('post-id').appendChild(pidText);
		document.getElementById('title').appendChild(titleText);
		document.getElementById('body').appendChild(bodyText);
		document.getElementById('link').appendChild(linkText);
		document.getElementById('time').appendChild(ptimeText);
		document.getElementById('post-likes').appendChild(plikesText);
}


function totalLikes(likes){
	console.log('in total likes function');
	console.log(likes);
	let total = 0;
	for(let i = 0; i < likes.length; i++){
		total++;
	}
	console.log(total);
	return total;
}


function handleSubmit(event) {
  event.preventDefault();

  const data = new FormData(event.target);

  const value = Object.fromEntries(data.entries());
  
  
  
  value.created = Date.now();
  console.log({ value });
  return value;
}

//const form = document.querySelector('form');
//form.addEventListener('submit', handleSubmit);



var formData = JSON.stringify($("#myForm").serializeArray());

$.ajax({
  type: "POST",
  url: "/ProjectTwo/earthlings/api/createUser",
  data: formData,
  success: function(){},
  dataType: "json",
  contentType : "application/json"
});