
function validateForm() {
    var form = document.getElementById("signupForm");
    var name = form.elements["name"].value;
    var email = form.elements["email"].value;
    var password = form.elements["password"].value;
    var phone = form.elements["phonenumber"].value;
    var gender = form.elements["gender"].value;
  
    if (!name || !email || !password || !phone || !gender) {
      alert("Please fill out all fields.");
      return;
    }
  
    if (!isValidEmail(email)) {
      alert("Please enter a valid email address.");
      return;
    }
  
    if (!isValidPhoneNumber(phone)) {
      alert("Please enter a valid phone number.");
      return;
    }
  
    form.submit();
  }
  
  function isValidEmail(email) {
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
  
  function isValidPhoneNumber(phone) {
    var phoneRegex = /^[0-9]{10}$/;
    return phoneRegex.test(phone);
  }
  
  function isValidPassword(password) {
    return password.length == 8;
  }
  