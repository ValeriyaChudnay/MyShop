<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation example" xmlns:c="http://www.w3.org/1999/XSL/Transform">
  <ul class="pagination justify-content">
    <c:choose>
      <c:when test="${((userRequestParameter.pageNumber-1)*userRequestParameter.amountOnPage-userRequestParameter.amountOnPage)<0}">
        <li class="page-item disabled">
          <a class="page-link " id="page">Previous</a>
        </li>
      </c:when>
      <c:when test="${((userRequestParameter.pageNumber-1)*userRequestParameter.amountOnPage-userRequestParameter.amountOnPage)>=0}">
        <li class="page-item">
          <a at="${userRequestParameter.pageNumber-1}" class="page-link">Previous</a>
        </li>
        <li class="page-item"><a at="${userRequestParameter.pageNumber-1}" class="page-link">${userRequestParameter.pageNumber-1}</a>
        </li>
      </c:when>
    </c:choose>
    <li class="page-item active"><a at="${userRequestParameter.pageNumber}" class="page-link" id="pageCurrent">${userRequestParameter.pageNumber} </a>
    </li>
    <c:choose>
      <c:when test="${(userRequestParameter.pageNumber)*userRequestParameter.amountOnPage<userRequestParameter.count}">
        <li class="page-item"><a at="${userRequestParameter.pageNumber+1}" class="page-link">${userRequestParameter.pageNumber+1}</a>
        </li>
        <li class="page-item">
          <a at="${userRequestParameter.pageNumber+1}" class="page-link">Next</a>
        </li>
      </c:when>
      <c:when test="${(userRequestParameter.pageNumber)*userRequestParameter.amountOnPage>userRequestParameter.count}">
        <li class="page-item disabled">
          <a at="{userRequestParameter.pageNumber+1}" class="page-link ">Next</a>
        </li>
      </c:when>
    </c:choose>


  </ul>
</nav>
