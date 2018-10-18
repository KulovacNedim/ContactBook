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
		</div>
		<div class="row margin-bottom-5px padding-bottom-5px border-bottom-orange">
			<div class="col-sm-9">
				<h2>Manage contacts</h2>
			</div>
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary btn-block btn-top-margine">Save changes</button>
				<button type="button" class="btn btn-primary btn-block btn-top-margine">Cancel</button>
			</div>
		</div>
		
			<div class="row" style="border-bottom: 1px solid red;">
				<div class="col-sm-2">
					<img src="images/users/defaultAvatar.jpg" class="img-circle img-responsive" alt="" >
				</div>
				<div class="col-sm-5">
					<form class="form-horizontal" action="/action_page.php">
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="email">First Name:</label>
					      <div class="col-sm-10">
					        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="email">Last Name:</label>
					      <div class="col-sm-10">
					        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="pwd">Nickname:</label>
					      <div class="col-sm-10">          
					        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="pwd">Role:</label>
					      <div class="col-sm-10">          
					        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="radio-inline">
						      <input onclick="setActivity('active')" type="radio" name="activity" id="activity" value="active" ${active.equals("active") ? "checked" : ""}>ACTIVE
						      <span class="checkmark"></span>
						    </label>
						    <label class="radio-inline">
						      <input onclick="setActivity('inactive')" type="radio" name="activity" id="activity" value="inactive" ${active.equals("inactive") ? "checked" : ""}>INACTIVE
						      <span class="checkmark"></span>
						    </label>
					    </div>
					    
					  </form>
				</div>
				<div class="col-sm-5">
					<form class="form-horizontal" action="/action_page.php">
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="email">Birthdate:</label>
					      <div class="col-sm-10">
					        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="pwd">Company:</label>
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
					</div>
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="pwd">Password:</label>
					      <div class="col-sm-10">          
					        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-2" for="pwd">Password confirm:</label>
					      <div class="col-sm-10">          
					        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
					      </div>
					    </div>
					    <div class="form-group">        
					      <div class="col-sm-offset-2 col-sm-10">
					        <button type="submit" class="btn btn-default">Submit</button>
					      </div>
					    </div>
					  </form>
				</div>
			</div>
			<div class="row" style="border-bottom: 1px solid red;">
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
										<tr>
									        <td>form input</td>
									        <td>form input</td>
									        <td>form input</td>
								      	</tr>
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
	</body>
</html>
