<link rel="stylesheet" href="${CSS_FORM_ORDER}">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>



<div class="container pt-4">
    <form class="order-form my-4 mx-4" method="post">
        <div class="row">
            <div class="row mx-4">
                <div class="col-12 mb-2">
                    <label class="order-form-label">Name</label>
                </div>
                <div class="col-12 col-sm-6">
                    <input class="order-form-input" name="${NAME_OF_ORDER_CREATOR}" required placeholder="First">
                </div>
                <div class="col-12 col-sm-6 mt-2 mt-sm-0">
                    <input class="order-form-input" name="${SURNAME_OF_ORDER_CREATOR}" required placeholder="Last">
                </div>
            </div>


            <div class="row mt-3 mx-4">
                <div class="col-12">
                    <label class="order-form-label">Adress</label>
                </div>
                <div class="col-12">
                    <input class="order-form-input" name="${ADDRESS_ORDER_CREATOR}" required placeholder="Address">
                </div>
            </div>

            <div class="row mt-3 mx-4">
                <div class="col-12">
                    <label class="order-form-label">Date</label>
                </div>
                <div class="col-12">
                    <input class="order-form-input" name="${DATE}" required type="date" placeholder="Date">
                </div>
            </div>

            <div class="row mt-3 mx-4">
                <div class="col-12">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="validation" id="validation" value="1">
                        <label for="validation" class="form-check-label">I know what I need to know</label>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-12">
                <button type="submit" id="btnSubmit" formaction="${CONTROLLER_PATH}?${COMMAND}=${CREATE_ORDER}"
                        class="btn btn-dark d-block mx-auto btn-submit">Submit
                </button>
            </div>
        </div>
    </form>
</div>

