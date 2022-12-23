import {createAsyncThunk} from "@reduxjs/toolkit";
import {addBook, deleteBook, getBookListOfUser, searchBook, sellBook, shelfBook} from "./book-service";

export const addBookThunk = createAsyncThunk(
    'addbook',
    async (book) => await addBook(book)
)

export const bookListThunk = createAsyncThunk(
    'bookList',
    async (user) => await getBookListOfUser(user)
)

export const sellBookThunk = createAsyncThunk(
    'sellBook',
    async (book) => await sellBook(book)
)

export const shelfBookThunk = createAsyncThunk(
    'shelfBook',
    async (book) => await shelfBook(book)
)

export const deleteBookThunk = createAsyncThunk(
    'deleteBook',
    async (book) => await deleteBook(book)
)

export const searchBookThunk = createAsyncThunk(
    'searchBook',
    async (search) => await searchBook(search)
)