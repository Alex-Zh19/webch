<link rel="stylesheet" href="${CSS_FORM_PRODUCT}">
<form method="post">
  <div class="schema_group" style="display: block">
    <label type="text" id="product-name" class="fadeIn second">${TEXT[PRODUCT_NAME_FIELD]}: ${product.name} </label>
    <label type="text" id="product-price" class="fadeIn third">${TEXT[PRODUCT_PRICE_FIELD]}: ${product.price}</label>
    <label type="text" id="product-description" class="fadeIn third">${TEXT[DESCRIPTION_LABEL_TEXT]}: ${product.description}</label>
    <button type="submit" formaction="${CONTROLLER_PATH}?${COMMAND}=${ADD_TO_CART}&${PRODUCT_ID}=${product.id}"
            class="add-to-cart-button">
      ${TEXT[ADD_TO_CART_TEXT]}</button>

  </div>
</form>