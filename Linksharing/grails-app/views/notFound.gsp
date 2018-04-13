<%--
  Created by IntelliJ IDEA.
  User: prachi
  Date: 12/4/18
  Time: 8:03 PM
--%>
<!doctype html>
<html>
<head>
    <title>Page Not Found</title>

    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
</head>
<body>
<ul class="errors">
    <li>Error: Page Not Found (404)</li>
    <li>Path: ${request.forwardURI}</li>
</ul>
</body>
</html>
