<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 리스트</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/resources/bootstrap/dist/css/adminlte.min.css">



</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
	
		<%@ include file="/WEB-INF/views/common/nav.jsp" %>
		<%@ include file="/WEB-INF/views/common/left.jsp" %>
		
		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>회원리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<a href="/member/regist"><button type="button" class="btn btn-primary">회원등록</button></a>
							<div id="keyword" class="card-tools" style="width: 550px;">
									<form id="frm" action="/member/list">
								<div class="input-group row">
										<!-- sort num -->
										<select class="form-control col-md-3" name="pageSize" id="pageSize">
											<option>정렬개수</option>
											<option value="3">3개씩</option>
											<option value="5">5개씩</option>
											<option value="7">7개씩</option>
										</select>
										<!-- search bar -->
										<select class="form-control col-md-3" name="searchType" id="searchType">
											<option value="none">검색구분</option>
											<option value="userid">아이디</option>
											<option value="usernm">이름</option>
											<option value="alias">별명</option>
										</select> <input class="form-control" type="text" name="searchKeyword" id="searchKeyword" placeholder="검색어를 입력하세요." > <span class="input-group-append">
											<button class="btn btn-primary" type="submit" id="searchBtn" data-card-widget="search">
												<i class="fa fa-fw fa-search"></i>
											</button>
										</span>
									
									<!-- end : search bar -->
								</div>
								</form>
							</div>
						</div>
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>아이디</th>
												<th>이름</th>
												<th>별명</th>
												<th>도로주소</th>
												<th>등록날짜</th>
												<!-- yyyy-MM-dd  -->
											</tr>
											
											<c:forEach items="${userList }" var="user">
												<tr class="userTr" data-userid="${user.userid }">
													<td>${user.userid }</td>
													<td>${user.usernm }</td>
													<td>${user.alias }</td>
													<td>${user.addr1 } ${user.addr2 }</td>
													<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy-MM-dd"/></td>
												</tr>
											</c:forEach>
											
											
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						<!-- card-body -->
						<div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-double-left"></i></a></li>
									<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
									<li class="page-item active"><a class="page-link" href="#">1</a></li>
									<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
									<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>

						</div>
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/bottom.jsp" %>
	
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script src="/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

	<!-- Bootstrap 4 -->
	<script src="/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="/resources/bootstrap/dist/js/adminlte.min.js"></script>
	
	<script>
		$(function(){
			$("#pageSize").val(${param.pageSize});
			$("#searchType").val("${param.searchType}");
			$("#searchKeyword").val("${param.searchKeyword}");
			$("#searchKeyword").focus();
			
			$(".userTr").on("click", function(){
				document.location="/member/member?userid=" + $(this).data("userid");
			});
		});
	</script>	

</body>
</html>







