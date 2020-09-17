<html lang="en-us" >
<head>
<title>${title} | Admin Site</title>
<link rel="stylesheet" type="text/css" href="/Static/admin/css/base.css">
<link rel="stylesheet" type="text/css" href="/Static/admin/css/forms.css">

<script type="text/javascript" src="/Static/admin/js/vendor/jquery/jquery.js"></script>
<script type="text/javascript" src="/Static/admin/js/jquery.init.js"></script>
<script type="text/javascript" src="/Static/admin/js/core.js"></script>
<script type="text/javascript" src="/Static/admin/js/admin/RelatedObjectLookups.js"></script>
<script type="text/javascript" src="/Static/admin/js/actions.js"></script>
<script type="text/javascript" src="/Static/admin/js/urlify.js"></script>
<script type="text/javascript" src="/Static/admin/js/prepopulate.js"></script>
<script type="text/javascript" src="/Static/admin/js/vendor/xregexp/xregexp.js"></script>


    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/static/admin/css/responsive.css">
    

<meta name="robots" content="NONE,NOARCHIVE">
</head>


<body class=" app-home model-category change-form"
  data-admin-utc-offset="0">

<!-- Container -->
<div id="container">

    
    <!-- Header -->
    <div id="header">
        <div id="branding">
        
<h1 id="site-name"><a href="/admin/">Ecommerce administration</a></h1>

        </div>
        
        
        <div id="user-tools">
            
                Welcome,
                <strong><% out.print(request.getUserPrincipal().getName()); %></strong>.
            
            
                
                    <a href="/">View site</a> /
                
                
                    
                    
                
                
                <a href="/admin/password_change/">Change password</a> /
                
                <a href="/admin/logout/">Log out</a>
            
        </div>
        
        
        
    </div>