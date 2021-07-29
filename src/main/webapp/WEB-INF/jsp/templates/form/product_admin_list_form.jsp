<link rel="stylesheet" href="${CSS_FORM_PRODUCT}">
<form method="post">
    <div class="schema_group" style="display: block">
        <label type="text" id="product-name" class="fadeIn second">${TEXT[PRODUCT_NAME_FIELD]}: ${product.name} </label>
        <label type="text" id="product-price" class="fadeIn third">${TEXT[PRODUCT_PRICE_FIELD]}: ${product.price}</label>
        <label type="text" id="product-description" class="fadeIn third">${TEXT[DESCRIPTION_LABEL_TEXT]}: ${product.description}</label>
        <label type="text" id="product-is-in-stock" class="fadeIn third">${TEXT[IS_IN_STOCK_LABEL_TEXT]}: ${product.isInStock}</label>
        <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_CHANGE_PRODUCT_INFO_PAGE}&${PRODUCT_ID}=${product.id}"
                class="change-product-info">${TEXT[CHANGE_PRODUCT_BUTTON_TEXT]}</button>
    </div>
</form>