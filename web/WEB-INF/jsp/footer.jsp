<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script type="text/javascript" src="../../js/jquery.leanModal.min.js"></script>

        <script>
            $(document).ready(function(){
                // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
                $('.modal').modal();
                $('select').material_select();

                var test = "<%=request.getAttribute("toastContent")%>";
                var $toastContent = $('<span>${toastContent}</span>');
                //if ($toastContent != '<span></span>' && $toastContent != null ){
                if (test != "null"){
                    Materialize.toast($toastContent, 5000);
                }
            });
        </script>
    </body>
</html>
