<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <body>
        <h1> LISTADO DE PRODUCTOS </h1>
        <table border='1'>
            <tr>
                <th>CÓDIGO</th>
                <th>NOMBRE</th>
                <th>PRECIO</th>
                <th>FABRICANTE</th>
            </tr>
            ${filas}
            <a href='<%=request.getContextPath()%>/index.html'>Volver</a>
        </table>
    </body>

    </html>