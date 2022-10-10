<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.util.PropsValues" %>


<%
	DecoratorConfiguration decoratorConfiguration =
   (DecoratorConfiguration)request.getAttribute(DecoratorConfiguration.class.getName());
%>
<%

	String decorate = null;

	if(decoratorConfiguration.applicationDecorators() == ""){
		decorate = PropsValues.DEFAULT_PORTLET_DECORATOR_ID;
	}else{
	decorate = decoratorConfiguration.applicationDecorators();
	}

%>


<div class="row">
   <div class="col-md-12">
      <br />
         <aui:select label="select-properties-application-decorators" type="text" name="applicationDecorators" required="<%= true %>" value="<%= decorate %>">
        	 	<aui:option label="Barebone" value="barebone" />
         		<aui:option label="Borderless" value="borderless" />
         		<aui:option label="Decorate" value="decorate" />
         </aui:select>
   </div>
</div>