<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<div class="container">
    <form class="form-horizontal" role="form" method="post">
        <fieldset>
            <legend>${TEXT[ORDER_LABEL_TEXT]}</legend>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order-id">${TEXT[ORDER_ID_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order-id" id="order-id"> ${order.id} </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order_order_id">${TEXT[ORDER_ORDER_ID_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order_order_id" id="order_order_id">${order.orderId} </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="product-name">${TEXT[PRODUCT_NAME_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="product-name" id="product-name">${order.productName.name}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order-status">${TEXT[ORDER_STATUS_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order-status" id="order-status">${order.status}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order-details">${TEXT[ORDER_DETAILS_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order-details" id="order-details">${order.orderDetails.details}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order-date">${TEXT[ORDER_DATE_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order-date" id="order-date">${order.orderDetails.orderDate}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order-creator">${TEXT[ORDER_CREATOR_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order-creator" id="order-creator">${order.creator.email}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="order-recipient">${TEXT[ORDER_RECIPIENT_TEXT]}: </label>
                <div class="col-sm-9">
                    <label type="text" class="form-control" name="order-recipient" id="order-recipient">${order.recipient.email}</label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-success"
                            formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_CHANGE_ORDER_INFO_PAGE}&${ORDER_NUMBER}=${order.orderId}"
                            class="change-order">${TEXT[CHANGE_ORDER_BUTTON_TEXT]}</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>