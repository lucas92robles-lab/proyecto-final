    /* ==========================================================================
    scripts.js - Comportamiento común del front 
    Se carga con "defer" desde header.jsp, login.jsp, registro.jsp y recuperar.jsp.
    Las vistas NO llevan JavaScript en línea: usan atributos data-* que este
    archivo interpreta (ver cada sección).
    ========================================================================== */
    (function () {
        'use strict';

        /** Ejecuta fn cuando el DOM está listo. */
        function listo(fn) {
            if (document.readyState === 'loading') {
                document.addEventListener('DOMContentLoaded', fn);
            } else {
                fn();
            }
        }

        /** Context path de la aplicación (viene en <body data-context-path="...">). */
        function contextPath() {
            return document.body.dataset.contextPath || '';
        }

        /* ----------------------------------------------------------------------
        1. Menú lateral responsivo
        <button data-toggle-sidebar> abre/cierra .sidebar; .sidebar-overlay lo cierra.
        ---------------------------------------------------------------------- */
        function initMenuLateral() {
            var boton = document.querySelector('[data-toggle-sidebar]');
            var sidebar = document.querySelector('.sidebar');
            var overlay = document.querySelector('.sidebar-overlay');
            if (!boton || !sidebar) return;

            var escritorio = window.matchMedia('(min-width: 901px)');

            // Escritorio: visible salvo que tenga .oculta. Celular: oculto salvo que tenga .abierta.
            function estaAbierto() {
                return escritorio.matches
                    ? !sidebar.classList.contains('oculta')
                    : sidebar.classList.contains('abierta');
            }

            function actualizarBoton() {
                var abierto = estaAbierto();
                var texto = abierto ? 'Ocultar menú' : 'Mostrar menú';
                boton.textContent = abierto ? '<' : '>';
                boton.setAttribute('aria-expanded', String(abierto));
                boton.setAttribute('aria-label', texto);
                boton.title = texto;
                if (overlay) overlay.classList.toggle('visible', !escritorio.matches && abierto);
            }

            function abrir(abierto) {
                if (escritorio.matches) {
                    sidebar.classList.toggle('oculta', !abierto);
                } else {
                    sidebar.classList.toggle('abierta', abierto);
                }
                actualizarBoton();
            }

            boton.addEventListener('click', function () { abrir(!estaAbierto()); });
            if (overlay) overlay.addEventListener('click', function () { abrir(false); });
            document.addEventListener('keydown', function (e) {
                if (e.key === 'Escape' && !escritorio.matches) abrir(false);
            });
            // En celular, al elegir una opción se cierra el menú.
            sidebar.addEventListener('click', function (e) {
                if (e.target.closest('a') && !escritorio.matches) abrir(false);
            });
            // Al cruzar el punto de corte (900px) se vuelve al estado por defecto.
            escritorio.addEventListener('change', function () {
                sidebar.classList.remove('abierta', 'oculta');
                actualizarBoton();
            });

            actualizarBoton();
        }

        /* ----------------------------------------------------------------------
        2. Acciones genéricas por atributo
        data-confirm="mensaje"   -> pide confirmación antes de seguir el enlace
        data-autosubmit          -> en un <select>, envía el formulario al cambiar
        data-volver              -> vuelve a la página anterior (href es el respaldo)
        ---------------------------------------------------------------------- */
        function initAccionesGenericas() {
            document.addEventListener('click', function (e) {
                var conConfirmacion = e.target.closest('[data-confirm]');
                if (conConfirmacion && !window.confirm(conConfirmacion.dataset.confirm)) {
                    e.preventDefault();
                    return;
                }
                var volver = e.target.closest('[data-volver]');
                if (volver && window.history.length > 1) {
                    e.preventDefault();
                    window.history.back();
                }
                if (e.target.closest('[data-imprimir]')) {
                    e.preventDefault();
                    window.print();
                }
            });

            document.addEventListener('change', function (e) {
                var select = e.target.closest('select[data-autosubmit]');
                if (select && select.form) select.form.submit();
            });
        }

        /* ----------------------------------------------------------------------
        3. Carruseles de categorías: la rueda del mouse desplaza en horizontal
        (solo con mouse; en pantallas táctiles se usa el gesto nativo).
        ---------------------------------------------------------------------- */
        function initCarruseles() {
            if (!window.matchMedia('(hover: hover) and (pointer: fine)').matches) return;
            document.querySelectorAll('.carrusel-categorias').forEach(function (carrusel) {
                carrusel.addEventListener('wheel', function (e) {
                    var sinDesborde = carrusel.scrollWidth <= carrusel.clientWidth;
                    var gestoHorizontal = Math.abs(e.deltaX) > Math.abs(e.deltaY);
                    if (sinDesborde || gestoHorizontal) return;
                    e.preventDefault();
                    carrusel.scrollLeft += e.deltaY;
                }, { passive: false });
            });
        }

        /* ----------------------------------------------------------------------
        4. Registro de usuario
        <form data-validar-registro> exige contraseña y correo completos.
        ---------------------------------------------------------------------- */
        function initValidacionRegistro() {
            var form = document.querySelector('form[data-validar-registro]');
            if (!form) return;
            form.addEventListener('submit', function (e) {
                var password = form.querySelector('#password');
                var email = form.querySelector('#email');
                if (!password || !email || !password.value || !email.value) {
                    window.alert('Por favor, complete todos los campos obligatorios.');
                    e.preventDefault();
                }
            });
        }

        /* ----------------------------------------------------------------------
        5. Formulario de alta / edición de medios (formulario-medio.jsp)
        ---------------------------------------------------------------------- */

        // 5.a Muestra el bloque de especificaciones según el tipo principal.
        function initTipoMedio() {
            var selector = document.getElementById('tipoSelect');
            if (!selector) return;

            function mostrar(esCargaInicial) {
                document.querySelectorAll('.bloque-tipo').forEach(function (bloque) {
                    bloque.classList.remove('activo');
                });
                if (!esCargaInicial) {
                    document.querySelectorAll('input[name="categoriaId"]').forEach(function (radio) {
                        radio.checked = false;
                    });
                }
                if (selector.value !== '') {
                    var bloque = document.getElementById('bloque' + selector.value);
                    if (bloque) bloque.classList.add('activo');
                }
            }

            selector.addEventListener('change', function () { mostrar(false); });
            if (selector.value !== '') mostrar(true);
        }

        // 5.b Subida de imágenes en segundo plano: <input type="file" data-subir-imagen="1|2|3">
        function mostrarEstadoSubida(contenedor, clase, texto) {
            contenedor.textContent = '';
            var span = document.createElement('span');
            span.className = clase;
            span.textContent = texto;
            contenedor.appendChild(span);
        }

        function subirImagen(input, indice) {
            if (!input.files || input.files.length === 0) return;

            var vista = document.getElementById('preview' + indice);
            var destino = document.getElementById(indice === '1' ? 'imagenUrl' : 'imagenUrl' + indice);
            var datos = new FormData();
            datos.append('file', input.files[0]);

            mostrarEstadoSubida(vista, 'estado-subiendo', 'Subiendo imagen...');

            fetch(contextPath() + '/app/imagenes/upload', { method: 'POST', body: datos })
                .then(function (respuesta) {
                    if (!respuesta.ok) throw new Error('Error al subir la imagen');
                    return respuesta.json();
                })
                .then(function (data) {
                    if (!data.url) throw new Error(data.error || 'Respuesta inválida');
                    destino.value = data.url;
                    mostrarEstadoSubida(vista, 'estado-ok', '✔ ' + data.url);
                })
                .catch(function (error) {
                    console.error('Error:', error);
                    mostrarEstadoSubida(vista, 'estado-error', '✖ Error al subir archivo');
                });
        }

        function initSubidaImagenes() {
            document.addEventListener('change', function (e) {
                var input = e.target.closest('input[data-subir-imagen]');
                if (input) subirImagen(input, input.dataset.subirImagen);
            });
        }

        // 5.c Selectores dinámicos de compatibilidad: <button data-agregar-select="radar|ew|armamento">
        var SELECTORES_COMPATIBLES = {
            radar: { contenedor: 'container-radares', nombre: 'radaresIds' },
            ew: { contenedor: 'container-ew', nombre: 'ewIds' },
            armamento: { contenedor: 'container-armamento', nombre: 'armamentoIds' }
        };

        function agregarOtroSelect(tipo) {
            var config = SELECTORES_COMPATIBLES[tipo];
            if (!config) return;
            var contenedor = document.getElementById(config.contenedor);
            if (!contenedor) return;
            var primero = contenedor.querySelector('select[name="' + config.nombre + '"]');
            if (!primero) return;

            var fila = document.createElement('div');
            fila.className = 'fila-select';

            var nuevo = primero.cloneNode(true);
            nuevo.value = '';

            var quitar = document.createElement('button');
            quitar.type = 'button';
            quitar.className = 'btn-quitar';
            quitar.textContent = '✕';
            quitar.setAttribute('aria-label', 'Quitar esta opción');
            quitar.addEventListener('click', function () { fila.remove(); });

            fila.appendChild(nuevo);
            fila.appendChild(quitar);
            contenedor.appendChild(fila);
        }

        function initSelectoresDinamicos() {
            document.addEventListener('click', function (e) {
                var boton = e.target.closest('[data-agregar-select]');
                if (boton) agregarOtroSelect(boton.dataset.agregarSelect);
            });
        }

                /* ----------------------------------------------------------------------
        

        /* ---------------------------------------------------------------------- */
        listo(function () {
            initMenuLateral();
            initAccionesGenericas();
            initCarruseles();
            initValidacionRegistro();
            initTipoMedio();
            initSubidaImagenes();
            initSelectoresDinamicos();
        });


        

    })();
