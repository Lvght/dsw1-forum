<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">
        <style>
            .r-navbar label{
              font-weight: bold;
            }

            .r-navbar .topicsLabel a{
              font-weight: 500;
              font-size: 12px;
            }

            .r-navbar .topics a:hover{
              text-decoration: underline;
            }

            .r-navbar .filterSelect{
              margin-top: 5px;
              margin-bottom: 10px!important;
              padding: 10px;
              background-color: #142931;
              color: white;
              border-radius: 15px;
              width: 50%;
            }

            .r-navbar .topics{
              margin-top: 50px;
            }

            .r-navbar .forumTopics{
              margin-top: 5px;
            }

            .r-navbar .forumTopics a:hover{
              text-decoration: none;
            }

            .r-navbar .topicCard{
              width: 100%;
              margin-bottom: 10px;
              color: black;
              padding: 10px;
              border-radius: 10px;
              background-color: rgba(20, 41, 49, 0.15);
              cursor: pointer;
            }

            .r-navbar .active{
              background-color: #142931;
              color: white;
            }

            .r-navbar .nav_list > * {
              padding: 0 1.5rem;
            }

            .r-navbar .filter{
              display: flex;
              flex-direction: column;
            }

        </style>
 </head>

<body id="body-pd" class="body-pd-left">
    <div class="r-navbar show-right" id="nav-bar">
        <nav class="nav">
            <div class="scrollNav"> <span class="nav_logo nav_logo-name">Filtros</span>
              <div class="nav_list"> 
                <div class="filter">
                  <label for="filterSelect">
                    Visualizar por
                  </label>
                  <select id="filterSelect" class="filterSelect" name="filterType">
                    <option value=1 selected>
                        Mais recente
                    </option>
                    <option value=2>
                        Mais popular
                    </option>
                  </select>
                </div>
                <div class="topics">
                  <label class="topicsLabel" for="forumTopics">
                    Tópicos deste fórum <a href="${pageContext.request.contextPath}/forum/topicoForm?forum_id=${requestScope.forum.id}">Adicionar</a>
                  </label>
                  <div id="forumTopics" class="forumTopics">
                    <a href="${pageContext.request.contextPath}/forum/especifico?id=${requestScope.forum.id}">
                      <div class="topicCard ${requestScope.topico == 0 ? "active" : ""}">
                        <span>Geral</span>
                      </div>
                    </a>
                    <c:forEach var="topico" items="${requestScope.topicos}">
                      <a href="${pageContext.request.contextPath}/forum/especifico?id=${requestScope.forum.id}&topico=${topico.id}&filtro=${requestScope.filtro}">
                        <div class="topicCard ${requestScope.topico == topico.id ? "active" : ""}">
                          <span>${topico.getNome()}</span>
                        </div>
                      </a>
                    </c:forEach>  
                  </div>
                </div>
              </div>
            </div>  
        </nav>
    </div>
    <!--Container Main start-->
  </body>
</html>