<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Medida" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <title>Mis Medidas</title>
    
    <%
        LinkedList<Medida> lm = (LinkedList<Medida>)request.getAttribute("listaMedidas");
        int id = (int)session.getAttribute("id_usuario");
    %>
    
</head>
<body>
    <%-- Header global --%>
    <% request.setAttribute("pageTitle", "Mis Medidas"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container">
        <!-- Barra de filtros con botón Crear -->
        <div class="filters-bar">
            <form action="crearMedida" method="post" style="display:inline;" class="create-wrap">
                <input type="hidden" name="id_usuario" value="<%=id%>">
                <input type="submit" value="+ Crear Medida" class="action-btn create-btn">
            </form>
            <button onclick="toggleGrafica()" class="action-btn edit-btn" style="margin-left: 10px;">
                Ver Gráfica
            </button>
        </div>
        
        <div id="tabla-container" class="div-table">
            <table>
                <thead>
                    <tr>
                        <th>Peso (kg)</th>
                        <th>Altura (metros)</th>
                        <th>Fecha</th>
                        <th>Acciones</th>	
                    </tr>
                </thead>
                <tbody>
                    <% for (Medida m : lm) { 
                    %>
                    <tr>
                        <td><%=m.getPeso()%></td>
                        <td><%=m.getAltura()%></td>
                        <td><%=m.getFecha()%></td>  
                        <td>
                            <form action="editarMedida" method="post" style="display:inline;">
                                <input type="hidden" name="id_usuario" value="<%=m.getId_usuario()%>">
                                <input type="hidden" name="fecha" value="<%=m.getFecha()%>">
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                            </form>
                            <form action="EliminarMedida" method="post" style="display:inline;">
                                <input type="hidden" name="id_usuario" value="<%=m.getId_usuario()%>">
                                <input type="hidden" name="fecha" value="<%=m.getFecha()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar esta medida?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    } // Fin de la condición
                    %>
                </tbody>
            </table>
        </div>
        
        <!-- Contenedor de las gráficas (inicialmente oculto) -->
        <div id="grafica-container" style="display: none;">
            <div style="background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(10px); border-radius: 15px; padding: 30px; margin-top: 20px;">
                <canvas id="chartPeso"></canvas>
            </div>
            <div style="background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(10px); border-radius: 15px; padding: 30px; margin-top: 20px;">
                <canvas id="chartAltura"></canvas>
            </div>
        </div>
        
        <div style="text-align: center; margin-top: 1.5rem;">
            <a href="javascript:history.back()" class="action-btn edit-btn" style="display: inline-block; text-decoration: none;">
                Volver
            </a>
        </div>
    </div>
    
    <script>
        let chartPeso = null;
        let chartAltura = null;
        let mostrandoGrafica = false;
        
        function toggleGrafica() {
            const tablaContainer = document.getElementById('tabla-container');
            const graficaContainer = document.getElementById('grafica-container');
            const boton = event.target;
            
            mostrandoGrafica = !mostrandoGrafica;
            
            if (mostrandoGrafica) {
                tablaContainer.style.display = 'none';
                graficaContainer.style.display = 'block';
                boton.textContent = 'Ver Tabla';
                crearGrafica();
            } else {
                tablaContainer.style.display = 'block';
                graficaContainer.style.display = 'none';
                boton.textContent = 'Ver Gráfica';
            }
        }
        
        function crearGrafica() {
            // Datos de las medidas desde JSP
            const medidas = [
                <% for (int i = 0; i < lm.size(); i++) { 
                    Medida m = lm.get(i);
                %>
                { fecha: '<%=m.getFecha()%>', peso: <%=m.getPeso()%>, altura: <%=m.getAltura()%> }<%= i < lm.size() - 1 ? "," : "" %>
                <% } %>
            ];
            
            // Ordenar por fecha
            medidas.sort((a, b) => new Date(a.fecha) - new Date(b.fecha));
            
            const fechas = medidas.map(m => m.fecha);
            const pesos = medidas.map(m => m.peso);
            const alturas = medidas.map(m => m.altura);
            
            // Destruir gráficas anteriores si existen
            if (chartPeso) {
                chartPeso.destroy();
            }
            if (chartAltura) {
                chartAltura.destroy();
            }
            
            // Crear gráfica de peso
            const ctxPeso = document.getElementById('chartPeso').getContext('2d');
            chartPeso = new Chart(ctxPeso, {
                type: 'line',
                data: {
                    labels: fechas,
                    datasets: [{
                        label: 'Peso (kg)',
                        data: pesos,
                        borderColor: '#ff6b35',
                        backgroundColor: 'rgba(255, 107, 53, 0.1)',
                        borderWidth: 3,
                        pointRadius: 6,
                        pointBackgroundColor: '#ff6b35',
                        pointBorderColor: '#fff',
                        pointBorderWidth: 2,
                        pointHoverRadius: 8,
                        tension: 0
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    plugins: {
                        legend: {
                            display: true,
                            labels: {
                                color: '#ffffff',
                                font: {
                                    size: 14
                                }
                            }
                        },
                        title: {
                            display: true,
                            text: 'Evolución del Peso',
                            color: '#ffffff',
                            font: {
                                size: 20,
                                weight: 'bold'
                            }
                        }
                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Fecha',
                                color: '#ffffff',
                                font: {
                                    size: 14
                                }
                            },
                            ticks: {
                                color: '#ffffff'
                            },
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Peso (kg)',
                                color: '#ffffff',
                                font: {
                                    size: 14
                                }
                            },
                            ticks: {
                                color: '#ffffff'
                            },
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            },
                            beginAtZero: false
                        }
                    }
                }
            });
            
            // Crear gráfica de altura
            const ctxAltura = document.getElementById('chartAltura').getContext('2d');
            chartAltura = new Chart(ctxAltura, {
                type: 'line',
                data: {
                    labels: fechas,
                    datasets: [{
                        label: 'Altura (m)',
                        data: alturas,
                        borderColor: '#4ecdc4',
                        backgroundColor: 'rgba(78, 205, 196, 0.1)',
                        borderWidth: 3,
                        pointRadius: 6,
                        pointBackgroundColor: '#4ecdc4',
                        pointBorderColor: '#fff',
                        pointBorderWidth: 2,
                        pointHoverRadius: 8,
                        tension: 0
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    plugins: {
                        legend: {
                            display: true,
                            labels: {
                                color: '#ffffff',
                                font: {
                                    size: 14
                                }
                            }
                        },
                        title: {
                            display: true,
                            text: 'Evolución de la Altura',
                            color: '#ffffff',
                            font: {
                                size: 20,
                                weight: 'bold'
                            }
                        }
                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Fecha',
                                color: '#ffffff',
                                font: {
                                    size: 14
                                }
                            },
                            ticks: {
                                color: '#ffffff'
                            },
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Altura (cm)',
                                color: '#ffffff',
                                font: {
                                    size: 14
                                }
                            },
                            ticks: {
                                color: '#ffffff'
                            },
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            },
                            beginAtZero: false
                        }
                    }
                }
            });
        }
    </script>
</body>
</html>