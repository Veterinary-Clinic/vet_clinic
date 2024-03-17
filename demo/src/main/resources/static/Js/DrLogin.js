
function validateForm() {
    var form = document.getElementById("loginForm");
    var name = form.elements["name"].value;
    var password = form.elements["password"].value;
    var alertElement = document.getElementById("alert");
  
    if (name.trim() === "" || password.trim() === "") {
      alertElement.innerHTML = "Name and password must be filled out";
      alertElement.style.display = "block";
      return false;
    }
    
    if (!isValidName(name)) {
      alertElement.innerHTML = "Please enter a valid name";
      alertElement.style.display = "block";
      return false;
    }
    
    if (!isValidPassword(password)) {
      alertElement.innerHTML = "Please enter a valid password";
      alertElement.style.display = "block";
      return false;
    }
  
    alertElement.style.display = "none";
    form.submit(); 
  }
  
  function isValidName(name) {
    var nameRegex = /^[a-zA-Z\s]+$/;
    return nameRegex.test(name);
  }
  
  function isValidPassword(password) {
    return password.length == 8;
  }
  