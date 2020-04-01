<c:if test="${totalPages > 1}">
    <div class="row">
        <ul class="pagination center-align">
            <c:choose>
                <c:when test="${currentPage == 0}">
                    <li class="disabled"><a><i class="material-icons">chevron_left</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="waves-effect"><a href="?page=${currentPage-1}"><i class="material-icons">chevron_left</i></a></li>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="0" end="${totalPages-1}">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <li class="active"><a href="?page=${i}">${i+1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="waves-effect"><a href="?page=${i}">${i+1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${currentPage == totalPages-1}">
                    <li class="disabled"><a ><i class="material-icons">chevron_right</i></a></li>
                </c:when>
                <c:otherwise>
                    <li class="waves-effect"><a href="?page=${currentPage+1}"><i class="material-icons">chevron_right</i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>