# Microservice-Authentication
 JASS Authentication with Microservice

-Create JDBCPool and JNDI from Payara Admin console
-Do everything in Client App
-Create Project config.java file in Source Packages:
	@DeclareRoles({"Admin","Student","College"})

	@CustomFormAuthenticationMechanismDefinition(
        	loginToContinue = @LoginToContinue(loginPage = "/login")
	)

	@DatabaseIdentityStoreDefinition(
        	dataSourceLookup = "hogwarts/jndi",
	        callerQuery = "select password from tbluser where userName=?",
        	groupsQuery = "select groupName from tbluser u,tblgroup g,tblusergroup ug where u.userID = ug.userID and g.groupID = ug.groupID and userName=?",
	        hashAlgorithm = Pbkdf2PasswordHash.class,
	        priority = 30)
	@ApplicationScoped
	@Named
-Create CDI Bean in Source Packages:
	    @Inject SecurityContext sc;
	    private String userName;
	    private String password;
 -create GetterSetter for userName & password
 -Now create login() method in bean file:
      try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            Credential credential = new UsernamePasswordCredential(getUserName(), new Password(getPassword()));
            AuthenticationStatus status = sc.authenticate(request, response, withParams().credential(credential));
            request.getSession().setAttribute("role", "");
            
            if(status.equals(SUCCESS)) 
            {
                if(sc.isCallerInRole("Admin"))
                {
                    System.out.println("Admin");
                    request.getSession().setAttribute("role", "Admin");
                    request.getSession().setAttribute("username", getUserName());
                    RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet");
                    rd.forward(request, response);
                }
                else if(sc.isCallerInRole("Student"))
                {
                    System.out.println("Student");
                    request.getSession().setAttribute("role", "Student");
                    request.getSession().setAttribute("username", getUserName());
                    RequestDispatcher rd = request.getRequestDispatcher("NewServlet");
                    rd.forward(request, response);
                }
                else
                {
                    response.sendRedirect("login.xhtml");
                }
            }
            else if(status.equals(SEND_FAILURE))
            {
                response.sendRedirect("login.xhtml");
            }
        } catch(Exception e) {
            System.out.println(e);
        }
-Now create login file and create Login Form
