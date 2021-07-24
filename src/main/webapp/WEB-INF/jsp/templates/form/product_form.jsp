<div class="schema_group" style="display: block">
    <label type="text" id="product-name" class="fadeIn second" >name: ${product.name} </label>
    <label type="text" id="product-price" class="fadeIn third" >price: ${product.price}</label>
    <a class="open-product" href="controller?command=open-product-page&${PRODUCT_ID}=${product.id}">Open product</a>
    <a class="add-to-product" href="controller?command=open-product&productId=">add to korzinka</a>
</div>