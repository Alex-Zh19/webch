<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${CONTROLLER_PATH}?${COMMAND}=${OPEN_HOME_PAGE}">
                    ${TEXT[PAGE_HOME]}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="controller?command=open-shop-page">
                    ${TEXT[SHOP]}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${CONTROLLER_PATH}?${COMMAND}=${OPEN_SIGN_IN_PAGE}">
                    ${TEXT[PAGE_SIGN_IN]}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${CONTROLLER_PATH}?${COMMAND}=${OPEN_SIGN_UP_PAGE}">
                    ${TEXT[PAGE_SIGN_UP]}
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${CONTROLLER_PATH}?${COMMAND}=${OPEN_CART_PAGE}">
                    ${TEXT[CART_TEXT]}
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle"
                   id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    ${TEXT[LANGUAGE]}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                       href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_LANGUAGE}&${LANGUAGE}=${RUSSIAN_LANGUAGE}">
                        ${TEXT[LANGUAGE_RUSSIAN]}</a>
                    <input type="hidden" name="language" value="English"/>
                    <a class="dropdown-item"
                       href="${CONTROLLER_PATH}?${COMMAND}=${CHANGE_LANGUAGE}&${LANGUAGE}=${ENGLISH_LANGUAGE}">
                        ${TEXT[LANGUAGE_ENGLISH]}</a>
                    <div class="dropdown-divider"></div>
                </div>
            </li>
        </ul>

    </div>
</nav>
