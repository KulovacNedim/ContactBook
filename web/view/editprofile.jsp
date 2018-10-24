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
			<div class="row margin-bottom-5px padding-bottom-5px border-bottom-orange">
				<div class="col-sm-9">
					<h2>Manage contacts</h2>
				</div>
				<div class="col-sm-3">
					<button type="button" class="btn btn-primary btn-block btn-top-margine">Save changes</button>
					<button type="button" class="btn btn-primary btn-block btn-top-margine">Cancel</button>
				</div>
			</div>
			<div class="row margin-bottom-5px padding-bottom-5px">
				<div class="col-sm-3 col-md-4 col-lg-3 form-group">
					<img src="/images/users/${contactToEdit.imagePath}" class="img-circle img-responsive" alt="" >
					 <form method="POST" action="editProfile" enctype="multipart/form-data" id="form">
						<div class="form-group upload-btn-wrapper">
						    <button class="btn btn-block btn-primary btn-sm">Upload image</button>
						    <input type="file" name="file" id="file" />
						</div>
					    <div class="form-group hiddenElement">
					        <input type="text" value="changeImage" name="flag"/>
					    </div>
					</form>
				</div>
				<div class="col-sm-5 col-md-4 col-lg-4">
					<form class="form-horizontal" action="/action_page.php">
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="first_name">First Name:</label>
					      <div class="col-sm-9">
					        <input type="text" class="form-control" id="first_name" placeholder="${contactToEdit.firstName == null ? 'Enter first name' : ''}" value="${contactToEdit.firstName == null ? '' : contactToEdit.firstName}" name="first_name">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="last_name">Last Name:</label>
					      <div class="col-sm-9">
					        <input type="text" class="form-control" id="last_name" placeholder="${contactToEdit.lastName == null ? 'Enter last name' : ''}" value="${contactToEdit.lastName == null ? '' : contactToEdit.lastName}" name="last_name">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="nick_name">Nickname:</label>
					      <div class="col-sm-9">          
					        <input type="text" class="form-control" id="nick_name" placeholder="${contactToEdit.nickName == null ? 'Enter nick name' : ''}" value="${contactToEdit.nickName == null ? '' : contactToEdit.nickName}" name="nick_name">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="company">Company:</label>
					      <div class="col-sm-9">
								<div class="dropdown ">
								    <button class="btn btn-block dropdown-toggle btn-success btn-sm" type="button" id="role" data-toggle="dropdown">${contactToEdit.company == null ? "Chose company" : contactToEdit.company.companyName} <span class="caret"></span>
								    </button>
								    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
									    <c:forEach items="${companies}" var="company" >
									    <li role="presentation">
									    	<a role="menuitem" tabindex="-1" value="${company.id}" href="#">${company.companyName}</a></li>
									    </c:forEach>
								    </ul>
								</div>
					    	</div>
						</div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="birthdate">Birthdate:</label>
					      <div class="col-sm-9">
					        <input type="date" class="form-control" id="birthdate" value="${contactToEdit.lastName == null ? '' : contactToEdit.birthdate}" name="birthdate">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-3" for="role">Role:</label>
					      	<div class="col-sm-9">
								<div class="dropdown ">
								    <button class="btn btn-block dropdown-toggle btn-success  btn-sm" type="button" id="role" data-toggle="dropdown">${contactToEdit.role == null ? "Chose role" : contactToEdit.role.roleName} <span class="caret"></span>
								    </button>
								    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
									    <c:forEach items="${roles}" var="role" >
									    <li role="presentation">
									    	<a role="menuitem" tabindex="-1" value="${role.id}" href="#">${role.roleName}</a></li>
									    </c:forEach>
								    </ul>
								</div>
					    	</div>
						</div>
					    <div class="form-group container1">
					      <label class="control-label col-sm-3" for="active">Active:</label>
					      <div class="col-sm-9">
					        <label class="radio-inline text-center">
						      <input onclick="setActivity('active')" type="radio" name="activity" id="activity" value="active" ${contactToEdit.active == true ? "checked" : ""}>ACTIVE
						      <span class="checkmark"></span>
						    </label>
						    <label class="radio-inline text-center">
						      <input onclick="setActivity('inactive')" type="radio" name="activity" id="activity" value="inactive" ${contactToEdit.active == false ? "checked" : ""}>INACTIVE
						      <span class="checkmark"></span>
						    </label>
					      </div>
					    </div>
					    <div class="form-group">        
					      <div class="col-sm-offset-3 col-sm-9">
					        <button type="submit" class="btn btn-block btn-primary btn-sm">Submit</button>
					      </div>
					    </div>
					</form>
				</div>
				<div class="col-sm-4 col-md-4 col-lg-5">
					<form class="form-horizontal" action="/action_page.php">
					    <div class="form-group">
					      <label class="control-label col-sm-4" for="pwd">Password:</label>
					      <div class="col-sm-8">          
					        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
					      </div>
					    </div>
					    <div class="form-group">
					      <label class="control-label col-sm-4" for="pwd_new">Password confirm:</label>
					      <div class="col-sm-8">          
					        <input type="password" class="form-control" id="pwd_new" placeholder="Confirm password" name="pwd_new">
					      </div>
					    </div>
					    <div class="form-group">        
					      <div class="col-sm-offset-4 col-sm-8">
					        <button type="submit" class="btn btn-block btn-primary btn-sm">Change password</button>
					      </div>
					    </div>
					  </form>
				</div>
			</div>
			<div class="row">
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
							    <c:forEach items="${contactToEdit.emailList}" var="email">
							    	<tr>
								        <td>${email.email}</td>
								        <td>${email.emailDescription}</td>
								        <td>${user.type.typeName}</td>
							      	</tr>
								</c:forEach>
									<tr class="hiddenFields" id="addEmail">
										<form class="form-horizontal" action="/action_page.php">
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="email" placeholder="Enter email" value="" name="email" autocomplete="off">
											      </div>
											    </div>
											    <br>
											    <div class="form-group">        
											      <div class="col-sm-12">
											        <button type="submit" class="btn btn-primary btn-block btn-sm">Submit</button>
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="description" placeholder="Enter description" value="" name="description">
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
														<div class="dropdown ">
														    <button class="btn btn-block dropdown-toggle btn-success btn-sm" type="button" id="type" data-toggle="dropdown">Chose type <span class="caret"></span>
														    </button>
														    <ul class="dropdown-menu" role="menu" aria-labelledby="type">
															    <c:forEach items="${types}" var="type" >
															    <li role="presentation">
															    	<a role="menuitem" tabindex="-1" value="${type.id}" href="#">${type.typeName}</a></li>
															    </c:forEach>
														    </ul>
														</div>
											    	</div>
												</div>
									        </td>
										</form>
							      	</tr>
								</tbody>
							</table>
							<div class="col-sm-4 ${contactToEdit.emailList.size()==0 || contactToEdit.emailList==null  ? 'show' : 'hidden'}">
							  	<p class="fine-print">Nothing to show</p>
							  	<button class="btn btn-success btn-sm btn-block " onclick="addItem('addEmail')">Add email</button>
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
									<tr class="hiddenFields" id="addPhone">
										<form class="form-horizontal" action="/action_page.php">
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="phone" placeholder="Enter phone number" value="" name="phone" autocomplete="off">
											      </div>
											    </div>
											    <br>
											    <div class="form-group">        
											      <div class="col-sm-12">
											        <button type="submit" class="btn btn-primary btn-block btn-sm">Submit</button>
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="description" placeholder="Enter description" value="" name="description">
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
														<div class="dropdown ">
														    <button class="btn btn-block dropdown-toggle btn-success btn-sm" type="button" id="type" data-toggle="dropdown">Chose type <span class="caret"></span>
														    </button>
														    <ul class="dropdown-menu" role="menu" aria-labelledby="type">
															    <c:forEach items="${types}" var="type" >
															    <li role="presentation">
															    	<a role="menuitem" tabindex="-1" value="${type.id}" href="#">${type.typeName}</a></li>
															    </c:forEach>
														    </ul>
														</div>
											    	</div>
												</div>
									        </td>
										</form>
							      	</tr>
								</tbody>
							</table>
							<div class="col-sm-4 ${contactToEdit.phoneList.size()==0 || contactToEdit.phoneList==null  ? 'show' : 'hidden'}">
							  	<p class="fine-print">Nothing to show</p>
							  	<button class="btn btn-success btn-sm btn-block " onclick="addItem('addPhone')">Add phone</button>
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
									<tr class="hiddenFields" id="addAddress">
										<form class="form-horizontal" action="/action_page.php">
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="phone" placeholder="Enter address description" value="" name="description" autocomplete="off">
											      </div>
											    </div>
											    <br>
											    <div class="form-group">        
											      <div class="col-sm-12">
											        <button type="submit" class="btn btn-primary btn-block btn-sm">Submit</button>
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="state" placeholder="Enter state" value="" name="state">
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="city" placeholder="Enter city" value="" name="city">
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="street" placeholder="Enter street" value="" name="street">
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
											        <input type="text" class="form-control" id="zipCode" placeholder="Enter ZIP code" value="" name="zipCode">
											      </div>
											    </div>
									        </td>
									        <td>
									        	<div class="form-group">
											      <div class="col-sm-12">
														<div class="dropdown ">
														    <button class="btn btn-block dropdown-toggle btn-success btn-sm" type="button" id="type" data-toggle="dropdown">Chose type <span class="caret"></span>
														    </button>
														    <ul class="dropdown-menu" role="menu" aria-labelledby="type">
															    <c:forEach items="${types}" var="type" >
															    <li role="presentation">
															    	<a role="menuitem" tabindex="-1" value="${type.id}" href="#">${type.typeName}</a></li>
															    </c:forEach>
														    </ul>
														</div>
											    	</div>
												</div>
									        </td>
										</form>
							      	</tr>
								</tbody>
							</table>
							<div class="col-sm-4 ${contactToEdit.addressList.size()==0 || contactToEdit.addressList==null  ? 'show' : 'hidden'}">
							  	<p class="fine-print">Nothing to show</p>
							  	<button class="btn btn-success btn-sm btn-block " onclick="addItem('addAddress')">Add address</button>
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
									<tr class="hiddenFields" id="addNote">
											<form class="form-horizontal" action="/action_page.php">
										        <td>
										        	<div class="form-group">
												      <div class="col-sm-12">
												        <input type="text" class="form-control" id="phone" placeholder="Enter note" value="" name="note" autocomplete="off">
												      </div>
												    </div>
												    <br>
												    <div class="form-group">        
												      <div class="col-sm-12">
												        <button type="submit" class="btn btn-primary btn-block btn-sm">Submit</button>
												      </div>
												    </div>
										        </td>
										        <td>
										        	<div class="form-group">
												      <div class="col-sm-12">
												        <input type="text" class="form-control" id="description" placeholder="Enter description" value="" name="description">
												      </div>
												    </div>
										        </td>
										        <td>
										        	<div class="form-group">
												      <div class="col-sm-12">
												        <input type="date" class="form-control" id="date" placeholder="Enter date" value="" name="date">
												      </div>
												    </div>
										        </td>
										        <td>
										        	<div class="form-group">
												      <div class="checkbox">
													      <label><input type="checkbox" value="" name="archived"></label>
													    </div>
												    </div>
										        </td>
											</form>
								      	</tr>
								</tbody>
							</table>
							<div class="col-sm-4 ${contactToEdit.noteList.size()==0 || contactToEdit.noteList==null  ? 'show' : 'hidden'}">
							  	<p class="fine-print">Nothing to show</p>
							  	<button class="btn btn-success btn-sm btn-block " onclick="addItem('addNote')">Add note</button>
							</div>
					    </div>
					</div>
				</div>
			</div>
		</div>
			<script type="text/javascript">
				function functionHide() {
				    var x = document.getElementsByClassName("hiddenFields");
				    var i;
				    for (i = 0; i < x.length; i++) {
				    	x[i].style.display = "none";
				    }
				}
			    window.onload = functionHide;



			    function addItem(tag) {
				    var x = document.getElementById(tag).style.display = "table-row";
				}



				document.getElementById("file").onchange = function() {
				    document.getElementById("form").submit();
				}



				
			</script>
	</body>
</html>
