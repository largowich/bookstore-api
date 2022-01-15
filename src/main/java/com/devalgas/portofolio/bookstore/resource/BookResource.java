package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.domain.Book;
import com.devalgas.portofolio.bookstore.repository.BookRepository;
import com.devalgas.portofolio.bookstore.resource.errors.BadRequestAlertException;
import com.devalgas.portofolio.bookstore.service.BookService;
import com.devalgas.portofolio.bookstore.utility.PaginationUtil;
import com.devalgas.portofolio.bookstore.utility.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author devalgas kamga on 14/01/2022. 01:53
 * REST controller for managing {@link Book}.
 */
@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private static final String ENTITY_NAME = "book";

    @Value("${bookstore.clientApp.name}")
    private String applicationName;

    private final BookService bookService;

    private final BookRepository bookRepository;

    public BookResource(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    /**
     * {@code POST  /add/books} : Create a new book.
     *
     * @param book the book to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new book, or with status {@code 400 (Bad Request)} if the book has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/add/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) throws URISyntaxException {
        log.debug("REST request to save Book : {}", book);
        if (book.getId() != null) {
            throw new BadRequestAlertException("A new book cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Book result = bookService.save(book);
        return ResponseEntity.created(new URI("/api/add/books/" + result.getId())).body(result);
    }

    /**
     * {@code PUT  /books/:id} : Updates an existing book.
     *
     * @param id   the id of the book to save.
     * @param book the book to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id", required = false) final Long id, @RequestBody Book book)
            throws URISyntaxException {
        log.debug("REST request to update Book : {}, {}", id, book);
        if (book.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, book.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Book result = bookService.save(book);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PATCH  /books/:id} : Partial updates given fields of an existing book, field will ignore if it is null
     *
     * @param id   the id of the book to save.
     * @param book the book to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 404 (Not Found)} if the book is not found,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/books/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Book> partialUpdateBook(@PathVariable(value = "id", required = false) final Long id, @RequestBody Book book) throws URISyntaxException {
        log.debug("REST request to partial update Book partially : {}, {}", id, book);
        if (book.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, book.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Book> result = bookService.partialUpdate(book);

        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code GET  /books} : get all the books.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(Pageable pageable) {
        log.debug("REST request to get a page of Books");
        Page<Book> page = bookService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /books/:id} : get the "id" book.
     *
     * @param id the id of the book to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the book, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        log.debug("REST request to get Book : {}", id);
        Optional<Book> book = bookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(book);
    }

    /**
     * {@code DELETE  /books/:id} : delete the "id" book.
     *
     * @param id the id of the book to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws IOException {
        log.debug("REST request to delete Book : {}", id);
        bookService.delete(id);
        String fileName = id + ".png";

        Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));

        return ResponseEntity.noContent().build();
    }

    /**
     * {@code GET  /bookList} : get all the books.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping("/bookList")
    public ResponseEntity<List<Book>> getBookList() {
        log.debug("REST request to get of Books");
        return ResponseEntity.ok().body(bookService.findAll());

    }

    /**
     * {@code POT  /add/imageBook} : save image an existing book.
     *
     * @param id       the id to retrieve.
     * @param response the httpServletResponse information .
     * @param request  the request to information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/add/imageBook")
    public ResponseEntity<String> upload(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to upload image Book : {}, {}, {}", id, response, request);
        try {
            Book book = bookService.findOne(id).orElse(null);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";


            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * {@code PUT  /update/image} : update image an existing book.
     *
     * @param id       the id to retrieve.
     * @param response the httpServletResponse information .
     * @param request  the request to information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/update/image")
    public ResponseEntity<String> updateImagePost(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to update image update partially : {}, {}, {}", id, response, request);

        try {
            Book book = bookService.findOne(id).orElseThrow(() -> new Exception("book empty"));
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";

            Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * {@code GET  /searchBook} : search book.
     *
     * @param keyword the title of the book to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of book in body.
     */
    @GetMapping("/searchBook")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String keyword) {
        log.debug("REST request to get of Books by keyword : {} ", keyword);
        List<Book> bookList = bookService.blurrySearch(keyword);
        return ResponseEntity.ok().body(bookList);
    }
}
