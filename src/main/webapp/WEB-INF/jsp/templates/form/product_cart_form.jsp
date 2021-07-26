<link rel="stylesheet" href="${CSS_FORM_PRODUCT}">
<form method="post">
    <div class="schema_group" style="display: block">
        <label type="text" id="product-name" class="fadeIn second">${TEXT[PRODUCT_NAME_FIELD]}: ${product.name} </label>
        <label type="text" id="product-price" class="fadeIn third">${TEXT[PRODUCT_PRICE_FIELD]}: ${product.price}</label>
        <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${OPEN_PRODUCT_PAGE}&${PRODUCT_ID}=${product.id}"
                class="open-product-button">
            ${TEXT[OPEN_PRODUCT_PAGE_TEXT]}</button>
        <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${DELETE_FROM_CART}&${PRODUCT_ID}=${product.id}"
                class="add-to-cart-button">
            ${TEXT[DELETE_FROM_CART_TEXT]}</button>

    </div>
</form>