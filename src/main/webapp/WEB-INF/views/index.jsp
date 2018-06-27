<!DOCTYPE html>
<html>

<head>
    <title>Firstpage</title>

    <%--<link href='style.css' rel='stylesheet' type='text/css'>--%>

    <style type="text/css">

        .style1 {
            color: mediumblue;
            font-size: 24px;
            font-family: "Baskerville Old Face";
            text-align-all: center;
            display: block;
        }
        .style2 {
            color: green;
            font-size: 24px;
            font-family: "Coronetscript";
            text-align-all: center;
            display: block;
        }
    </style>

</head>



<div class="style1"  align = center>

    <h1>Sign Up </h1>
</div>



<div class = "style2" align = "center">
    <form action="/index" id="signup" method="post">
        <table>
            <tr>
                <td> Name: </td><td>    <label> <input type="username"  type="text" form="signup" required></label>        </td>
            </tr>


            <tr>
                <td> Password: </td><td>    <label> <input type="password" type="text" form="signup" required></label> </td>
            </tr>


        </table>
    </form>
</div>

<div align="center">
    <form>
        <button formmethod="post">Submit</button>
    </form>
</div>


</html>