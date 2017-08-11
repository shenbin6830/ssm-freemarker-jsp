<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript">
function toAjaxPost(){
    
    $.ajax({  
        url: 'user',  
        type: 'POST', 
        data : {
            'userId' : $("#userId").val()
         },  
        success: function(result) {  
          $("#show").text(result.userName);
        },  
        error:function(result){  
            alert("error");
        }  
    });//end  
}

</script>
<body>
<input id ="userId" />
<button onclick="toAjaxPost()">search</button>
<a id="show">---</a>
</body>
</html>