<%@ taglib prefix="s" uri="/struts-tags" %>
<ul class="pagination pagination-sm m-0">
    <nav>
        <ul class="pagination">
            <s:if test="(page - 1) > 0">
                <li class="page-item">
                    <a class="page-link" href="${changePageAction}?${searchURL}page=${page - 1}" rel="prev"
                        aria-label="&laquo; Previous">&lsaquo;</a>
                </li>
            </s:if>
            <s:else>
                <li class="page-item disabled" aria-disabled="true" aria-label="&laquo; Previous">
                    <span class="page-link" aria-hidden="true">&lsaquo;</span>
                </li>
            </s:else>

            <s:if test="totalPage <= 13">
                <s:iterator begin="1" end="totalPage" var="pageLoop">
                    <li class='page-item <s:if test="page == #pageLoop">active</s:if>' aria-current="page">
                        <s:if test="page == #pageLoop">
                            <span class="page-link">
                                <s:property value="pageLoop" />
                            </span>
                        </s:if>
                        <s:else>
                            <a class="page-link" href="${changePageAction}?${searchURL}page=${pageLoop}">
                                <s:property value="pageLoop" />
                            </a>
                        </s:else>
                    </li>
                </s:iterator>
            </s:if>

            <s:else>
                <s:if test="(page <= 7) && (totalPage > 13)">
                    <s:iterator begin="1" end="10" var="pageLoop">
                        <li class='page-item <s:if test="page == #pageLoop">active</s:if>' aria-current="pageLoop">
                            <s:if test="page == #pageLoop">
                                <span class="page-link">
                                    <s:property value="pageLoop" />
                                </span>
                            </s:if>
                            <s:else>
                                <a class="page-link" href="${changePageAction}?${searchURL}page=${pageLoop}">
                                    <s:property value="pageLoop" />
                                </a>
                            </s:else>
                        </li>
                    </s:iterator>
                    <li class="page-item disabled" aria-disabled="true"><span class="page-link">...</span></li>
                    <a class="page-link" href="${changePageAction}?${searchURL}page=${totalPage - 1}">
                        ${totalPage - 1}
                    </a>
                    <a class="page-link" href="${changePageAction}?${searchURL}page=${totalPage}">
                        ${totalPage}
                    </a>
                </s:if>
                <s:elseif test="(page > 7) && (page < (totalPage - 6))">
                    <a class="page-link" href="${changePageAction}?${searchURL}page=1">1</a>
                    <a class="page-link" href="${changePageAction}?${searchURL}page=2">2</a>
                    <li class="page-item disabled" aria-disabled="true"><span class="page-link">...</span></li>
                    <s:iterator begin="page - 2" end="page + 2" var="pageLoop">
                        <li class='page-item <s:if test="page == #pageLoop">active</s:if>' aria-current="page">
                            <s:if test="page == #pageLoop">
                                <span class="page-link">
                                    <s:property value="pageLoop" />
                                </span>
                            </s:if>
                            <s:else>
                                <a class="page-link" href="${changePageAction}?${searchURL}page=${pageLoop}">
                                    <s:property value="pageLoop" />
                                </a>
                            </s:else>
                        </li>
                    </s:iterator>
                    <li class="page-item disabled" aria-disabled="true"><span class="page-link">...</span></li>
                    <a class="page-link" href="${changePageAction}?${searchURL}page=${totalPage - 1}">
                        ${totalPage - 1}
                    </a>
                    <a class="page-link" href="${changePageAction}?${searchURL}page=${totalPage}">
                        ${totalPage}
                    </a>
                </s:elseif>
                <s:elseif test="(page > (totalPage - 9)) && ((totalPage > 13))">
                    <a class="page-link" href="${changePageAction}?${searchURL}page=1">1</a>
                    <a class="page-link" href="${changePageAction}?${searchURL}page=2">2</a>
                    <li class="page-item disabled" aria-disabled="true"><span class="page-link">...</span></li>
                    <s:iterator begin="totalPage - 9" end="totalPage" var="pageLoop">
                        <li class='page-item <s:if test="page == #pageLoop">active</s:if>' aria-current="page">
                            <s:if test="page == #pageLoop">
                                <span class="page-link">
                                    <s:property value="pageLoop" />
                                </span>
                            </s:if>
                            <s:else>
                                <a class="page-link" href="${changePageAction}?${searchURL}page=${pageLoop}">
                                    <s:property value="pageLoop" />
                                </a>
                            </s:else>
                        </li>
                    </s:iterator>
                </s:elseif>
            </s:else>
            <%-- <s:property value="totalPage" /> --%>
            <s:if test="(page + 1) <= totalPage">
                <li class="page-item">
                    <a class="page-link" href="${changePageAction}?${searchURL}page=${page + 1}" rel="next"
                        aria-label="Next &raquo;">&rsaquo;</a>
                </li>
            </s:if>
            <s:else>
                <li class="page-item disabled" aria-disabled="true" aria-label="Next &raquo;">
                    <span class="page-link" aria-hidden="true">&rsaquo;</span>
                </li>
            </s:else>

        </ul>
    </nav>
</ul>