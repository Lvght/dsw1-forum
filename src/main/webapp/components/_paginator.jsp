<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="br">
    <head>
        <meta charset="utf-8">
        <style>
            .pagination {
                width: fit-content;
                margin: 15px auto;
            }

            .page-link {
                color: #033142fa;
            }

            .page-item.active .page-link {
                z-index: 3;
                color: #fff;
                background-color: #033142fa;
                border-color: #033142fa;
            }
        </style>
    </head>

    <body>
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Anterior</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item active"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">6</a></li>
            <li class="page-item"><a class="page-link" href="#">7</a></li>
            <li class="page-item"><a class="page-link" href="#">8</a></li>
            <li class="page-item"><a class="page-link" href="#">9</a></li>
            <li class="page-item"><a class="page-link" href="#">10</a></li>
            <li class="page-item"><a class="page-link" href="#">Próximo</a></li>
        </ul>
    </body>
</html>