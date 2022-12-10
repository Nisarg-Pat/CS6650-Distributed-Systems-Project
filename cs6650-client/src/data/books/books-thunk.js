import {createAsyncThunk} from "@reduxjs/toolkit";
import {addBook, getBookListOfUser, searchBook, sellBook} from "./book-service";

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
    async (user) => await sellBook(user)
)

export const searchBookThunk = createAsyncThunk(
    'searchBook',
    async (search) => await searchBook(search)
)