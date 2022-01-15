package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.domain.Book;
import com.devalgas.portofolio.bookstore.domain.CartItem;
import com.devalgas.portofolio.bookstore.domain.ShoppingCart;
import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.service.BookService;
import com.devalgas.portofolio.bookstore.service.CartItemService;
import com.devalgas.portofolio.bookstore.service.ShoppingCartService;
import com.devalgas.portofolio.bookstore.service.UserService;
import com.devalgas.portofolio.bookstore.utility.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author devalgas kamga on 15/01/2022. 15:38
 */
public class ShoppingCartResource {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartResource.class);

    private final UserService userService;

    private final BookService bookService;

    private final CartItemService cartItemService;

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartResource(UserService userService, BookService bookService, CartItemService cartItemService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.bookService = bookService;
        this.cartItemService = cartItemService;
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * {@code GET  /shopping/add} :add item.
     *
     * @param mapper    the mapper of HashMap to retrieve.
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the mapper, keys: bookId, qty. or with status {@code 400 (Bad Request)} if the user is null or book is null.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/shopping/add")
    public ResponseEntity<String> addItem(@RequestBody HashMap<String, String> mapper, Principal principal) throws Exception {
        log.debug("REST request to add item: {} ", mapper);
        String bookId = (String) mapper.get("bookId");
        String qty = (String) mapper.get("qty");

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));
        Book book = bookService.findOne(Long.parseLong(bookId)).orElseThrow(() -> new Exception("book not found"));

        if (Integer.parseInt(qty) > book.getInStockNumber())
            return ResponseEntity.badRequest().body("Not Enough Stock!");

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
        return ResponseEntity.ok().body("Book Added Successfully");
    }

    /**
     * {@code GET  /getCartItemList} :get cart item list.
     *
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the list cartItems. or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/getCartItemList")
    public ResponseEntity<List<CartItem>> getCartItemList(Principal principal) throws Exception {
        log.debug("REST request to get cart item list");
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);
        return ResponseEntity.ok().body(cartItemList);
    }

    /**
     * {@code GET  /getShoppingCart} :get shopping.
     *
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the shoppingCart. or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/getShoppingCart")
    public ResponseEntity<ShoppingCart> getShoppingCart(Principal principal) throws Exception {
        log.debug("REST request to get shopping cart");
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new Exception("user not found"));

        ShoppingCart shoppingCart = user.getShoppingCart();

        shoppingCartService.updateShoppingCart(shoppingCart);

        return ResponseUtil.wrapOrNotFound(Optional.of(shoppingCart));
    }

    /**
     * {@code DEL  /removeItem} :remove item.
     *
     * @param id the principal of the cartItem.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Cart Item Removed Successfully!. or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @DeleteMapping("/removeItem")
    public ResponseEntity<String> removeItem(@RequestBody String id) throws Exception {
        log.debug("REST request to remove item, {}", id);

        cartItemService.removeCartItem(cartItemService.findOne(Long.parseLong(id)).orElseThrow(() -> new Exception("user not found")));
        return ResponseEntity.ok().body("Cart Item Removed Successfully!");
    }

    /**
     * {@code GET  /updateCartItem} :update cart item.
     *
     * @param mapper the mapper of the HashMap to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the mapper, keys: bookId, qty. or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @GetMapping("/updateCartItem")
    public ResponseEntity<String> updateCartItem(@RequestBody HashMap<String, String> mapper) throws Exception {
        log.debug("REST request to update cart item, {}", mapper);
        String cartItemId = mapper.get("cartItemId");
        String qty = mapper.get("qty");

        CartItem cartItem = cartItemService.findOne(Long.parseLong(cartItemId)).orElseThrow(() -> new Exception("user not found"));

        cartItem.setQty(Integer.parseInt(qty));

        cartItemService.updateCartItem(cartItem);
        return ResponseEntity.ok().body("Cart Item Updated Successfully!");
    }

}
