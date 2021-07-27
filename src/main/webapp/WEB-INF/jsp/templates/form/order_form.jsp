<link rel="stylesheet" href="${CSS_FORM_ORDER}">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<section class="order-form my-4 mx-4">
    <form class="order-form-input" method="post">
        <div class="container pt-4">

            <div class="row">
                <div class="row mx-4">
                    <div class="col-12 mb-2">
                        <label class="order-form-label">Name</label>
                    </div>
                    <div class="col-12 col-sm-6">
                        <input class="order-form-input" placeholder="First">
                    </div>
                    <div class="col-12 col-sm-6 mt-2 mt-sm-0">
                        <input class="order-form-input" placeholder="Last">
                    </div>
                </div>


                <div class="row mt-3 mx-4">
                    <div class="col-12">
                        <label class="order-form-label">Adress</label>
                    </div>
                    <div class="col-12">
                        <input class="order-form-input" placeholder="Address">
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

                <div class="row mt-3">
                    <div class="col-12">
                        <button type="submit" id="btnSubmit" formaction="${CONTROLLER_PATH}?${COMMAND}=${DELETE_FROM_CART}"
                                class="btn btn-dark d-block mx-auto btn-submit">Submit
                        </button>
                    </div>
                </div>

            </div>
        </div>
        </div>
    </form>
</section>