<meta charset="utf-8"/>
<title>Lefoto Admin Panel</title>

<link rel="stylesheet" href="/src/plugins/backtemplate/css/layout.css" type="text/css" media="screen" />
<link rel="stylesheet" href="/src/css/bootstrap.css" type="text/css" media="screen" />
<link href='http://fonts.googleapis.com/css?family=Cuprum&amp;subset=latin' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="/src/plugins/conformdialog/css/styles.css" />
<link rel="stylesheet" type="text/css" href="/src/plugins/conformdialog/jquery.confirm/jquery.confirm.css" />


<!--[if lt IE 9]>
<link rel="stylesheet" href="/lefoto/src/plugins/backtemplate/css/ie.css" type="text/css" media="screen" />
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="/src/plugins/backtemplate/js/hideshow.js" type="text/javascript"></script>
<script src="/src/plugins/backtemplate/js/jquery.tablesorter.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/src/plugins/backtemplate/js/jquery.equalHeight.js"></script>
<script type="text/javascript" src="/src/plugins/bootstrap/js/bootstrap.js"></script>
<script src="/src/plugins/conformdialog/jquery.confirm/jquery.confirm.js" type="text/javascript" ></script>


<script type="text/javascript">
    $(document).ready(function() 
    { 
        $(".tablesorter").tablesorter(); 
    } 
);
    $(document).ready(function() {

        //When page loads...
        $(".tab_content").hide(); //Hide all content
        $("ul.tabs li:first").addClass("active").show(); //Activate first tab
        $(".tab_content:first").show(); //Show first tab content

        //On Click Event
        $("ul.tabs li").click(function() {

            $("ul.tabs li").removeClass("active"); //Remove any "active" class
            $(this).addClass("active"); //Add "active" class to selected tab
            $(".tab_content").hide(); //Hide all tab content

            var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
            $(activeTab).fadeIn(); //Fade in the active ID content
            return false;
        });

    });
</script>
<script type="text/javascript">
    $(function(){
        $('.column').equalHeight();
    });
</script>