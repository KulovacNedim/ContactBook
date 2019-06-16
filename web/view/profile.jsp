<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<%@ include file="headContent.jsp"%>
	</head>
	<body>
		<div class="container-fluid">
			<%@ include file="navigationBar.jsp"%>
			<div class="row">
				<div class="col-sm-9">
					<h2>Manage contacts</h2>
				</div>
				<div class="col-sm-3">
					<button type="button" class="btn btn-primary btn-block btn-top-margine btn-sm">Add contact</button>
				</div>
			</div>
			<div class="row rowMain">
				<div class="col-sm-4 col-md-3 contact-list" style="">
					<div class="row">
						<form action="showContact" method="post" autocomplete="off">
							<div class="input-group">
						    <input type="text" class="form-control input-sm" placeholder="Search" name="search" value="${searchPlaceholder}" >
						    <input type="text" name="activity" id="activity" value="${active}" hidden>
						    <div class="input-group-btn">
						      <button class="btn btn-default input-sm" type="submit">
						        <i class="glyphicon glyphicon-search"></i>
						      </button>
						    </div>
						  </div>
						</form>
					</div>
					<div class="row container1">
						<form class="form-inline text-center">
						    <label class="radio-inline">
						      <input onclick="setActivity('active')" type="radio" name="activity" id="activity" value="active" ${active.equals("active") ? "checked" : ""}>ACTIVE
						      <span class="checkmark"></span>
						    </label>
						    <label class="radio-inline">
						      <input onclick="setActivity('inactive')" type="radio" name="activity" id="activity" value="inactive" ${active.equals("inactive") ? "checked" : ""}>INACTIVE
						      <span class="checkmark"></span>
						    </label>
						</form>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="dropdown ">
							    <button class="btn btn-block dropdown-toggle btn-success  btn-sm" type="button" id="menu1" data-toggle="dropdown">${ContactGroup.contactGroup == null ? "Filter Contact group" : contactGroupName} <span class="caret"></span>
							    </button>
							    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
								    <c:forEach items="${contactGroupList}" var="contactGroup" >
								    <li role="presentation">
								    	<a role="menuitem" tabindex="-1" href="showContact?groupId=${contactGroup.id}&groupName=${contactGroup.contactGroup}&activity=${active}" class="getLink">${contactGroup.contactGroup}</a></li>
								    </c:forEach>
							    </ul>
							</div>
						</div>
						<div class="col-sm-6">
							<a href="showContact">
								<button type="button" class="btn btn-primary btn-block btn-top-margine btn-sm">Reset all parameters</button>
							</a>
						</div>
					</div>
					<c:forEach items="${myContacts}" var="contact" >
					<div class="row contact-card">
						<a href="showContact?contactId=${contact.id}&search=${searchPlaceholder}&groupId=${groupId}&groupName=${contactGroupName}&activity=${active}" class="getLink">
							<div class="hidden-xs col-xs-3 col-sm-4 col-md-5 col-lg-4" >
								<img src="getImage?name=${contact.imagePath}" class="img-circle img-responsive" alt="" >
							</div>
							<div class="col-xs-7 col-sm-6 col-md-5 col-lg-6">
								<p class="blue-heading ${contact.id == contactToShow.id ? 'selected' : ''}">${contact.lastName} ${contact.firstName}</p>
								<p class="hidden-sm hidden-xs hidden-md fine-print ${contact.id == contactToShow.id ? 'selected' : ''}">${contact.nickName}</p>
							</div>
						</a>
						<a href="#">
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					          <span class="glyphicon glyphicon-trash float-right"></span>
					      	</div>
						</a>
					</div>
	                </c:forEach>
				</div>
				<div class="col-sm-8 col-md-9">
					<div class="row border-bottom-orange" style="margin:10px 0 10px 0;">
						<p class="selected">CONTACT DETAILS</p>
					</div>
					<div class="row">
						<div class="hidden-xs hidden-sm col-xs-0 col-sm-1 col-md-5 col-lg-4">
							<img src="getImage?name=${contactToShow.imagePath}" class="img-circle img-responsive" alt="" >
						</div>
						<div class="col-sm-9 col-md-5 col-lg-6">
							<form class="form-horizontal" action="/action_page.php">
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="full_name">Full name:</label>
							      <div class="col-sm-10">
							      	<p class="form-control-static">${contactToShow.lastName} ${contactToShow.firstName}</p>
							      </div>
							    </div>
							    <div class="form-group">
								    <label class="control-label col-sm-2" for="pwd">Nickname:</label>
								    <div class="col-sm-10">          
								       <p class="form-control-static">${contactToShow.nickName}</p>
								    </div>
							    </div>
							    <div class="form-group">
								    <label class="control-label col-sm-2" for="pwd">Birthdate:</label>
								     <div class="col-sm-10">          
								       <p class="form-control-static">${contactToShow.birthdate}</p>
							     	</div>
								</div>
							    <div class="form-group">
							      	<label class="control-label col-sm-2" for="pwd">Company:</label>
							      	<div class="col-sm-10">          
							        	<p class="form-control-static">${contactToShow.company.companyName}</p>
							      	</div>
								</div>
							    <div class="form-group">
							      	<label class="control-label col-sm-2" for="pwd">Role:</label>
							      	<div class="col-sm-10">          
							        	<p class="form-control-static">${contactToShow.role.roleName}</p>
							      	</div>
							    </div>		    
							</form>
						</div>
						<div class=" col-sm-2 col-md-2 col-lg-2">
							<button type="button" class="btn btn-primary btn-block  btn-sm">Edit</button>
							<div class="dropdown ">
							    <button class="btn btn-block dropdown-toggle btn-primary  btn-sm" type="button" id="menu2" data-toggle="dropdown">${refContactGroup.contactGroup == null ? "Add to contact group" : refContactGroup.contactGroup} <span class="caret"></span>
							    </button>
							    <ul class="dropdown-menu" role="menu2" aria-labelledby="menu2">
								    <c:forEach items="${contactGroupList}" var="contactGroup" >
								    <li role="presentation">
								    	<a role="menuitem" tabindex="-1" href="showContact?contactId=${contactToShow.id}&search=${searchPlaceholder}&groupId=${groupId}&groupName=${contactGroupName}&newGroupId=${contactGroup.id}&activity=${active}" class="getLink">${contactGroup.contactGroup}</a></li>
								    </c:forEach>
							    </ul>
							</div>
						</div>
					</div>
					<div class="row tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#emails">e-mail list</a></li>
						    <li><a data-toggle="tab" href="#phones">Phone list</a></li>
						    <li><a data-toggle="tab" href="#addresses">Home address list</a></li>
						    <li><a data-toggle="tab" href="#notes">My notes</a></li>
						</ul>
						<br>
						<div class="tab-content  contact-tabs">
						    <div id="emails" class="tab-pane fade in active">
							    <table class="table table-hover">
							     	<thead>
									    <tr>
									        <th>email</th>
									        <th>description</th>
									        <th>type</th>
									    </tr>
							    	</thead>

									<tbody>
								    <c:forEach items="${contactToShow.emailList}" var="email">
								    	<tr>
									        <td>${email.email}</td>
									        <td>${email.emailDescription}</td>
									        <td>${user.type.typeName}</td>
								      	</tr>
									</c:forEach>
									</tbody>
								</table>
								<div class="${contactToShow.emailList.size()==0 ? 'show' : 'hidden'}"">
								  	<p>Nothing to show</p>
								</div>
						    </div>
						    <div id="phones" class="tab-pane fade">
							     <table class="table table-hover">
							     	<thead>
									    <tr>
									        <th>phone number</th>
									        <th>description</th>
									        <th>type</th>
									    </tr>
							    	</thead>

									<tbody>
								    <c:forEach items="${contactToShow.phoneList}" var="phone">
								    	<tr>
									        <td>${phone.phoneNumber}</td>
									        <td>${phone.phoneDescription}</td>
									        <td>${phone.type.typeName}</td>
								      	</tr>
									</c:forEach>
									</tbody>
								</table>
								<div class="${contactToShow.phoneList.size()==0 ? 'show' : 'hidden'}"">
								  	<p>Nothing to show</p>
								</div>
						    </div>
						    <div id="addresses" class="tab-pane fade">
							     <table class="table table-hover">
							     	<thead>
									    <tr>
									        <th>description</th>
									        <th>state</th>
									        <th>city</th>
									        <th>street</th>
									        <th>zip</th>
									        <th>type</th>
									    </tr>
							    	</thead>

									<tbody>
								    <c:forEach items="${contactToShow.addressList}" var="address">
								    	<tr>
									        <td>${address.addressDescription}</td>
									        <td>${address.state}</td>
									        <td>${address.city}</td>
									        <td>${address.street}</td>
									        <td>${address.zipCode}</td>
									        <td>${address.type.typeName}</td>
								      	</tr>
									</c:forEach>
									</tbody>
								</table>
								<div class="${contactToShow.addressList.size()==0 ? 'show' : 'hidden'}"">
								  	<p>Nothing to show</p>
								</div>
						    </div>
						    <div id="notes" class="tab-pane fade">
							    <table class="table table-hover">
							     	<thead>
									    <tr>
									    	<th>note</th>
									        <th>description</th>
									        <th>date</th>
									        <th>archived</th>
									    </tr>
							    	</thead>

									<tbody>
								    <c:forEach items="${contactToShow.noteList}" var="note">
								    	<tr>
								    		<td>${note.note}</td>
									        <td>${note.noteDescription}</td>
									        <td>${note.date}</td>
									        <td>${note.archived == true ? 'Yes' : 'No'}</td>
								      	</tr>
									</c:forEach>
									</tbody>
								</table>
								<div class="${contactToShow.noteList.size()==0 ? 'show' : 'hidden'}"">
								  	<p>Nothing to show</p>
								</div>
						    </div>
						  </div>
					</div>
				</div>
			</div>
		</div>
		<script>
			function setActivity(activity) {
			    document.getElementById("activity").value = activity;

			    var x = document.getElementsByClassName("getLink");
			    var i;
			    for (i = 0; i < x.length; i++) {
			    	var j = x[i].href;
			        x[i].setAttribute("href", j + "activity=" + activity);

			        var pos = j.lastIndexOf("=");
			        x[i].setAttribute("href", j.substring(0, pos+1) + activity);
			    }
			}
		</script>
	</body>
</html>
