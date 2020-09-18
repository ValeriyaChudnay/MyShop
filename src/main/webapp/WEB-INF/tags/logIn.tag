<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Category Options" trimDirectiveWhitespaces="true"  pageEncoding="UTF-8" %>
          <c:choose>
             <c:when test="${empty currentUser}">
      <form class="form-signin" weight=100  height=300 method="post" action="logIn">
        <img class="mb-4" src="https://cdn.onlinewebfonts.com/svg/img_524742.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="psw1" id="inputPassword" class="form-control" placeholder="Password" required="">
        <div class="checkbox mb-3">
            <h5>
                       ${alert}
             </h5>
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">© 2017-2019</p>
      </form>
      </c:when>
      <c:when test="${currentUser!=null}">
      <form action="logOut" method="post" class="form-signin" weight=100  height=300>
      <img src="${currentUser.imgPath}" alt="" class="bigAvatar">
      <h1>Hi!"${currentUser.firstName}".</h1>
      <button class="btn btn-lg btn-primary btn-block" link="" type="submit">Log out</button>
      </form>
      </c:when>
      </c:choose>