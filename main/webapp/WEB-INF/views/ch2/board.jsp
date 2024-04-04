<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<body>

<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-red w3-card w3-left-align w3-large">
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="/quiz/quiz04/index04.jsp" class="w3-bar-item w3-button w3-padding-large w3-white">index</a>
    <a href="/quiz/quiz04/boardList04.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">board</a>
    <a href="/LoginFilter04/boardList" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">login</a>
    <a href="/LoginFilter04/Logout" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">logout</a>
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="/quiz/quiz04/index04.jsp" class="w3-bar-item w3-button w3-padding-large">index</a>
    <a href="/quiz/quiz04/boardList04.jsp" class="w3-bar-item w3-button w3-padding-large">board</a>
    <a href="/LoginFilter04/boardList" class="w3-bar-item w3-button w3-padding-large">login</a>
    <a href="/LoginFilter04/Logout" class="w3-bar-item w3-button w3-padding-large">logout</a>
  </div>
</div>

<!-- Header -->
<header class="w3-container w3-red w3-center" style="padding:128px 16px">
  <h1 class="w3-margin w3-jumbo">My Board List!!</h1>
</header>

This is BOARD <br>
This is BOARD <br>
This is BOARD <br>
This is BOARD <br>
This is BOARD

<script type="text/javascript">
// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
  var x = document.getElementById("navDemo");
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else { 
    x.className = x.className.replace(" w3-show", "");
  }
}
</script>

</body>
</html>